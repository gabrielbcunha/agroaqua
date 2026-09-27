#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <WiFi.h>
#include <HTTPClient.h>
#include <time.h>

// WIFI and API configuration
const char* WIFI_SSID = "YOUR_WIFI_NAME";
const char* WIFI_PASSWORD = "YOUR_WIFI_PASSWORD";
const char* API_URL = "YOUR_API_URL";

// Sensor ID and ApiKey configuration
const long SENSOR_ID = 0; //Change to your sensor ID 
const String rawApiKey = "YOUR_SENSOR_API_KEY";

// Implement and configure NTP
const char* NTP_SERVER = "a.st1.ntp.br";
const long GMT_OFFSET_SEC = -3 * 3600; // UTC-3
const int DAYLIGHT_OFFSET_SEC = 0;

// Configures LCD display
LiquidCrystal_I2C lcd(0x27, 16, 2);

// Pinout
const int PIN_SENSOR = 34; // Soil Moisture sensor
const int GREEN_LED  = 25; // Telemetry OK (201 Created)
const int YELLOW_LED = 26; // Processing / Sending HTTP POST
const int RED_LED    = 27; // Communication / Auth Failure
const int PIN_BUTTON = 14; // Manual reading button

// Intervals (10 minute for reading, 10s to turn off display/LEDs)
const unsigned long AUTOMATIC_INTERVAL_MS = 10UL * 60UL * 1000UL;
const unsigned long DISPLAY_TIMEOUT_MS    = 10UL * 1000UL;

// Control
unsigned long lastMeasurement = 0;
bool lastButton = HIGH;
bool isDisplayOn = true;

// Humidity calibration
const int LOW_VALUE  = 4075; 
const int HIGH_VALUE = 700;

// Configures percentage moisture
int moisturePercent(int measurement) {
  int pct = map(measurement, LOW_VALUE, HIGH_VALUE, 0, 100);
  return constrain(pct, 0, 100);
}

// Configures the activation of the diagnostic LEDs
void setLeds(const char* status) {
  digitalWrite(GREEN_LED, LOW);
  digitalWrite(YELLOW_LED, LOW);
  digitalWrite(RED_LED, LOW);
  
  if (strcmp(status, "PROCESSING") == 0) {
    digitalWrite(YELLOW_LED, HIGH);
  } else if (strcmp(status, "FAILURE") == 0) {
    digitalWrite(RED_LED, HIGH);
  } else if (strcmp(status, "OK") == 0) {
    digitalWrite(GREEN_LED, HIGH);
  }
}

// Turns off LCD backlight, clears text and turns off all LEDs
void sleepPeripherals() {
  lcd.clear();
  lcd.noBacklight();
  setLeds("OFF");
  isDisplayOn = false;
  Serial.println("[SYSTEM] Display and LEDs turned OFF (Standby)");
}

// Wakes up the LCD backlight before a new measurement
void wakeUpPeripherals() {
  if (!isDisplayOn) {
    lcd.backlight();
    isDisplayOn = true;
  }
}

// Configures the screen display
void lcdShow(int pct, const char* mode, const char* status) {
  wakeUpPeripherals();
  lcd.setCursor(0, 0);
  lcd.print("MOISTURE: ");

  if (pct < 100) {
    lcd.print(" ");
  }
  if (pct < 10) {
    lcd.print(" ");
  }

  lcd.print(pct);
  lcd.print("% ");

  lcd.setCursor(0, 1);
  lcd.print(mode);
  lcd.print(" ");
  lcd.print(status);

  int use = (int)strlen(mode) + 1 + (int)strlen(status);
  for (int i = use; i < 16; i++) {
    lcd.print(" ");
  }
}

// Gets the current date and time and formats it to the ISO-8601 standard
String getDateTimeISO() {
  struct tm timeinfo;

  if (!getLocalTime(&timeinfo)) {
    return "";
  }

  char buffer[25];
  strftime(buffer, sizeof(buffer), "%Y-%m-%dT%H:%M:%S", &timeinfo);
  return String(buffer);
}

// Connects the ESP32 to Wi-Fi
void connectWifi() {
  wakeUpPeripherals();
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("Connecting WIFI");
  setLeds("PROCESSING");

  WiFi.mode(WIFI_STA);
  WiFi.setSleep(false);         
  WiFi.setAutoReconnect(true);  
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);

  int attempts = 0;
  while (WiFi.status() != WL_CONNECTED && attempts < 20) {
    delay(500);
    Serial.print(".");
    lcd.setCursor(attempts % 16, 1);
    lcd.print(".");
    attempts++;
  }
  lcd.clear();
  
  if (WiFi.status() == WL_CONNECTED) {
    Serial.println("\nWi-Fi connected! IP: " + WiFi.localIP().toString());
    lcd.setCursor(0, 0);
    lcd.print("WiFi Connected!");
    setLeds("OK");
    configTime(GMT_OFFSET_SEC, DAYLIGHT_OFFSET_SEC, NTP_SERVER, "pool.ntp.org");
  } else {
    Serial.println("\nFailed to connect to Wi-Fi. Starting offline mode.");
    lcd.setCursor(0, 0);
    lcd.print("Offline mode");
    setLeds("FAILURE");
  }

  delay(1000);
  lcd.clear();
}

// Send the measurement to the API with automatic retry
void sendToApi(int pct, const char* mode) {
  if (WiFi.status() != WL_CONNECTED) {
    Serial.println("[API] No Wi-Fi, trying to reconnect...");
    lcd.setCursor(0, 1);
    lcd.print("Error: No WiFi  ");
    setLeds("FAILURE");
    WiFi.disconnect();
    WiFi.reconnect();
    delay(2000);
    if (WiFi.status() != WL_CONNECTED) {
      lcdShow(pct, mode, "NO WIFI");
      return;
    }
  }

  lcd.setCursor(0, 1);
  lcd.print("Sending to API..");
  setLeds("PROCESSING");

  String measuredAt = getDateTimeISO();

  String jsonPayload = "{";
  jsonPayload += "\"sensorId\":" + String(SENSOR_ID) + ",";
  jsonPayload += "\"humidity\":" + String(pct);

  if (measuredAt != "") {
    jsonPayload += ",\"measuredAt\":\"" + measuredAt + "\"";
  }
  jsonPayload += "}";

  int httpResponseCode = -1;
  int maxRetries = 3;

  for (int attempt = 1; attempt <= maxRetries; attempt++) {
    WiFiClient client;
    HTTPClient http;
    
    http.begin(client, API_URL);
    http.setConnectTimeout(5000); 
    http.setTimeout(5000);
    http.useHTTP10(true);         

    // Headers
    http.addHeader("Content-Type", "application/json");
    http.addHeader("X-Sensor-Key", rawApiKey);
    http.addHeader("Connection", "close");

    Serial.printf("[API] Attempt %d/%d - Sending POST: %s\n", attempt, maxRetries, jsonPayload.c_str());
    httpResponseCode = http.POST(jsonPayload);
    http.end();

    if (httpResponseCode == 200 || httpResponseCode == 201) {
      break; 
    }

    Serial.printf("[API] Attempt %d failed (Code %d). Retrying in 1s...\n", attempt, httpResponseCode);
    delay(1000);
  }

  lcd.setCursor(0, 1);
  if (httpResponseCode == 200 || httpResponseCode == 201) {
    Serial.printf("[API] Response HTTP: %d\n", httpResponseCode);
    lcd.print("API OK! Cod: ");
    lcd.print(httpResponseCode);
    setLeds("OK");
    delay(1500);
    lcdShow(pct, mode, "API: 201");
  } else {
    Serial.printf("[API] Final Error Code: %d\n", httpResponseCode);
    lcd.print("Error! Code: ");
    lcd.print(httpResponseCode);
    setLeds("FAILURE");
    delay(1500);
    lcdShow(pct, mode, "API ERROR");
  }
}

// Take a measurement
void takeMeasurement(const char* mode) {
  wakeUpPeripherals();

  int measurement = analogRead(PIN_SENSOR);
  int pct = moisturePercent(measurement);

  lcdShow(pct, mode, "READING");

  Serial.print(mode);
  Serial.print(" raw=");
  Serial.print(measurement);
  Serial.print(" pct=");
  Serial.println(pct);

  sendToApi(pct, mode);

  lastMeasurement = millis();
} 

void setup() {
  Serial.begin(115200);

  pinMode(GREEN_LED, OUTPUT);
  pinMode(YELLOW_LED, OUTPUT);
  pinMode(RED_LED, OUTPUT);
  pinMode(PIN_BUTTON, INPUT_PULLUP);

  Wire.begin(21, 22);
  lcd.init();
  lcd.backlight();
  lcd.clear();

  lcd.setCursor(0, 0);
  lcd.print("Starting...");
  delay(600);

  connectWifi();

  takeMeasurement("AUTO");
}

void loop() {
  unsigned long now = millis();

  // Turns on the LCDs and LED after the button is clicked
  bool state = digitalRead(PIN_BUTTON);
  if (lastButton == HIGH && state == LOW) {
    delay(30);
    if (digitalRead(PIN_BUTTON) == LOW) {
      takeMeasurement("MANUAL");
    }
  }
  lastButton = state;
  
  // Turn off the LCD and LEDs 10 seconds after the measurement
  if (isDisplayOn && (now - lastMeasurement >= DISPLAY_TIMEOUT_MS)) {
    sleepPeripherals();
  }

  // Take a measurement every 10 minutes
  if (now - lastMeasurement >= AUTOMATIC_INTERVAL_MS) {
    takeMeasurement("AUTO");
  }
}