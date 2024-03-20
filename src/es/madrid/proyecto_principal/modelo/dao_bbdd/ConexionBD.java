package es.madrid.proyecto_principal.modelo.dao_bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
    private static final String USER = "GESTORTAREAS";
    private static final String PASSWORD = "manager";

    public Connection connect() throws ClassNotFoundException, SQLException {
        // Asegúrate de que la ruta a ojdbc.jar sea correcta
        String ojdbcPath = "lib/ojdbc.jar";

        // Cargar el controlador JDBC de Oracle
        Class.forName(JDBC_DRIVER);

        // Establecer la conexión
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
