package co.edu.utp.misiontic.cesardiaz.vista;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.utp.misiontic.cesardiaz.controlador.RestauranteControlador;

public class MenuPrincipal {

    private Scanner sc;
    private RestauranteControlador controlador;

    public MenuPrincipal(Scanner sc, RestauranteControlador controlador) {
        this.sc = sc;
        this.controlador = controlador;
    }

    public void iniciarAplicacion() throws SQLException {
        var enMenu = true;
        do {
            try {
                System.out.println(".: MENU PRINCIPAL :.");
                System.out.println("1 -> Gestion de pedidos");
                System.out.println("2 -> Pagos de mesas");
                System.out.println("3 -> Tablas maestras");
                System.out.println("0 -> Salir");
                System.out.print("Cual es su opcion: ");
                var opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 0:
                        System.out.println("Hasta pronto");
                        enMenu = false;
                        break;
                    case 1:
                        abrirMenuGestionPedidos();
                        break;
                    case 2:
                        pagosDeMesa();
                        break;
                    case 3:
                        abrirMenuTablasMaestras();
                        break;

                    default:
                        System.err.println("Opción inválida, intente de nuevo");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.err.println("Opción inválida, intente de nuevo");
                sc.nextLine();
            }
        } while (enMenu);
    }

    private void abrirMenuTablasMaestras() {
        var salida = false;
        while (!salida) {
            try {
                System.out.println(".: TABLAS MAESTRAS :.");
                System.out.println("1 -> Mesa");
                System.out.println("2 -> Opciones de Sopa");
                System.out.println("3 -> Opciones de Principio");
                System.out.println("4 -> Opciones de Carne");
                System.out.println("5 -> Opciones de Ensalada");
                System.out.println("6 -> Opciones de Jugo");
                System.out.println("7 -> Opciones de Adicionales");
                System.out.println("0 -> Salir al menu principal");
                System.out.print("Cual es su opcion: ");
                var opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 0:
                        salida = true;
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;

                    default:
                        System.err.println("Opción inválida, intente de nuevo");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.err.println("Opción inválida, intente de nuevo");
                sc.nextLine();
            }
        }
    }

    private void pagosDeMesa() throws SQLException {
        var mesa = controlador.consultarMesa();

        controlador.pagarCuenta(mesa);
    }

    private void abrirMenuGestionPedidos() throws SQLException {
        var mesa = controlador.consultarMesa();

        var salida = false;
        while (!salida) {
            try {
                System.out.println(".: GESTION DE PEDIDOS :.");
                System.out.println("1 -> Ver pedido de mesa");
                System.out.println("2 -> Agregar pedido");
                System.out.println("3 -> Agregar adicional a pedido");
                System.out.println("4 -> Entregar pedido");
                System.out.println("0 -> Salir al menu principal");
                System.out.print("Cual es su opcion: ");
                var opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 0:
                        salida = true;
                        break;
                    case 1:
                        controlador.mostrarPedidos(mesa);
                        salida = true;
                        break;
                    case 2:
                        controlador.agregarPedido(mesa);
                        salida = true;
                        break;
                    case 3:
                        // TODO Realizar este metodo
                        salida = true;
                        break;

                    case 4:
                        controlador.entregarPedido(mesa);
                        salida = true;
                        break;

                    default:
                        System.err.println("Opción inválida, intente de nuevo");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.err.println("Opción inválida, intente de nuevo");
                sc.nextLine();
            }
        }
    }
}
