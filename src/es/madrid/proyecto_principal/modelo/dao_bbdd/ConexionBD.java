package es.madrid.proyecto_principal.modelo.dao_bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase ConexionBD es una clase que usamos para la conexion a nuestra 
 * base de datos
 */

public class ConexionBD {
    //Unas constante para poder conectarnos a SQLdeveloper de Oracle
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
    private static final String USER = "GESTORTAREAS";
    private static final String PASSWORD = "manager";

    /**
     * metodo Connect que lo usamos para conectarnos a nuestra base de datos
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection connect() throws ClassNotFoundException, SQLException {
        
        String ojdbcPath = "lib/ojdbc.jar";
  
        Class.forName(JDBC_DRIVER);

        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
