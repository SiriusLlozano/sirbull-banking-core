package org.sirbull;

public class Main {
    public static void main(String[] args) {

        CuentaBancaria miCuenta = new CuentaBancaria("CTA-001","Hector", 500.00);
        System.out.println("Titular: " + miCuenta.getTitular());
        System.out.println("Numero de cuenta: " + miCuenta.getNumeroCuenta());
        System.out.println("Saldo: " + miCuenta.getSaldo());


        System.out.println("\n--- Probando Operaciones ---");
        miCuenta.depositar(200.00);
        miCuenta.retirar(150.00);
        miCuenta.retirar(1000.00);
        System.out.println("\nSaldo Final en objeto: S/ " + miCuenta.getSaldo());



        TarjetaCredito miTarjeta = new TarjetaCredito(2000.0);
        System.out.println("REALIZAR COMPRA");
        miTarjeta.comprar(400);
        System.out.println("Mi deuda actual es: " + miTarjeta.getDeudaActual());
        System.out.println("=====SEGUNDA COMPRA=====");
        miTarjeta.comprar(1800);
        System.out.println("Mi deuda final es: " + miTarjeta.getDeudaActual());

        double calcularInteres = FinanzasUtil.calcularInteresSimple(2500.00, 48.80, 12);
        System.out.println("el interes de este prestamos es de :" + calcularInteres);

        miCuenta.CambiarEstado(EstadoCuenta.BLOQUEADA);

        System.out.println("cambio de estado de cuenta a " + miCuenta.getEstado());

        System.out.println("REALIZAR COMPRA");
        miCuenta.retirar(400);







    }
}