package co.edu.utp.misiontic.cesardiaz.modelo;

import static co.edu.utp.misiontic.cesardiaz.modelo.EstadoPedido.*;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String cliente;
    private Corrientazo almuerzo;
    private EstadoPedido estado;
    private List<Adicional> adicionales;

    public Pedido(String cliente, Corrientazo almuerzo) {
        this.cliente = cliente;
        this.almuerzo = almuerzo;

        estado = SIN_ENTREGAR;
        adicionales = new ArrayList<>();
    }

    public void setAlmuerzo(Corrientazo almuerzo) {
        this.almuerzo = almuerzo;
    }

    public String getCliente() {
        return cliente;
    }

    public Corrientazo getAlmuerzo() {
        return almuerzo;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void entregarPedido() {
        estado = PENDIENTE_COBRAR;
    }

    public void agregarAdicional(Adicional adicional) {
        this.adicionales.add(adicional);
    }

    public Integer calcularValor() {
        return almuerzo.getPrecio()
                + adicionales.stream()
                        .map(adicional -> adicional.getPrecio())
                        .reduce((a, b) -> a + b)
                        .orElse(0);
    }
}
