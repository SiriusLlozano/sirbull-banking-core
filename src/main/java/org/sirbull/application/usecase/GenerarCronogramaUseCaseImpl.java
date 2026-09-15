package org.sirbull.application.usecase;

import org.sirbull.application.port.input.GenerarCronogramaUseCase;
import org.sirbull.application.port.output.CronogramaRepositoryPort;
import org.sirbull.domain.model.CronogramaPago;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class GenerarCronogramaUseCaseImpl implements GenerarCronogramaUseCase {

    private final CronogramaRepositoryPort cronogramaRepositoryPort;

    public GenerarCronogramaUseCaseImpl(CronogramaRepositoryPort cronogramaRepositoryPort) {
        this.cronogramaRepositoryPort = cronogramaRepositoryPort;
    }

    @Override
    public CronogramaResultado generarCronograma(
            BigDecimal monto,
            LocalDate fechaCompra,
            int cuotas,
            int diaCierre,
            int diaPago,
            BigDecimal ted
    ) {
        // 1. Generamos las cuotas con el dominio puro
        List<CronogramaPago> cuotasGeneradas = CronogramaPago.cronogramaPago(
                monto, fechaCompra, cuotas, diaPago, diaCierre, ted
        );

        // 2. Guardamos en la BD a través del puerto de salida y capturamos el ID real
        long idGenerado = cronogramaRepositoryPort.guardarCronograma(
                monto,
                fechaCompra,
                cuotas,
                diaCierre,
                diaPago,
                ted,
                cuotasGeneradas
        );

        // 3. Retornamos un objeto envoltorio que lleva el ID real y las cuotas
        return new CronogramaResultado(idGenerado, cuotasGeneradas);
    }
}