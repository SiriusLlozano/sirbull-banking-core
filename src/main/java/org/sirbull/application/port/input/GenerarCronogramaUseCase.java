package org.sirbull.application.port.input;

import org.sirbull.application.usecase.CronogramaResultado;
import org.sirbull.domain.model.CronogramaPago;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
public interface GenerarCronogramaUseCase {
        CronogramaResultado generarCronograma(
                BigDecimal monto,
                LocalDate fechaCompra,
                int cuotas,
                int diaCierre,
                int diaPago,
                BigDecimal ted);
    }
