package Modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CuentaAhorroProgramado extends Cuenta {

    private CuentaCorriente cuentaCorrienteLigada;
    private double rebajo;

    public CuentaAhorroProgramado(String cuentaNumero, double rebajo) {
        super(cuentaNumero, 0);
        this.rebajo = rebajo;
    }

    public boolean aplicarRebajo() {
        if(cuentaCorrienteLigada.getSaldo() >= rebajo) {
            cuentaCorrienteLigada.retiro(rebajo);
            this.saldo = rebajo;
            return true;
        } else {
            return false;
        }
    }
    //Dos condiciones
    //Primero 365 dias
    //Segundo no mayor al saldo
    @Override
    public boolean retiro(double debito) {
        long diasEntreFechas = ChronoUnit.DAYS.between(this.fechaCreacion, LocalDate.now());
        if ( diasEntreFechas >= 365) {
            if (this.saldo >= debito) {
                this.saldo -= debito;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean ligarCuentaCorriente(Cuenta cuenta) {
        if (cuenta instanceof CuentaCorriente) {
            cuentaCorrienteLigada = (CuentaCorriente) cuenta;
            return true;
        } else {
            return false;
        }
    }

    public CuentaCorriente getCuentaCorrienteLigada() {
        return cuentaCorrienteLigada;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "cuentaNumero=" + cuentaNumero +
                ", saldo=" + saldo +
                ", ligada a la cuenta corriente=" + cuentaCorrienteLigada.getCuentaNumero() +
                '}';
    }
}
