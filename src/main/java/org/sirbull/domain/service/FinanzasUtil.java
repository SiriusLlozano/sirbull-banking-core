package org.sirbull.domain.service;

import org.hibernate.usertype.BaseUserTypeSupport;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class FinanzasUtil {

    private  static final MathContext MATH_CONTEXT = new MathContext(10, RoundingMode.HALF_UP);

    // Cálculo del factor de actualización usando BigDecimal
    public static BigDecimal factorActualizacion(BigDecimal ted, int diasAcumulados){
        BigDecimal base = BigDecimal.ONE.add(ted, MATH_CONTEXT);
        BigDecimal potencia = base.pow(diasAcumulados,MATH_CONTEXT);
        return BigDecimal.ONE.divide(potencia, MATH_CONTEXT);
    }

    // Cálculo de la sumatoria de factores
    public static BigDecimal calcularSumaFactores (int numeroCuotas, List<Integer> diasAcumulados, BigDecimal ted){
        BigDecimal sumaFactor = BigDecimal.ZERO;
        for (int i = 1;i<= numeroCuotas; i++){
            BigDecimal factor = factorActualizacion(ted, diasAcumulados.get(i - 1));
            sumaFactor = sumaFactor.add(factor, MATH_CONTEXT);
        }
        return sumaFactor;
    }

    // Cálculo de la cuota fija (Sistema Francés) con BigDecimal
    public static BigDecimal calcularCuotaFija(BigDecimal monto, BigDecimal sumaFactores){
        return monto.divide(sumaFactores, MATH_CONTEXT);
    }

    public static List<LocalDate> fechaPago(LocalDate fechaCompra, int diaPago, int diaCierre, int numeroCuotas){

        int diaCompra = fechaCompra.getDayOfMonth();
        List<LocalDate> listaFechas = new ArrayList<>();
        LocalDate fechaPrimerPago ;

        if (diaCompra <= diaCierre){
            fechaPrimerPago = fechaCompra.plusMonths(1).withDayOfMonth(diaPago);
        }
        else {
            fechaPrimerPago = fechaCompra.plusMonths(2).withDayOfMonth(diaPago);
        }
        listaFechas.add(fechaPrimerPago);
        for (int i = 1; i < numeroCuotas; i ++){
            listaFechas.add(fechaPrimerPago.plusMonths(i));
        }
        return listaFechas;
    }

    public static List<Integer> calcularDiasAcumulados(LocalDate fechaCompra, List<LocalDate> listaFechas) {
        List<Integer> listaDias = new ArrayList<>();
        for (int i = 0; i < listaFechas.size(); i++) {
            int diasAcumulados = (int) ChronoUnit.DAYS.between(fechaCompra, listaFechas.get(i))+1;

            listaDias.add(diasAcumulados);
        }
        return listaDias;
    }
}






