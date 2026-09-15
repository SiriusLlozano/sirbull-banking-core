package org.sirbull.application.port.input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sirbull.application.port.output.CronogramaRepositoryPort;
import org.sirbull.application.usecase.CronogramaResultado;
import org.sirbull.application.usecase.GenerarCronogramaUseCaseImpl;
import org.sirbull.domain.model.CronogramaPago;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GenerarCronogramaUseCaseTest {
    @Mock
    private CronogramaRepositoryPort cronogramaRepositoryPort;

    @InjectMocks
    private GenerarCronogramaUseCaseImpl useCase;

    @Test
    void GenerarCronogramaExitoso (){

        //todo:GIVEN
        BigDecimal monto = new BigDecimal("2500"); // (o el monto que uses en tu test)
        LocalDate fechaCompra = LocalDate.now();
        int cuotas = 6 ;
        int diaPago = 15;
        int diaCierre = 18;
        BigDecimal ted = new BigDecimal("0.093508202").divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);

        //TODO: WHEN


        CronogramaResultado resultado = useCase.generarCronograma(monto,fechaCompra,cuotas,diaPago,diaCierre,ted);

        //todo: THEN

        assertEquals(6, resultado.cuotas().size());
    }
}