package Modelo;

import java.time.LocalDate;

public abstract class Cuenta {

    protected String cuentaNumero;
    protected double saldo;
    //Variable para luego calcular que los retiros de la cuenta de ahorro programado, que haya pasado un año
    protected LocalDate fechaCreacion;

    public Cuenta(String cuentaNumero, double saldo) {
        this.cuentaNumero = cuentaNumero;
        this.saldo = saldo;
        this.fechaCreacion = LocalDate.now();

    }
    public String getCuentaNumero() {
        return cuentaNumero;
    }

    public double getSaldo() {
        return saldo;
    }

    public abstract boolean retiro (double debito);

}
