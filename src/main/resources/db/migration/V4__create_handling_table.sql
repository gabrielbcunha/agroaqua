CREATE TABLE tb_handlings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    plot_id BIGINT NOT NULL,
    category ENUM(
        'PLOWING',
        'FERTILIZE',
        'PLANTING',
        'MANUAL_IRRIGATION',
        'COLLECTION_OF_SOIL_SAMPLES',
        'TO_HARVEST') NOT NULL,
    description VARCHAR(300) NOT NULL,
    start_date_time DATETIME NOT NULL,
    end_date_time DATETIME NOT NULL,

    CONSTRAINT fk_employee_handling
    FOREIGN KEY (employee_id)
    REFERENCES tb_employees (id),

    CONSTRAINT fk_plot_handling
    FOREIGN KEY (plot_id)
    REFERENCES tb_plots (id)
)