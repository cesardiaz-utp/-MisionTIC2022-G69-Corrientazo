package co.edu.utp.misiontic.cesardiaz.vista;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import co.edu.utp.misiontic.cesardiaz.controlador.RestauranteControlador;
import co.edu.utp.misiontic.cesardiaz.modelo.Mesa;
import co.edu.utp.misiontic.cesardiaz.modelo.Pedido;

public class MesaVista {

    private Scanner sc;
    private RestauranteControlador controlador;

    public MesaVista(Scanner sc, RestauranteControlador controlador) {
        this.sc = sc;
        this.controlador = controlador;
    }

    public Mesa pedirInformacionMesa() {
        System.out.println(".: INFORMACION DE LA MESA :.");
        System.out.print("Ingrese el número de la mesa: ");
        var numero = sc.nextLine();

        return new Mesa(numero);
    }

    public void mostrarMesas(List<Mesa> mesas) {
        System.out.println(".: MESAS EN EL SISTEMA :.");
        mesas.forEach(System.out::println);
    }

    public Mesa consultarMesa() throws SQLException {
        System.out.println(".: CONSULTANDO MESAS :.");

        var mesas = controlador.getMesas();
        Mesa respuesta = null;
        do {
            try {
                System.out.println("Las mesas existentes son:");
                for (int i = 0; i < mesas.size(); i++) {
                    System.out.printf(" %d -> %s %n", (i + 1), mesas.get(i));
                }
                System.out.print("Cual es su opcion?: ");
                var opcion = sc.nextInt();
                if (opcion >= 1 && opcion <= mesas.size()) {
                    respuesta = mesas.get(opcion - 1);
                } else {
                    System.err.println("Opción inválida, intente de nuevo");
                }
            } catch (InputMismatchException e) {
                System.err.println("Opción inválida, intente de nuevo");
            } finally {
                sc.nextLine();
            }
        } while (respuesta == null);

        return respuesta;
    }

    public Pedido seleccionePedido(List<Pedido> opciones) {
        System.out.println(".: ELIJA EL PEDIDO :.");

        Pedido respuesta = null;
        do {
            try {
                System.out.println("Las opciones son:");
                for (int i = 0; i < opciones.size(); i++) {
                    System.out.printf(" %d -> %s %n", (i + 1), opciones.get(i));
                }
                System.out.print("Cual es su opcion?: ");
                var opcion = sc.nextInt();
                if (opcion >= 1 && opcion <= opciones.size()) {
                    respuesta = opciones.get(opcion - 1);
                } else {
                    System.err.println("Opción inválida, intente de nuevo");
                }
            } catch (InputMismatchException e) {
                System.err.println("Opción inválida, intente de nuevo");
            } finally {
                sc.nextLine();
            }
        } while (respuesta == null);

        return respuesta;
    }

    public Integer leerValorEfectivo() {
        Integer respuesta = null;
        while (respuesta == null) {
            try {
                System.out.print("Ingrese valor de efectivo: ");
                respuesta = sc.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("valor inválido, intente de nuevo");
            } finally {
                sc.nextLine();
            }
        }

        return respuesta;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarPedidos(List<Pedido> opciones) {
        System.out.println("Las pedidos son:");
        for (int i = 0; i < opciones.size(); i++) {
            System.out.printf(" %d -> %s %n", (i + 1), opciones.get(i));
        }
    }

}
