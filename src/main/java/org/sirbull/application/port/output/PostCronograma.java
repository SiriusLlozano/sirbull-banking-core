package org.sirbull.application.port.output;
import org.sirbull.domain.model.CronogramaPago;

import java.time.LocalDate;
import java.util.List;

public interface PostCronograma {

    List<CronogramaPago> saveCronograma(
            double monto,
            LocalDate fechaCompra,
            int cuotas,
            int diaCierre,
            int diaPago,
            double ted);
}
