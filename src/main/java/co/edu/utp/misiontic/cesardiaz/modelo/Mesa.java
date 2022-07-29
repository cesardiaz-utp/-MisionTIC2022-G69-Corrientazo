package co.edu.utp.misiontic.cesardiaz.modelo;

import java.util.ArrayList;
import java.util.List;

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

    public List<Pedido> getPedidos() {
        return pedidos;
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

    @Override
    public String toString() {
        return "Mesa [numero=" + numero + "]";
    }

    public void borrarPedidos() {
        pedidos.clear();
    }


}
