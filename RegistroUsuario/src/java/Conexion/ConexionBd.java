package Conexion;

import java.sql.*;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexionBd {

    public static Connection ObtenerConexion() throws NamingException, SQLException {

        InitialContext contexto = new InitialContext();
        DataSource origenDatos = (DataSource) contexto.lookup("jdbc/prolim");
        Connection cnn = origenDatos.getConnection();
        cnn.setAutoCommit(false);
        return cnn;

    }

    public static void CerrarConexion(Connection cnn) throws SQLException {

        if (cnn != null) {
            cnn.commit();
            cnn.close();
        }
        cnn = null;
    }
    
    public static void reversarBD(Connection cnn) throws SQLException {
        if (cnn != null) {
            cnn.rollback();
            cnn.close();
        }
        cnn = null;
    }
}
