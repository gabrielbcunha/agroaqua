package br.com.agroaqua.api.management.infrastructure.persistence.plot;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="tb_plots")
@Getter
@Setter
@NoArgsConstructor
public class PlotJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Long cropId;

    private BigDecimal totalArea;

    private BigDecimal usedArea;

    private BigDecimal availableArea;

    private BigDecimal width;

    private BigDecimal length;

}