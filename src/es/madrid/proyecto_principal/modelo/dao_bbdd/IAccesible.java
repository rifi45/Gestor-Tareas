package es.madrid.proyecto_principal.modelo.dao_bbdd;

import java.sql.SQLException;

public interface IAccesible {
    public void insertarTareas() throws ClassNotFoundException, SQLException;
    public void obtenerTareas() throws ClassNotFoundException, SQLException;
    public void modificarTareas() throws ClassNotFoundException, SQLException;

}
