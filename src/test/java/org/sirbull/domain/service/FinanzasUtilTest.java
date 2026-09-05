package org.sirbull.domain.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class FinanzasUtilTest {

    @Test
    void probarCalculoFechaPago(){

        //todo : Arrange, datos de prueba:

        LocalDate fechaCompra = LocalDate.of(2026, 8, 23);
        int diaPago = 15;
        int diaCierre = 18;
        int numeroCuotas = 4;

        //todo: Act llamamos al metodo fechapago

        List<LocalDate> resultado = FinanzasUtil.fechaPago(fechaCompra,diaPago,diaCierre,numeroCuotas);
        // Imprime la lista completa de fechas en la consola de abajo
        System.out.println("Las fechas calculadas son: " + resultado);

        //todo: Assert
        assertEquals(4, resultado.size());
        assertEquals(LocalDate.of(2026, 10, 15), resultado.get(0));
    }

    @Test
    void calcularDiasAcumulados (){

        /*ARRANGE */
        LocalDate fechaCompra = LocalDate.of(2026,9,27);
        List<LocalDate> fechaPago = FinanzasUtil.fechaPago(fechaCompra, 25, 28, 4);
        List<Integer> resultadoEsperado = List.of(28,59,89,120);

        /*ACT*/
        List<Integer> resultadoObtenido = FinanzasUtil.calcularDiasAcumulados(fechaCompra,fechaPago);

        /* ASSERT */
        assertIterableEquals(resultadoEsperado, resultadoObtenido);

    };

    @Test
    void calcularSumaFactores(){

        //TODO: ARRANGE

        List<Integer> diasAcumulados = List.of(28, 59, 89, 120);
        double ted = 0.0005;

        //TODO: ACT

        double resultadoObtenido =FinanzasUtil.calcularSumaFactores(4, diasAcumulados, ted);

        //TODO: ASSERT

        double resultadoEsperado= 3.855266;

        assertEquals(resultadoEsperado, resultadoObtenido, 0.001);

    };

    @Test
    void calcularCuotaFija(){

        //todo: =========== ARRANGE ===========

        double monto = 2000;
        double sumaFactores = 3.855266;

        //todo: =========== ACT ============

        double valorCuotaFija = monto/sumaFactores;

        //todo: =========== ASSERT ===========

        assertEquals(518.7709486193689,valorCuotaFija);
    }





}