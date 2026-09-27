# AgroAqua - Smart Agricultural & Irrigation Management System

> **Status:** Work in Progress (Active Development)

**AgroAqua** is a full-cycle agricultural management and automated irrigation platform built with **Java (Spring Boot)** following **Domain-Driven Design (DDD)** principles, integrated with physical **ESP32 IoT telemetry nodes** for real-time soil moisture monitoring.

---

## Architecture & Bounded Contexts (DDD)

The backend is structured around isolated Bounded Contexts to keep domain rules decoupled from hardware infrastructure:

* **`management`:** Crop catalog (`Crop` ideal humidity levels and growth cycles), plots/fields registration, and agricultural lifecycle.
* **`telemetry`:** Ingestion of real-time soil moisture measurements (`POST /api/measurement`) and IoT sensor management (`POST /api/sensor`).
* **`operational`:** Creation of staff positions and definition of their roles for the granting of authority.
* **`identity` / Security:** Role-based access control via **JWT** for users/admins and custom **`X-Sensor-Key` (BCrypt hashed)** authentication dedicated to IoT devices.

---

## IoT Telemetry Node (ESP32)

The physical telemetry module reads soil moisture via an analog sensor (`GPIO 34`) and transmits JSON payloads over Wi-Fi to the Spring Boot API at 10-minute intervals (or via manual hardware trigger on `GPIO 14`), featuring local LCD 16x2 I2C feedback, diagnostic LEDs, and a 10-second standby power-saving mode.

**[View the full IoT Hardware Documentation, Pinout & Firmware (`/iot`)](./iot/README.md)**

---

## Tech Stack

* **Backend:** Java, Spring Boot, Spring Security (JWT + Custom API Key Filter), Spring Data JPA
* **Database & Migrations:** MySQL, Flyway
* **IoT / Embedded:** C++ (Arduino/ESP32), `HTTPClient`, I2C LCD, NTP Time Synchronization (`a.st1.ntp.br`)

---

## Project Roadmap

- [x] Domain modeling and DDD package structure
- [x] Database schema versioning with Flyway migrations
- [x] User authentication & authorization with Spring Security and JWT
- [x] IoT Sensor registration (`POST /api/sensor`) with BCrypt API Key hashing
- [x] Real-time soil moisture ingestion endpoint (`POST /api/measurement`)
- [x] ESP32 firmware implementation, physical protoboard assembly, and end-to-end integration
- [ ] Query endpoints (`GET`) for historical telemetry and sensor status
- [ ] Web Frontend dashboard (Angular)