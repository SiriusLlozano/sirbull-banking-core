package org.sirbull;

public class FinanzasUtil {


    //METODOS

    public static double calcularInteresSimple (double capital,double tasaAnual, int meses){

        double interes = capital * (tasaAnual / 100) * (meses / 12.00);
        return interes;
    }


}






