package org.sirbull.domain.service;

import javax.swing.plaf.PanelUI;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class FinanzasUtil {

    // TODO : M1 paso 1 CALCULO DE FACTOR DE ACTUALIZACION Y TASA EFECTIVA DIARIA
    public static double factorActualizacion(double ted, int diasAcumulados){
        return 1/Math.pow((1+ ted),diasAcumulados);
    }

    // todo: M2 paso 2  CALCULAR LA SUMATORIA DE FACTORES
    public static double calcularSumaFactores (int numeroCuotas, List<Integer> diasAcumulados, double ted){
        double sumaFactor = 0.0;
        for (int i = 1;i<= numeroCuotas; i++){
            sumaFactor += factorActualizacion(ted, diasAcumulados.get(i - 1));
        }
        return sumaFactor;
    }

    // TODO: M2 PASO 3 EL CALCULO DE LA CUOTA FIJA (SISTEMA FRANCES)

   public static double calcularCuotaFija(double monto, double sumaFactores){

        return monto / sumaFactores;
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






