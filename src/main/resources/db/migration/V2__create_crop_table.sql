CREATE TABLE tb_crops (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(300) NOT NULL,
    normal_humidity DECIMAL(5,2) NOT NULL,
    standard_time_to_grow_in_days INT NOT NULL
);