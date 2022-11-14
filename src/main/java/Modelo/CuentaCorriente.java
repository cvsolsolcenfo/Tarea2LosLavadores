package Modelo;

public class CuentaCorriente extends Cuenta{


    public CuentaCorriente(String cuentaNumero, double saldo) {
        super(cuentaNumero, saldo);
    }

    public boolean deposito (double importe) {
        if (importe <= 0) {
            return false;
        } else {
            saldo += importe;
            return true;
        }

    }
    public boolean retiro (double debito) {
        if (debito <= 0) {
            return false;
        }
        if (debito > saldo) {
            return false;
        } else {
            saldo = saldo - debito;
            return true;
        }
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "cuentaNumero=" + cuentaNumero +
                ", saldo=" + saldo +
                '}';
    }


}

