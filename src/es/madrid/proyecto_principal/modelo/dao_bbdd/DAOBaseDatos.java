package es.madrid.proyecto_principal.modelo.dao_bbdd;

import java.sql.SQLException;

/**
 * Clase de DAO
 * Clase para la acceso y obtenecion de datos que estan guardados en nuestra 
 * Base de datos
 */

public class DAOBaseDatos implements IAccesible {
    static ConexionBD cnx = new ConexionBD();

    /**
     * Metodo que sirve para insertar nuevas tareas en nuestra base de datos
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void insertarTareas() throws ClassNotFoundException, SQLException{
        cnx.connect();

        
        
    }

    /**
     * Metodo que sirve para sacar todas las tareas existentes en nuestra base de datos
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void obtenerTareas()  throws ClassNotFoundException, SQLException{
        cnx.connect();


    }

    /**
     * metodo que sirve para modificar registros ya guardados en nuestra base de datos
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void modificarTareas()  throws ClassNotFoundException, SQLException{
        cnx.connect();

        
    }
    
}
