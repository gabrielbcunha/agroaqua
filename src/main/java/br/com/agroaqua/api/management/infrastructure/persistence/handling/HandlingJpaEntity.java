package br.com.agroaqua.api.management.infrastructure.persistence.handling;

import br.com.agroaqua.api.management.domain.handling.HandlingCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="tb_handlings")
@Getter
@Setter
@NoArgsConstructor
public class HandlingJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private Long plotId;

    @Enumerated(EnumType.STRING)
    private HandlingCategory category;

    private String description;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

}