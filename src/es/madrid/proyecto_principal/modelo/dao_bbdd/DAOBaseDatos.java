package es.madrid.proyecto_principal.modelo.dao_bbdd;

import java.sql.SQLException;

public class DAOBaseDatos implements IAccesible {
    static ConexionBD cnx = new ConexionBD();

    @Override
    public void insertarTareas() throws ClassNotFoundException, SQLException{
        cnx.connect();

        
        
    }

    @Override
    public void obtenerTareas()  throws ClassNotFoundException, SQLException{
        cnx.connect();


    }

    @Override
    public void modificarTareas()  throws ClassNotFoundException, SQLException{
        cnx.connect();

        
    }
    
}
