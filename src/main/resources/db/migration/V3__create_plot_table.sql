CREATE TABLE tb_plots (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(100) NOT NULL UNIQUE,
    crop_id BIGINT NULL,
    length DECIMAL(10,2) NOT NULL,
    width DECIMAL(10,2) NOT NULL,
    total_area DECIMAL(10,2) NOT NULL,
    used_area DECIMAL(10,2) NOT NULL,
    available_area DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_crop_plots FOREIGN KEY (crop_id) REFERENCES tb_crops(id)
);