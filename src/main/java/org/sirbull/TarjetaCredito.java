package org.sirbull;

public class TarjetaCredito {

    //ATRIBUTOS PRINCIPALES DE LA CLASE
      private double limite;
      private double deudaActual;



    //CONSTRUCTUR
    public TarjetaCredito(double limite){
        this.limite=limite;
        this.deudaActual=0.0;
    }



    //METODO
    public boolean comprar (double monto){
        if((this.deudaActual + monto) <= this.limite) {
            this.deudaActual = this.deudaActual+monto;
            System.out.println("Compra realizada");
            return true;
        }
        else{
            System.out.println("Compra rechazada, excede el limite de la linea de credito");
            return false;
        }
    }

    //GETTERS FORMULA : public + tipo_de_dato + getNombreAtributo() + { return atributo,}

    public double getLimite(){
        return this.limite;
    }

    public double getDeudaActual(){
        return this.deudaActual;
    }


}
