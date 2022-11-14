package Modelo;

import java.util.ArrayList;

public class Cliente {

    private String nombre;
    private int edad;
    private String identificacion;
    private String nacimiento;
    private String direccion;
    private ArrayList<CuentaCorriente> cuentaCorrientes;
    private ArrayList<CuentaAhorros> cuentaAhorros;
    private ArrayList<CuentaAhorroProgramado> cuentaAhorroProgramados;


    public Cliente(String nombre, int edad, String identificacion, String nacimiento, String direccion) {
        this.nombre = nombre;
        this.edad = edad;
        this.identificacion = identificacion;
        this.nacimiento = nacimiento;
        this.direccion = direccion;
        cuentaCorrientes = new ArrayList<CuentaCorriente>();
        cuentaAhorros = new ArrayList<CuentaAhorros>();
        cuentaAhorroProgramados = new ArrayList<CuentaAhorroProgramado>();

    }

    public String getIdentificacion() {

        return identificacion;
    }
    //Aca tambien se usa el instance of para agregar cada cuenta segun su arraylist de los 3 tipos
    public void agregarCuenta(Cuenta cuenta) {
        if(cuenta instanceof CuentaCorriente) {
            agregarCuentaCorriente((CuentaCorriente) cuenta);
        } else if(cuenta instanceof CuentaAhorros) {
            agregarCuentaAhorro((CuentaAhorros) cuenta);
        } else if(cuenta instanceof CuentaAhorroProgramado) {
            agregarCuentaAhorroProgramado((CuentaAhorroProgramado) cuenta);
        }
    }
    public void agregarCuentaCorriente(CuentaCorriente cuentaCorriente) {

        cuentaCorrientes.add(cuentaCorriente);
    }
    public void agregarCuentaAhorro(CuentaAhorros cuentaAhorro) {

        cuentaAhorros.add(cuentaAhorro);
    }
    public void agregarCuentaAhorroProgramado(CuentaAhorroProgramado cuentaAhorroProgramado) {
        cuentaAhorroProgramados.add(cuentaAhorroProgramado);
    }

    @Override
    public String toString() {
        String texto ="Cliente{" +
                " nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", identificacion='" + identificacion + '\'' +
                ", nacimiento='" + nacimiento + '\'' +
                ", direccion='" + direccion + '\'' +
                "}\n";
        if (cuentaCorrientes.size() > 0) {
            texto += "\tCuentas corrientes:" + cuentaCorrientes.size() + "\n";

            for (int i = 0; i < cuentaCorrientes.size(); i++) {
                texto += "\t" + cuentaCorrientes.get(i).toString() + "\n";
            }
        }
        if (cuentaAhorros.size() > 0) {
            texto += "\tCuentas de Ahorro:" + cuentaAhorros.size() + "\n";
            for (int i = 0; i < cuentaAhorros.size(); i++) {
                texto += "\t" + cuentaAhorros.get(i).toString() + "\n";
            }
        }
        if (cuentaAhorroProgramados.size() > 0) {
            texto += "\tCuentas de Ahorro Programado:" + cuentaAhorroProgramados.size() + "\n";
            for (int i = 0; i < cuentaAhorroProgramados.size(); i++) {
                texto += "\t" + cuentaAhorroProgramados.get(i).toString() + "\n";
            }
        }
        return texto;
    }
    public ArrayList<CuentaCorriente> getCuentaCorrientes() {

        return cuentaCorrientes;
    }
    public ArrayList<CuentaAhorros> getCuentaAhorros() {

        return cuentaAhorros;
    }
}

