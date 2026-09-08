package org.sirbull.application.port.input;

import org.junit.jupiter.api.Test;
import org.sirbull.application.usecase.GenerarCronogramaUseCaseImpl;
import org.sirbull.domain.model.CronogramaPago;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenerarCronogramaUseCaseTest {

    @Test
    void GenerarCronogramaExitoso (){

        //todo:GIVEN

        GenerarCronogramaUseCaseImpl useCase = new GenerarCronogramaUseCaseImpl();
        double monto = 2500;
        LocalDate fechaCompra = LocalDate.now();
        int cuotas = 6 ;
        int diaPago = 15;
        int diaCierre = 18;
        double ted =  0.093508202 / 100;

        //TODO: WHEN


        List<CronogramaPago> resultado = useCase.generarCronograma(monto,fechaCompra,cuotas,diaPago,diaCierre,ted);

        //todo: THEN

        assertEquals(6,resultado.size());
    }
}