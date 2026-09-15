package org.sirbull.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CronogramaPagoTest {

    @Test
    void cronogramaPagos(){

        //todo: ARRANGE
        BigDecimal monto = new BigDecimal("2000");
        LocalDate fechaCompra = LocalDate.now();
        int cuotas = 6 ;
        int diaPago = 15;
        int diaCierre = 18;
        BigDecimal ted = new BigDecimal("0.093508202").divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);

        //todo: ACT

        CronogramaPago.cronogramaPago(monto,fechaCompra,cuotas,diaPago,diaCierre,ted);

        //todo: ASSERT






    }

}