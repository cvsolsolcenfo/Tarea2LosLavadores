package Controlador;

import Modelo.*;
import Vista.Menu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Banco Los Lavadores!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        Banco banco = new Banco("Banco Los Lavadores");
        String nombre;
        String identificacion;
        String fechaNac;
        int edad;
        String direccion;
        String cuentaNumero;
        double saldo;
        boolean ok;
        double importe;
        double debito;
        int opcion;
        int opcion2;
        launch();

        do {
            opcion = menu.opciones();
            switch (opcion) {
                case 1:
                    menu.mostrarTexto("Registrar cliente:");
                    nombre = menu.solicitaTexto("Ingrese nombre: ");
                    identificacion = menu.solicitaTexto("Ingrese identificación: ");
                    fechaNac = menu.solicitaTexto("Ingrese fecha de nacimiento: ");
                    edad = menu.solicitaNumeroEntero("Ingrese edad: ");
                    direccion = menu.solicitaTexto("Ingrese dirección de la casa: ");
                    Cliente cliente = new Cliente(nombre, edad, identificacion, fechaNac, direccion);
                    ok = banco.addCliente(cliente);
                    if (ok == true) {
                        menu.mostrarTexto("Cliente ingresado");
                    } else {
                        menu.mostrarTexto("El cliente ya existe!");
                    }
                    break;
                case 2:
                    menu.mostrarTexto("Listado Clientes:");
                    menu.listarClientes(banco.getListaCliente());
                    break;
                case 3:
                    opcion2 = menu.opcionesCuentas();
                    switch (opcion2) {
                        case 1:
                            menu.mostrarTexto("Crear cuenta corriente");
                            crearCuentaCorriente(banco, menu);

                            break;
                        case 2:
                            menu.mostrarTexto("Crear cuenta de ahorros");
                            crearCuentaAhorros(banco, menu);

                            break;
                        case 3:
                            menu.mostrarTexto("Crear cuenta de ahorro programado");
                            crearCuentaAhorroProgramado(banco, menu, banco);

                            break;
                        case 4:
                            menu.mostrarTexto("Ninguna, volver");
                            break;
                    }

                    break;
                case 4:
                    menu.mostrarTexto("Realizar depósito: ");
                    cuentaNumero = menu.solicitaTexto("Ingrese número de cuenta: ");
                    importe = menu.solicitaNumeroDouble("Ingrese el monto a depositar: ");
                    if (banco.realizaDeposito(cuentaNumero, importe) == true) {
                        menu.mostrarTexto("Deposito de fondos realizado con éxito");
                    } else {
                        menu.mostrarTexto("Error, deposito de fondos no realizado");
                    }
                    break;
                case 5:
                    menu.mostrarTexto("Realizar retiro: ");
                    cuentaNumero = menu.solicitaTexto("Ingrese número de cuenta: ");
                    debito = menu.solicitaNumeroDouble("Ingrese el monto a retirar: ");
                    if (banco.realizaRetiro(cuentaNumero, debito) == true) {
                        menu.mostrarTexto("Retiro de fondos realizado con éxito");
                    } else {
                        menu.mostrarTexto("Error, retiro  de fondos no realizado");
                    }
                    break;
                case 6:
                    menu.mostrarTexto("Mostrar saldo de cuenta: ");
                    cuentaNumero = menu.solicitaTexto("Ingrese número de cuenta: ");
                    saldo = banco.mostrarSaldo(cuentaNumero);
                    if (saldo <= 0) {
                        menu.mostrarTexto("Error, cuenta sin fondos");
                    } else {
                        menu.mostrarTexto("Saldo de la cuenta: " + saldo);
                    }
                    break;
                case 7:
                    menu.mostrarTexto("Gracias por ser parte del Banco Los Lavadores!!");
                    break;
            }
        } while (opcion != 7);

    }

    //POLIMORFISMO > TRABAJAR CON LA HERENCIA/ YA NO/ Mejo todas difererentes
    public static void crearCuentaCorriente(Banco banco, Menu menu) {
        String identificacion;
        String cuentaNumero;
        double saldo;
        boolean ok;

        //CONDICION CLIENTE EXISTE O NO
        identificacion = menu.solicitaTexto("Ingrese identificación del cliente: ");
        Cliente cliente = banco.existeCliente(identificacion);
        if (cliente != null) {
            //CONDICION NUMERO 7 DIGITOS
            cuentaNumero = menu.solicitaNumeroCuenta();
            //CONDICION MINIMO 50000
            saldo = menu.solicitaNumeroDouble("Ingrese el saldo inicial (debe ser mayor o igual a 50000): ");
            if (saldo <= 50000) {
                menu.mostrarTexto("El saldo inicial no puede ser menor a 50000 colones");
            } else {
                CuentaCorriente cuentaCorriente = new CuentaCorriente(cuentaNumero, saldo);
                ok = banco.addCuenta(cuentaCorriente, identificacion);
                if (ok == true) {
                    menu.mostrarTexto("Nueva cuenta cliente creada " + cuentaNumero);
                } else {
                    menu.mostrarTexto("Esta cuenta cliente ya existe!");
                }
            }
        } else {
            menu.mostrarTexto("El cliente no existe en el banco, debe registrar cliente primero (opc.1)");
        }

    }

    private static void crearCuentaAhorroProgramado(Banco banco, Menu menu, Banco banco1) {
        String identificacion;
        boolean ok;

        //CONDICION TIENE UNA CUENTA NORMAL YA, SOLICITA CEDULA Y RETORNA CUENTAS, DE ESTA MANERA ESCOGE CUAL LIGAR AHORRO
        identificacion = menu.solicitaTexto("Ingrese identificación del cliente: ");
        Cliente clienteEncontrado = banco.existeCliente(identificacion);
        if (clienteEncontrado != null) {
            ArrayList<CuentaCorriente> cuentasCorrientes = clienteEncontrado.getCuentaCorrientes();
            if (cuentasCorrientes.size() > 0) {
                menu.mostrarTexto("Sus cuentas existentes son las siguientes:");
                for (int i = 0; i < cuentasCorrientes.size(); i++) {
                    menu.mostrarTexto((i + 1) + ". " + cuentasCorrientes.get(i));
                }
                String mensaje = "Seleccione una para ligar a la cuenta de ahorro programado";
                int opcion = menu.solicitaNumeroEntero(mensaje, 1, cuentasCorrientes.size());
                String numeroCAP = menu.solicitaNumeroCuenta();
                double rebajo = menu.solicitaNumeroDouble("Indique cuánto será el rebajo");
                CuentaAhorroProgramado cuentaAhorroProgramado = new CuentaAhorroProgramado(numeroCAP, rebajo);
                cuentaAhorroProgramado.ligarCuentaCorriente(cuentasCorrientes.get(opcion - 1));

                ok = banco.addCuenta(cuentaAhorroProgramado, identificacion);
                if (ok == true) {
                    menu.mostrarTexto("Nueva cuenta de ahorros programado creada " + numeroCAP);
                } else {
                    menu.mostrarTexto("Esta cuenta de ahorros programado ya existe!");
                }
                menu.mostrarTexto("Cuenta de ahorro programado creada con éxito.");
                // No se como decirle cada viernes, jueves... saque dinero, cuando se liga se retira, y se supone que esa sera la fecha
                if (cuentaAhorroProgramado.aplicarRebajo()) {
                    menu.mostrarTexto("Se ha hecho el primer depósito a la cuenta de ahorro programado");
                } else {
                    menu.mostrarTexto("No hay suficiente dinero en la cuenta corriente para realizar la transferencia.");
                }
            } else {
                menu.mostrarTexto("El cliente no tiene cuentas corrientes");
            }
        } else {
            menu.mostrarTexto("ERROR: Usted aun no tiene una cuenta cliente");
        }
    }

    public static void crearCuentaAhorros(Banco banco, Menu menu) {
        String identificacion;
        String cuentaNumero;
        double saldo;
        boolean ok;

        identificacion = menu.solicitaTexto("Ingrese identificación del cliente: ");
        Cliente cliente = banco.existeCliente(identificacion);
        if (cliente != null) {
            //CONDICION NUMERO 7 DIGITOS
            cuentaNumero = menu.solicitaNumeroCuenta();
            //CONDICION MINIMO 50000
            saldo = menu.solicitaNumeroDouble("Ingrese el saldo inicial (debe ser mayor o igual a 50000): ");
            if (saldo <= 50000) {
                menu.mostrarTexto("El saldo inicial no puede ser menor a 50000 colones");
            } else {
                CuentaAhorros cuentaAhorros = new CuentaAhorros(cuentaNumero, saldo);
                ok = banco.addCuenta(cuentaAhorros, identificacion);
                if (ok == true) {
                    menu.mostrarTexto("Nueva cuenta de ahorros creada " + cuentaNumero);
                    cuentaAhorros.aplicaInteres();
                    menu.mostrarTexto("Se ha aplicado el interés.");
                } else {
                    menu.mostrarTexto("Esta cuenta de ahorros ya existe!");
                }
            }
        } else {
            menu.mostrarTexto("El cliente no existe en el banco, debe registrar cliente primero (opc.1)");
        }
    }
}



