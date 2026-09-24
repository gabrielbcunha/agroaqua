package br.com.agroaqua.api.management.infrastructure.persistence.crop;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="tb_crops")
@Getter
@Setter
@NoArgsConstructor
public class CropJpaEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal normalHumidity;

    private int standardTimeToGrowInDays;

}