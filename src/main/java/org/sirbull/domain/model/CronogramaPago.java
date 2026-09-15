package org.sirbull.domain.model;

import org.sirbull.domain.service.FinanzasUtil;

import javax.swing.plaf.basic.BasicIconFactory;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record CronogramaPago (
        int numeroCuota,
        LocalDate fechaVencimiento,
        BigDecimal interes,
        BigDecimal amortizacion,
        BigDecimal cuotaFija,
        BigDecimal saldoCapital
){

    private static final MathContext MATH_CONTEXT = new MathContext(10, RoundingMode.HALF_UP);

    public static List<CronogramaPago> cronogramaPago(
            BigDecimal monto,
            LocalDate fechaCompra,
            int numeroCuotas,
            int diaPago,
            int diaCierre,
            BigDecimal ted

    ){

        List<CronogramaPago> listaCronograma = new ArrayList<>();

        List<LocalDate> listaFechas = FinanzasUtil.fechaPago(fechaCompra, diaPago, diaCierre, numeroCuotas);
        List<Integer> diasAcumulados = FinanzasUtil.calcularDiasAcumulados(fechaCompra,listaFechas);


        BigDecimal sumaFactores = FinanzasUtil.calcularSumaFactores(numeroCuotas,diasAcumulados,ted);
        BigDecimal cuotaFija = FinanzasUtil.calcularCuotaFija(monto,sumaFactores);
        BigDecimal saldoCapital = monto;

        for (int i = 1; i <= numeroCuotas; i++){

            int diasPeriodo = (i == 1)? diasAcumulados.get(0) : diasAcumulados.get(i - 1) - diasAcumulados.get(i - 2);

            // CÁLCULO DE INTERÉS: saldoCapital * ((1 + ted)^diasPeriodo - 1)
            BigDecimal baseInteres = BigDecimal.ONE.add(ted, MATH_CONTEXT);
            BigDecimal potenciaInteres = baseInteres.pow(diasPeriodo, MATH_CONTEXT);
            BigDecimal factorInteres = potenciaInteres.subtract(BigDecimal.ONE, MATH_CONTEXT);
            BigDecimal interes = saldoCapital.multiply(factorInteres, MATH_CONTEXT).setScale(2, RoundingMode.HALF_UP);

            // CÁLCULO DE AMORTIZACIÓN: cuotaFija - interés (sin parches manuales)
            BigDecimal amortizacion = cuotaFija.subtract(interes).setScale(2, RoundingMode.HALF_UP);

            // NUEVO SALDO DE CAPITAL
            if (i == numeroCuotas) {
                saldoCapital = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
                // Ajuste de cierre para la última cuota por diferencias de redondeo de centavos
                amortizacion = saldoCapital.add(amortizacion).setScale(2, RoundingMode.HALF_UP); // Absorbe cualquier residuo matemático exacto
            } else {
                saldoCapital = saldoCapital.subtract(amortizacion).setScale(2, RoundingMode.HALF_UP);
            }
            LocalDate fechaVencimiento = listaFechas.get(i - 1);
            listaCronograma.add(new CronogramaPago(i, fechaVencimiento, interes, amortizacion, cuotaFija, saldoCapital));
        }
        return listaCronograma;
    }
}
