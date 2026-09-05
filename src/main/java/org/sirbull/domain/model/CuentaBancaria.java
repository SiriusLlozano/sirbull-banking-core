package org.sirbull.domain.model;

public class CuentaBancaria {

    private String numeroCuenta;
    private String titular;
    private double saldo;
    private EstadoCuenta estado;

    //creamos el constructor de la clase
    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial){
        this.numeroCuenta= numeroCuenta;
        this.titular= titular;
        this.saldo= saldoInicial;
        this.estado= EstadoCuenta.ACTIVA;
    }
    //ahora creamos lo smetodos depositar y retirar

    public boolean depositar(double monto) {
        if (monto > 0 ) {
            this.saldo += monto;
            System.out.println(" Deposito exitoso en la cuenta " + this.numeroCuenta + " Nuevo saldo:" + this.saldo);
            return true;
        } else {
            System.out.println(" El monto a depositar debe ser mayor a 0 ");
            return false;
        }
    }

    // Método para retirar dinero
    public boolean retirar(double monto) {


        if (this.estado !=  EstadoCuenta.ACTIVA) {

            System.out.println("Transaccion fallida: La cuenta se encuentra " + this.estado);
            return false;
        }

        if (monto > 0 && this.saldo >= monto) {
                this.saldo -= monto;
                System.out.println(" Retiro exitoso de la cuenta " + this.numeroCuenta + " Nuevo saldo: " + this.saldo);
                return true;
        } else {
                System.out.println(" Transacción fallida: Fondos insuficientes o monto inválido en la cuenta " + this.numeroCuenta);
                return false;
            }

    }
    public void CambiarEstado (EstadoCuenta nuevoEstado){

    this.estado= nuevoEstado;

    }






    public double getSaldo(){return this.saldo;}
    public String getTitular(){
        return  this.titular;
    }
    public String getNumeroCuenta(){
        return  this.numeroCuenta;
    }

    public EstadoCuenta getEstado(){        return estado;
    }
}






