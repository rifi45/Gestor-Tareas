package es.madrid.proyecto_principal.modelo.dao_bbdd;

import java.sql.SQLException;

/**
 * Una interfaz de accesibilidad a la base de datos que define unos metodos que implementaremos 
 * en otras clases
 */

public interface IAccesible {
    public void insertarTareas() throws ClassNotFoundException, SQLException;
    public void obtenerTareas() throws ClassNotFoundException, SQLException;
    public void modificarTareas() throws ClassNotFoundException, SQLException;

}
