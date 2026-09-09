package org.sirbull.infrastructure.adapter.input;

import lombok.Data;
import java.time.LocalDate;


@Data
public class GenerarCronogramaRequestDto {

    private double monto;
    private LocalDate fechaCompra;
    private int cuotas;
    private int diaCierre;
    private int diaPago;
    private double ted;
}
