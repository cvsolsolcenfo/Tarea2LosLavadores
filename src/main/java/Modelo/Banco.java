package Modelo;

import java.util.ArrayList;

public class Banco {

    private String bancoNombre;
    private ArrayList<Cuenta> listaCuentasCliente;

    private ArrayList<Cliente> listaCliente;

    public Banco(String bancoNombre) {
        this.bancoNombre = bancoNombre;
        this.listaCuentasCliente = new ArrayList<Cuenta>() ;
        this.listaCliente = new ArrayList<Cliente>();
    }

    public boolean addCliente(Cliente cliente) {
        for(int i = 0; i < listaCliente.size(); i++) {
            if (cliente.getIdentificacion().equals(listaCliente.get(i).getIdentificacion())) {
                return false;
            }
        }
        listaCliente.add(cliente);
        return true;
    }
    /*Partes de instanceof en Java
    Dicho lo anterior, instanceof consta de tres partes:

    Una referencia (nunca una variable primitiva).
    La palabra clave instanceof.
    El nombre de la clase o interfaz de la que queremos comprobar si el objeto al que apunta la referencia utilizada es una instancia.
     */
    public boolean addCuenta(Cuenta cuenta, String identificacion) {
        for (int i = 0; i < listaCuentasCliente.size(); i++) {
            if (cuenta.getCuentaNumero() == (listaCuentasCliente.get(i).getCuentaNumero())) {
                return false;
            }
        }
        listaCuentasCliente.add(cuenta);
        for (int i = 0; i < listaCliente.size(); i++) {
            if (listaCliente.get(i).getIdentificacion().equalsIgnoreCase(identificacion)) {

                listaCliente.get(i).agregarCuenta(cuenta);
            }
        }
        return true;
    }

    public Cliente existeCliente(String id) {
        for (int i = 0; i < listaCliente.size(); i++) {
            if (listaCliente.get(i).getIdentificacion().equalsIgnoreCase(id)) {
                return listaCliente.get(i);
            }
        }
        return null;
    }

    public ArrayList<Cliente> getListaCliente() {

        return listaCliente;
    }

    public boolean realizaDeposito(String numeroCuenta, double importe) {
        for (int i = 0; i < listaCuentasCliente.size(); i++) {
            if (listaCuentasCliente.get(i).getCuentaNumero().equals(numeroCuenta)) {
                if (listaCuentasCliente.get(i)instanceof CuentaCorriente){
                    if (((CuentaCorriente)(listaCuentasCliente.get(i))).deposito(importe)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                if (listaCuentasCliente.get(i)instanceof CuentaAhorros){
                    if (((CuentaCorriente)(listaCuentasCliente.get(i))).deposito(importe)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
                return false;
            }
        }
        return false;
    }

    public boolean realizaRetiro(String numeroCuenta, double debito) {
        for (int i = 0; i < listaCuentasCliente.size(); i++) {
            if (listaCuentasCliente.get(i).getCuentaNumero().equals(numeroCuenta)) {
                if (listaCuentasCliente.get(i).retiro(debito)) {
                    return true;
                }
            }
        }
        return false;
    }

    public double mostrarSaldo(String numeroCuenta) {
        double saldo = -1;
        for (int i = 0; i < listaCuentasCliente.size(); i++) {
            if (listaCuentasCliente.get(i).getCuentaNumero().equals(numeroCuenta)) {
                saldo = listaCuentasCliente.get(i).getSaldo();
            }
        }
        return saldo;
    }

}
