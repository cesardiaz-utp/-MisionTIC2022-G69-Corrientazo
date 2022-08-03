package co.edu.utp.misiontic.cesardiaz.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic.cesardiaz.modelo.OpcionSopa;
import co.edu.utp.misiontic.cesardiaz.util.JDBCUtilities;

public class OpcionSopaDao {

    public void guardar(OpcionSopa sopa) throws SQLException {
        PreparedStatement stmt = null;
        try {
            sopa.setId(generarConsecutivo());

            var conn = JDBCUtilities.getConnection();
            stmt = conn.prepareStatement("INSERT INTO OpcionSopa (id, nombre) VALUES (?, ?);");
            stmt.setInt(1, sopa.getId());
            stmt.setString(2, sopa.getNombre());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private Integer generarConsecutivo() throws SQLException {
        Integer resultado = 0;

        Statement stmt = null;
        ResultSet rset = null;
        try {
            var conn = JDBCUtilities.getConnection();
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT MAX(id) AS ID FROM opcionsopa;");
            if (rset.next()) {
                resultado = rset.getInt("ID");
            }
            resultado++;

        } finally {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return resultado;
    }

    public List<OpcionSopa> listar() throws SQLException {
        List<OpcionSopa> respuesta = null;

        Statement stmt = null;
        ResultSet rset = null;
        try {
            stmt = JDBCUtilities.getConnection().createStatement();
            rset = stmt.executeQuery("SELECT * FROM OPCIONSOPA");

            respuesta = new ArrayList<>();
            while (rset.next()) {
                var sopa = new OpcionSopa(rset.getString("NOMBRE"));
                sopa.setId(rset.getInt("ID"));

                respuesta.add(sopa);
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return respuesta;
    }
}
