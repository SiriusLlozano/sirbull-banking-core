package org.sirbull.domain.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FinanzasUtilTest {

    @Test
    void probarCalculoFechaPago(){
        LocalDate fechaCompra = LocalDate.of(2026, 8, 23);
        int diaPago = 15;
        int diaCierre = 18;
        int numeroCuotas = 4;

        List<LocalDate> resultado = FinanzasUtil.fechaPago(fechaCompra, diaPago, diaCierre, numeroCuotas);
        System.out.println("Las fechas calculadas son: " + resultado);

        assertEquals(4, resultado.size());
        assertEquals(LocalDate.of(2026, 10, 15), resultado.get(0));
    }

    @Test
    void calcularDiasAcumulados (){
        LocalDate fechaCompra = LocalDate.of(2026,9,27);
        List<LocalDate> fechaPago = FinanzasUtil.fechaPago(fechaCompra, 25, 28, 4);
        List<Integer> resultadoEsperado = List.of(28, 59, 89, 120);

        List<Integer> resultadoObtenido = FinanzasUtil.calcularDiasAcumulados(fechaCompra, fechaPago);

        assertIterableEquals(resultadoEsperado, resultadoObtenido);
    }

    @Test
    void calcularSumaFactores(){
        // ARRANGE: Usamos BigDecimal para la TED
        List<Integer> diasAcumulados = List.of(28, 59, 89, 120);
        BigDecimal ted = new BigDecimal("0.0005");

        // ACT: Llamamos al método que ahora retorna BigDecimal
        BigDecimal resultadoObtenido = FinanzasUtil.calcularSumaFactores(4, diasAcumulados, ted);

        // ASSERT: Comparamos BigDecimal usando compareTo o un valor esperado escalado
        BigDecimal resultadoEsperado = new BigDecimal("3.855266").setScale(6, RoundingMode.HALF_UP);

        // compareTo() devuelve 0 si ambos BigDecimal son matemáticamente iguales
        assertEquals(0, resultadoEsperado.compareTo(resultadoObtenido.setScale(6, RoundingMode.HALF_UP)));
    }

    @Test
    void calcularCuotaFija(){
        // ARRANGE: Todo en BigDecimal
        BigDecimal monto = new BigDecimal("2000");
        BigDecimal sumaFactores = new BigDecimal("3.855266");

        // ACT
        BigDecimal valorCuotaFija = FinanzasUtil.calcularCuotaFija(monto, sumaFactores);

        // ASSERT: Validamos con un resultado esperado acotado a 2 o más decimales
        BigDecimal resultadoEsperado = new BigDecimal("518.77").setScale(2, RoundingMode.HALF_UP);

        assertEquals(0, resultadoEsperado.compareTo(valorCuotaFija.setScale(2, RoundingMode.HALF_UP)));
    }
}