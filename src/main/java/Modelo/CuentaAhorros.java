package Modelo;

public class CuentaAhorros extends Cuenta {

    /*
    Se quiere que la clase CuentaAhorro
    maneje una tasa de interés, pero que la tasa de interés
    sea la misma para todas las instancias de la clase. Un detalle importante de diseño es que
    VARIABLES DE CLASE (STATIC)
    Las variables de clase son atributos diferentes de las variables de instancia. Las variables de clase implican una sola zona de memoria reservada para todas las instancias de la clase, y no una copia por objeto, como sucede con las variables de instancia.
     */
    // si es static no se si lleva contructor?
    //MAYUSCULA POR SER CONSTANTE

    private static final double INTERES = 0.07;
    private double interesAcumulado;

    public CuentaAhorros(String cuentaNumero, double saldo) {

        super(cuentaNumero, saldo);
        interesAcumulado = 0;
    }

    @Override
    public boolean retiro(double debito) {
        if (this.saldo >= 100000 && (this.saldo / 2) >= debito){
            this.saldo -= debito;
            return true;
        } else {
            return false;
        }
    }

    public void aplicaInteres() {
        this.saldo = this.saldo * (1.0 + INTERES);
        this.interesAcumulado += this.saldo * INTERES;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "cuentaNumero=" + cuentaNumero +
                ", saldo=" + saldo +
                ", interes=" + CuentaAhorros.INTERES +
                ", intereses acumulados a la fecha =" + interesAcumulado +
                '}';
    }
}

