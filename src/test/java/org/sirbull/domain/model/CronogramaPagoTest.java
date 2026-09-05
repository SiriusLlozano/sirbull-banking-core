package org.sirbull.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CronogramaPagoTest {

    @Test
    void cronogramaPagos(){

        //todo: ARRANGE
        double monto = 2500;
        LocalDate fechaCompra = LocalDate.now();
        int cuotas = 6 ;
        int diaPago = 15;
        int diaCierre = 18;
        double ted =  0.093508202 / 100;

        //todo: ACT

        CronogramaPago.cronogramaPago(monto,fechaCompra,cuotas,diaPago,diaCierre,ted);

        //todo: ASSERT






    }

}