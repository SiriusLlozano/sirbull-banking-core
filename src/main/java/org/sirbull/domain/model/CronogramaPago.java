package org.sirbull.domain.model;

import org.sirbull.domain.service.FinanzasUtil;

import java.time.LocalDate;
import java.util.List;

public class CronogramaPago {

    public static void cronogramaPago(

            double monto,
            LocalDate fechaCompra,
            int numeroCuotas,
            int diaPago,
            int diaCierre,
            double ted

    ){

        //todo: FECHAS Y DIAS ACUMULADOS

        List<LocalDate> listaFechas = FinanzasUtil.fechaPago(fechaCompra, diaPago, diaCierre, numeroCuotas);
        List<Integer> diasAcumulados = FinanzasUtil.calcularDiasAcumulados(fechaCompra,listaFechas);

        // todo : SUMA DE FACTORES DE ACTUALIZACION

        double sumaFactores = FinanzasUtil.calcularSumaFactores(numeroCuotas,diasAcumulados,ted);

        //todo: LA CUOTA FIJA SE CALCULA UNA SOLA VEZ

        double cuotaFija = FinanzasUtil.calcularCuotaFija(monto,sumaFactores);

       double saldoCapital = monto;

        for (int i = 1; i <= numeroCuotas; i++){

            int diasPeriodo = (i == 1)? diasAcumulados.get(0) : diasAcumulados.get(i - 1) - diasAcumulados.get(i - 2);

           // TODO: Calculamos el interes
            double interes = saldoCapital * (Math.pow(1 + ted, diasPeriodo) - 1);
            double interesTruncado = Math.floor(interes * 100)/ 100;

           //TODO: Calculamos la amortizacion
            double amortizacion = (i == 1)? Math.floor((cuotaFija - interesTruncado)*100)/100 + 0.01 : cuotaFija - interesTruncado;


            //todo: Calculamos el nuevo saldo capital
            saldoCapital = (i == numeroCuotas)? 0.0: Math.floor((saldoCapital-amortizacion)*100)/100 ;

            System.out.printf("Cuota %d | Interes: %.2f | amortizacion: %.2f | cuota: %.2f | Saldo: %.2f  %n",
                    i, interesTruncado, amortizacion, cuotaFija, saldoCapital);
        }
    }
}
