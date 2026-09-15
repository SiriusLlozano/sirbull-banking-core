package org.sirbull.application.port.output;

import org.sirbull.domain.model.CronogramaPago;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CronogramaRepositoryPort {

    // Cambiamos el nombre y el tipo de retorno para que coincida exactamente con el adaptador
    long guardarCronograma(
            BigDecimal monto,
            LocalDate fechaCompra,
            int cuotasTotales,
            int diaCierre,
            int diaPago,
            BigDecimal ted,
            List<CronogramaPago> cuotas
    );


}
