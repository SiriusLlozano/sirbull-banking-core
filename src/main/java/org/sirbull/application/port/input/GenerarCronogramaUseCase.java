package org.sirbull.application.port.input;

import org.sirbull.domain.model.CronogramaPago;
import java.time.LocalDate;
import java.util.List;
public interface GenerarCronogramaUseCase {
        List<CronogramaPago> generarCronograma(
                double monto,
                LocalDate fechaCompra,
                int cuotas,
                int diaCierre,
                int diaPago,
                double ted);
    }
