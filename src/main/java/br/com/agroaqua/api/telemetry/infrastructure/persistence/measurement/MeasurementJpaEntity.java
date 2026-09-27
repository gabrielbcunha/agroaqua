package br.com.agroaqua.api.telemetry.infrastructure.persistence.measurement;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="tb_measurements")
@Getter
@Setter
@NoArgsConstructor
public class MeasurementJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sensorId;

    private BigDecimal humidity;

    private LocalDateTime measuredAt;

}
