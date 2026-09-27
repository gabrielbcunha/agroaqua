CREATE TABLE tb_sensors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(100) NOT NULL UNIQUE,
    plot_id BIGINT NOT NULL,
    active BOOLEAN NOT NULL,
    CONSTRAINT fk_plot_sensors
    FOREIGN KEY (plot_id)
    REFERENCES tb_plots (id)
);

CREATE TABLE tb_measurements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sensor_id BIGINT NOT NULL,
    humidity DECIMAL (5,2) NOT NULL,
    measured_at DATETIME NOT NULL,
    CONSTRAINT fk_sensor_measurements
    FOREIGN KEY (sensor_id)
    REFERENCES tb_sensors (id)
);