package co.edu.utp.misiontic.cesardiaz.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic.cesardiaz.modelo.Mesa;
import co.edu.utp.misiontic.cesardiaz.util.JDBCUtilities;

public class MesaDao {

    public List<Mesa> listar() throws SQLException {
        List<Mesa> respuesta = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("SELECT * FROM MESA");
            rset = statement.executeQuery();

            respuesta = new ArrayList<>();
            while (rset.next()) {
                var mesa = new Mesa(rset.getString("numero"));
                mesa.setId(rset.getInt("id"));

                respuesta.add(mesa);
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return respuesta;
    }

    public void guardar(Mesa mesa) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("INSERT INTO MESA (ID, NUMERO) VALUES (?, ?);");
            statement.setInt(1, generarConsecutivo());
            statement.setString(2, mesa.getNumero());
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private Integer generarConsecutivo() throws SQLException {
        Integer respuesta = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rset = null;
        try {
            connection = JDBCUtilities.getConnection();
            statement = connection.prepareStatement("SELECT MAX(ID) AS ID FROM MESA");
            rset = statement.executeQuery();
            if (rset.next()) {
                respuesta = rset.getInt("ID");
            } 
            respuesta += 1;
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return respuesta;
    }
}
