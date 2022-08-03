package co.edu.utp.misiontic.cesardiaz.controlador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import co.edu.utp.misiontic.cesardiaz.exception.PagoException;
import co.edu.utp.misiontic.cesardiaz.modelo.EstadoPedido;
import co.edu.utp.misiontic.cesardiaz.modelo.Mesa;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionCarne;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionEnsalada;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionJugo;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionPrincipio;
import co.edu.utp.misiontic.cesardiaz.modelo.OpcionSopa;
import co.edu.utp.misiontic.cesardiaz.modelo.Pedido;
import co.edu.utp.misiontic.cesardiaz.modelo.dao.MesaDao;
import co.edu.utp.misiontic.cesardiaz.modelo.dao.OpcionAlimentoDao;
import co.edu.utp.misiontic.cesardiaz.modelo.dao.OpcionSopaDao;
import co.edu.utp.misiontic.cesardiaz.modelo.dao.PedidoDao;
import co.edu.utp.misiontic.cesardiaz.vista.MenuPrincipal;
import co.edu.utp.misiontic.cesardiaz.vista.MesaVista;
import co.edu.utp.misiontic.cesardiaz.vista.PedidoVista;

public class RestauranteControlador {

    private MenuPrincipal menuPrincipal;
    private MesaVista mesaVista;
    private PedidoVista pedidoVista;

    private MesaDao mesaDao;
    private PedidoDao pedidoDao;
    private OpcionSopaDao opcionSopaDao;
    private OpcionAlimentoDao<OpcionPrincipio> opcionPrincipioDao;
    private OpcionAlimentoDao<OpcionCarne> opcionCarneDao;
    private OpcionAlimentoDao<OpcionEnsalada> opcionEnsaladaDao;
    private OpcionAlimentoDao<OpcionJugo> opcionJugoDao;

    public RestauranteControlador(Scanner sc) {
        this.menuPrincipal = new MenuPrincipal(sc, this);
        this.mesaVista = new MesaVista(sc, this);
        this.pedidoVista = new PedidoVista(sc, this);

        this.mesaDao = new MesaDao();
        this.pedidoDao = new PedidoDao();
        this.opcionSopaDao = new OpcionSopaDao();

        this.opcionPrincipioDao = new OpcionAlimentoDao<>("OpcionPrincipio");
        this.opcionCarneDao = new OpcionAlimentoDao<>("OpcionCarne");
        this.opcionEnsaladaDao = new OpcionAlimentoDao<>("OpcionEnsalada");
        this.opcionJugoDao = new OpcionAlimentoDao<>("OpcionJugo");

    }

    public List<Mesa> getMesas() throws SQLException {
        return mesaDao.listar();
    }

    public List<OpcionSopa> getSopas() throws SQLException {
        return opcionSopaDao.listar();
    }

    public List<OpcionPrincipio> getPrincipios() throws SQLException {
        return opcionPrincipioDao.listar(rset -> {
            try {
                var principio = new OpcionPrincipio(rset.getString("nombre"));
                principio.setId(rset.getInt("id"));

                return principio;
            } catch (SQLException ex) {
                return null;
            }
        });
    }

    public List<OpcionCarne> getCarnes() throws SQLException {
        return opcionCarneDao.listar(rset -> {
            try {
                var opcion = new OpcionCarne(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));

                return opcion;
            } catch (SQLException ex) {
                return null;
            }
        });
    }

    public List<OpcionEnsalada> getEnsaladas() throws SQLException {
        return opcionEnsaladaDao.listar(rset -> {
            try {
                var opcion = new OpcionEnsalada(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));

                return opcion;
            } catch (SQLException ex) {
                return null;
            }
        });
    }

    public List<OpcionJugo> getJugos() throws SQLException {
        return opcionJugoDao.listar(rset -> {
            try {
                var opcion = new OpcionJugo(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));

                return opcion;
            } catch (SQLException ex) {
                return null;
            }
        });
    }

    public void crearMesa() throws SQLException {
        // Pedir al usuario la informacion necesaria para crear la mesa
        Mesa mesa = mesaVista.pedirInformacionMesa();

        // Almacenar la mesa
        mesaDao.guardar(mesa);

        // Listar las mesas que se encuentran en el sistema
        mesaVista.mostrarMesas(getMesas());
    }

    public void agregarPedido(Mesa mesa) {
        try {
            // Pedir al usuario la informacion del pedido
            var pedido = pedidoVista.pedirInformacionPedido();

            // Agregar el pedido a la mesa
            pedidoDao.guardar(mesa, pedido);
            // mesa.agregarPedido(pedido);

            // Mostrar confirmacion de agregar el pedido
            pedidoVista.mostrarMensaje("Se ha recibido el pedido de " + pedido.getCliente());
        } catch (SQLException ex) {
            System.err.println("Error accediendo a la base de datos: " + ex.getMessage());
        }
    }

    public Mesa consultarMesa() throws SQLException {
        return mesaVista.consultarMesa();
    }

    public void entregarPedido(Mesa mesa) {
        try {
            // Seleccionar pedido de mesa
            var pedidos = pedidoDao.listar(mesa).stream()
                    .filter(p -> p.getEstado() == EstadoPedido.SIN_ENTREGAR)
                    .collect(Collectors.toList());
            Pedido pedido = mesaVista.seleccionePedido(pedidos);

            // Marcar como entregado el pedido
            pedido.entregarPedido();
            pedidoDao.entregarPedido(pedido);
            pedidoVista.mostrarMensaje(String.format("El pedido de %s fue entregado",
                    pedido.getCliente()));

        } catch (Exception ex) {
            System.err.println("Error entregando pedidos: " + ex.getMessage());
        }
    }

    public void mostrarPedidos(Mesa mesa) {
        try {
            var pedidos = pedidoDao.listar(mesa);
            mesaVista.mostrarPedidos(pedidos);
        } catch (SQLException ex) {
            System.err.println("Error obteniendo pedidos: " + ex.getMessage());
        }
    }

    public void pagarCuenta(Mesa mesa) {
        try {
            var pedidos = pedidoDao.listar(mesa);
            var total = pedidos.stream()
                    .filter(pedido -> pedido.getEstado() == EstadoPedido.PENDIENTE_COBRAR)
                    .map(pedido -> pedido.calcularValor())
                    .reduce((a, b) -> a + b)
                    .orElse(0);

            pedidoVista.mostrarMensaje(String.format("La cuenta es: $ %,d", total));

            var efectivo = mesaVista.leerValorEfectivo();

            try {
                // Valido si es suficiente para pagar
                if (efectivo < total) {
                    throw new PagoException("El efectivo no es suficiente para cubrir la cuenta");
                }

                // Elimino los pedidos de la mesa
                pedidoDao.eliminarPedidosDeMesa(mesa);
                mesa.borrarPedidos();

                // Retorna la devuelta
                mesaVista.mostrarMensaje(String.format("La devuelta son: $ %,d", (efectivo - total)));
            } catch (PagoException ex) {
                mesaVista.mostrarMensaje(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error al pagar la cuenta: " + ex.getMessage());
        }
    }

    public void iniciarAplicacion() throws SQLException {
        menuPrincipal.iniciarAplicacion();
    }

}
