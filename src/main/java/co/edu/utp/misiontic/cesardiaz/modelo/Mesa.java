package co.edu.utp.misiontic.cesardiaz.modelo;

import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic.cesardiaz.exception.PagoException;

public class Mesa {
    private String numero;
    private List<Pedido> pedidos;

    public Mesa(String numero) {
        this.numero = numero;

        this.pedidos = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public void agregarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public Integer calcularValor() {
        var total = pedidos.stream()
                .filter(pedido -> pedido.getEstado() == EstadoPedido.PENDIENTE_COBRAR)
                .map(pedido -> pedido.calcularValor())
                .reduce((a, b) -> a + b)
                .orElse(0);
        return total;
    }

    public Integer pagar(Integer efectivo) throws PagoException {
        // Valido si es suficiente para pagar
        var total = calcularValor();
        if (efectivo < total) {
            throw new PagoException("El efectivo no es suficiente para cubrir la cuenta");
        }

        // Elimino los pedidos de la mesa
        pedidos.clear();

        // Retorna la devuelta
        return efectivo - total;
    }

}
