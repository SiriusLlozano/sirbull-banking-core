package org.sirbull.infrastructure.adapter.output.persistence.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "cronogramas")
public class CronogramaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal monto;
    @Column (nullable = false)
    private LocalDate fechaCompra;
    @Column(nullable = false)
    private Integer CuotasTotales;
    @Column(nullable = false)
    private Integer diaCierre;
    @Column(nullable = false)
    private Integer diaPago;
    @Column(nullable = false)
    private BigDecimal ted;

    @OneToMany(mappedBy = "cronograma", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CuotaEntity> cuotas;
}
