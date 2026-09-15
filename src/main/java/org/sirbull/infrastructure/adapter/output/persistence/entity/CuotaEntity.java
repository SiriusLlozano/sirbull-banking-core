package org.sirbull.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuotas")
public class CuotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer numeroCuota;
    @Column(nullable = false)
    private BigDecimal montoCuota;
    @Column(nullable = false)
    private LocalDate fechaVencimiento;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cronograma_id", nullable = false)
    private  CronogramaEntity cronograma;

}
