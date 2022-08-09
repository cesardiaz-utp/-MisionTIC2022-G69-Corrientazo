/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.misiontic.cesardiaz.controlador;

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
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ROG
 */
public class RestauranteGUIController {

    private MesaDao mesaDao;
    private PedidoDao pedidoDao;
    private OpcionAlimentoDao<OpcionSopa> opcionSopaDao;
    private OpcionAlimentoDao<OpcionPrincipio> opcionPrincipioDao;
    private OpcionAlimentoDao<OpcionCarne> opcionCarneDao;
    private OpcionAlimentoDao<OpcionEnsalada> opcionEnsaladaDao;
    private OpcionAlimentoDao<OpcionJugo> opcionJugoDao;

    public RestauranteGUIController() {
        this.mesaDao = new MesaDao();
        this.pedidoDao = new PedidoDao();
        this.opcionSopaDao = new OpcionAlimentoDao<>("OpcionSopa");
        this.opcionPrincipioDao = new OpcionAlimentoDao<>("OpcionPrincipio");
        this.opcionCarneDao = new OpcionAlimentoDao<>("OpcionCarne");
        this.opcionEnsaladaDao = new OpcionAlimentoDao<>("OpcionEnsalada");
        this.opcionJugoDao = new OpcionAlimentoDao<>("OpcionJugo");
    }

    public List<Mesa> listarMesas() throws SQLException {
        return mesaDao.listar();
    }

    public Integer calcularValorMesa(Mesa mesa) throws SQLException {
        var pedidos = pedidoDao.listar(mesa);
        return pedidos.stream()
                .filter(pedido -> pedido.getEstado() == EstadoPedido.PENDIENTE_COBRAR)
                .map(pedido -> pedido.calcularValor())
                .reduce((a, b) -> a + b)
                .orElse(0);
    }

    public List<Pedido> listarPedidos(Mesa mesa) throws SQLException {
        return pedidoDao.listar(mesa);
    }

    public List<OpcionSopa> listarSopas() throws SQLException {
        return opcionSopaDao.listar(rset -> {
            try {
                var dato = new OpcionSopa(rset.getString("nombre"));
                dato.setId(rset.getInt("id"));

                return dato;
            } catch (SQLException ex) {
                return null;
            }
        });
    }

    public List<OpcionPrincipio> listarPrincipios() throws SQLException {
        return opcionPrincipioDao.listar(rset -> {
            try {
                var dato = new OpcionPrincipio(rset.getString("nombre"));
                dato.setId(rset.getInt("id"));

                return dato;
            } catch (SQLException ex) {
                return null;
            }
        });
    }

    public List<OpcionCarne> listarCarnes() throws SQLException {
        return opcionCarneDao.listar(rset -> {
            try {
                var dato = new OpcionCarne(rset.getString("nombre"));
                dato.setId(rset.getInt("id"));

                return dato;
            } catch (SQLException ex) {
                return null;
            }
        });
    }

    public List<OpcionEnsalada> listarEnsaladas() throws SQLException {
        return opcionEnsaladaDao.listar(rset -> {
            try {
                var dato = new OpcionEnsalada(rset.getString("nombre"));
                dato.setId(rset.getInt("id"));

                return dato;
            } catch (SQLException ex) {
                return null;
            }
        });
    }

    public List<OpcionJugo> listarJugos() throws SQLException {
        return opcionJugoDao.listar(rset -> {
            try {
                var dato = new OpcionJugo(rset.getString("nombre"));
                dato.setId(rset.getInt("id"));

                return dato;
            } catch (SQLException ex) {
                return null;
            }
        });
    }

    public void entregarPedido(Pedido pedido) throws SQLException {
        // Marcar como entregado el pedido
        pedido.entregarPedido();
        pedidoDao.entregarPedido(pedido);
    }

    public void guardarPedido(Mesa mesa, Pedido pedido) throws SQLException {
        // Agregar el pedido a la mesa
        pedidoDao.guardar(mesa, pedido);
    }

    public Integer pagarMesa(Mesa mesa, Integer efectivo) throws SQLException, PagoException {
        // Valido si es suficiente para pagar
        var total = calcularValorMesa(mesa);
        if (efectivo < total) {
            throw new PagoException("El efectivo no es suficiente para cubrir la cuenta");
        }

        // Elimino los pedidos de la mesa
        pedidoDao.eliminarPedidosDeMesa(mesa);
        
        return efectivo - total;
    }
}
