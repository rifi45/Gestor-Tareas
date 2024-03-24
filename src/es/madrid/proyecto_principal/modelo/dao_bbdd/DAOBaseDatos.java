package es.madrid.proyecto_principal.modelo.dao_bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.madrid.proyecto_principal.modelo.Tarea;
import es.madrid.proyecto_principal.modelo.TareaPersonal;
import es.madrid.proyecto_principal.modelo.TareaTrabajo;

/**
 * Clase de DAO
 * Clase para la acceso y obtenecion de datos que estan guardados en nuestra 
 * Base de datos
 */

public class DAOBaseDatos{
    static Connection cnx = null;

    /**
     * Metodo que sirve para insertar nuevas tareas en nuestra base de datos
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void insertarTareas(Tarea tarea) throws ClassNotFoundException, SQLException{
        cnx = ConexionBD.connect();
        String sql = "INSERT INTO TAREA(ID_TAREA, NOMBRE, DESCRIPCION, FECHA_LIMITE, PRIORIDAD, REALIZADA, ID_TIPO_TAREA) VALUES (ID_TAREA.NEXTVAL, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement pr = cnx.prepareStatement(sql)){
            pr.setString(1, tarea.getNombre());
            pr.setString(2, tarea.getDescripcion());
            pr.setString(3, tarea.getFechaLimite());
            pr.setInt(4, tarea.getPrioridad());
            pr.setString(5, devolverSioNo(tarea.isRealizada()));
            if(tarea instanceof TareaPersonal){
                pr.setInt(6, 1);
            }else if(tarea instanceof TareaTrabajo){
                pr.setInt(6, 2);
            }

        }  
    }

    /**
     * Metodo que sirve para sacar todas las tareas existentes en nuestra base de datos
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void obtenerTareas()  throws ClassNotFoundException, SQLException{
        


    }

    /**
     * metodo que sirve para modificar registros ya guardados en nuestra base de datos
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void modificarTareas()  throws ClassNotFoundException, SQLException{
        

        
    }

    private String devolverSioNo(Boolean booleano){
        if(booleano) return "Si";

        if(!booleano) return "No";

        return null;
    }
    
}
