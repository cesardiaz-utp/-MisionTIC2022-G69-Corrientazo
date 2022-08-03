package co.edu.utp.misiontic.cesardiaz.vista;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.utp.misiontic.cesardiaz.controlador.RestauranteControlador;
import co.edu.utp.misiontic.cesardiaz.modelo.Corrientazo;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionCarne;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionEnsalada;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionJugo;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionPrincipio;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionSopa;
import co.edu.utp.misiontic.cesardiaz.modelo.Pedido;

public class PedidoVista {

    private static final int PRECIO_CORRIENTAZO = 12_000;

    private Scanner sc;
    private RestauranteControlador controlador;

    public PedidoVista(Scanner sc, RestauranteControlador controlador) {
        this.sc = sc;
        this.controlador = controlador;
    }

    public Pedido pedirInformacionPedido() throws SQLException {
        System.out.println(".: INGRESANDO EL PEDIDO :.");

        System.out.print("Ingrese nombre (descripcion) del cliente: ");
        var cliente = sc.nextLine();

        var sopa = elegirOpcionSopa();
        var principio = elegirOpcionPrincipio();
        var carne = elegirOpcionCarne();
        var ensalada = elegirOpcionEnsalada();
        var jugo = elegirOpcionJugo();

        return new Pedido(cliente,
                new Corrientazo(PRECIO_CORRIENTAZO, sopa, principio, carne, ensalada, jugo));
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private OpcionSopa elegirOpcionSopa() throws SQLException {
        System.out.println(".: ELIJA SOPA :.");

        var opciones = controlador.getSopas();
        OpcionSopa respuesta = null;
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

    private OpcionPrincipio elegirOpcionPrincipio() throws SQLException {
        System.out.println(".: ELIJA PRINCIPIO :.");

        var opciones = controlador.getPrincipios();
        OpcionPrincipio respuesta = null;
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

    private OpcionCarne elegirOpcionCarne() throws SQLException {
        System.out.println(".: ELIJA CARNE :.");

        var opciones = controlador.getCarnes();
        OpcionCarne respuesta = null;
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

    private OpcionEnsalada elegirOpcionEnsalada() throws SQLException {
        System.out.println(".: ELIJA ENSALADA :.");

        var opciones = controlador.getEnsaladas();
        OpcionEnsalada respuesta = null;
        do {
            try {
                System.out.println("Las opciones son:");
                System.out.printf(" %d -> %s %n", 0, "Sin ensalada");
                for (int i = 0; i < opciones.size(); i++) {
                    System.out.printf(" %d -> %s %n", (i + 1), opciones.get(i));
                }
                System.out.print("Cual es su opcion?: ");
                var opcion = sc.nextInt();
                if (opcion == 0) {
                    break;
                } else if (opcion >= 1 && opcion <= opciones.size()) {
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

    private OpcionJugo elegirOpcionJugo() throws SQLException {
        System.out.println(".: ELIJA JUGO :.");

        var opciones = controlador.getJugos();
        OpcionJugo respuesta = null;
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

}
