/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.utp.misiontic.cesardiaz.vista.gui.modeloui;

import co.edu.utp.misiontic.cesardiaz.modelo.EstadoPedido;
import co.edu.utp.misiontic.cesardiaz.modelo.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ROG
 */
public class PedidoTableModel extends AbstractTableModel {

    private List<Pedido> datos;

    public PedidoTableModel() {
        this.datos = new ArrayList<>();
    }

    public void setDatos(List<Pedido> datos) {
        this.datos = datos;
        fireTableDataChanged();
    }

    public void addDato(Pedido pedido) {
        this.datos.add(pedido);
        var row = getRowCount() - 1;
        fireTableRowsInserted(row, row);
    }
    
    public Pedido getDato(Integer row) {
        return this.datos.get(row);
    }

    public void setDato(int row, Pedido pedido) {
        this.datos.set(row, pedido);
        fireTableRowsUpdated(row, row);
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int column) {
        var dato = datos.get(row);
        switch (column) {
            case 0: // Cliente
                return dato.getCliente();
            case 1: // Entregado
                return dato.getEstado() == EstadoPedido.PENDIENTE_COBRAR;
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: // Cliente
                return "Cliente";
            case 1: // Entregado
                return "Entregado";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: // Cliente
                return String.class;
            case 1: // Entregado
                return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }


}
