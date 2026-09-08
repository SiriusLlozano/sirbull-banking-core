package org.sirbull.application.usecase;

import org.sirbull.application.port.input.GenerarCronogramaUseCase;
import org.sirbull.domain.model.CronogramaPago;
import org.sirbull.domain.service.FinanzasUtil;

import java.time.LocalDate;
import java.util.List;

public class GenerarCronogramaUseCaseImpl implements GenerarCronogramaUseCase {
    @Override
    public List<CronogramaPago> generarCronograma(double monto, LocalDate fechaCompra, int cuotas, int diaCierre, int diaPago, double ted) {

        return CronogramaPago.cronogramaPago(monto,fechaCompra,cuotas,diaPago,diaCierre,ted);
    }
}
