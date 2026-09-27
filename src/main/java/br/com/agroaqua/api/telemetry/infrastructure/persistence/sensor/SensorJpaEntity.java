package br.com.agroaqua.api.telemetry.infrastructure.persistence.sensor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_sensors")
@Getter
@Setter
@NoArgsConstructor
public class SensorJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Long plotId;

    private boolean active;

    private String apiKeyHash;

}
