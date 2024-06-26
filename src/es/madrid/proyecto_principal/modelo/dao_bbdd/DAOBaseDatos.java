package es.madrid.proyecto_principal.modelo.dao_bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

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
    public boolean insertarTareas(Tarea tarea) throws ClassNotFoundException, SQLException{
        boolean comprobar = false;
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
            pr.executeUpdate();
            comprobar = true;
        }  
        return comprobar;
    }

    /**
     * Metodo que sirve para sacar todas las tareas existentes en nuestra base de datos
     * @return Devuelve una pila de tareas pendientes por realizar
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Stack<Tarea> obtenerTareas()  throws ClassNotFoundException, SQLException{
        Stack<Tarea> tareas = new Stack<>();
        Tarea tarea = null;
        String sql = "Select * from TAREA where realizada = 'No'";
        cnx = ConexionBD.connect();
        try(PreparedStatement st = cnx.prepareStatement(sql);
        ResultSet rs = st.executeQuery()){
            while(rs.next()){
                String nombre = rs.getString("NOMBRE");
                String descripcion = rs.getString("DESCRIPCION");
                String fechaLimite = rs.getString("FECHA_LIMITE");
                int prioridad = rs.getInt("PRIORIDAD");
                boolean realizada = (rs.getString("REALIZADA").equalsIgnoreCase("SI"))? true : false;
                int tipoTarea = rs.getInt("ID_TIPO_TAREA");
                if(tipoTarea == 1){
                    tarea = new TareaPersonal(nombre, descripcion, fechaLimite, prioridad);
                    tarea.setRealizada(realizada);
                }else if(tipoTarea == 2){
                    tarea = new TareaTrabajo(nombre, descripcion, fechaLimite, prioridad);
                    tarea.setRealizada(realizada);
                }

                tareas.push(tarea);
            }
        }
        return tareas;
    }

    /**
     * Metodo que sirve para obtener las tareas realizasas 
     * @return devuelve una pila de tareas donde isRealizada() = true
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Stack<Tarea> obtenerTareasRealizadas()  throws ClassNotFoundException, SQLException{
        Stack<Tarea> tareas = new Stack<>();
        Tarea tarea = null;
        String sql = "Select * from TAREA where realizada = 'Si'";
        cnx = ConexionBD.connect();
        try(PreparedStatement st = cnx.prepareStatement(sql);
        ResultSet rs = st.executeQuery()){
            while(rs.next()){
                String nombre = rs.getString("NOMBRE");
                String descripcion = rs.getString("DESCRIPCION");
                String fechaLimite = rs.getString("FECHA_LIMITE");
                int prioridad = rs.getInt("PRIORIDAD");
                boolean realizada = (rs.getString("REALIZADA").equalsIgnoreCase("SI"))? true : false;
                int tipoTarea = rs.getInt("ID_TIPO_TAREA");
                if(tipoTarea == 1){
                    tarea = new TareaPersonal(nombre, descripcion, fechaLimite, prioridad);
                    tarea.setRealizada(realizada);
                }else if(tipoTarea == 2){
                    tarea = new TareaTrabajo(nombre, descripcion, fechaLimite, prioridad);
                    tarea.setRealizada(realizada);
                }

                tareas.push(tarea);
            }
        }
        return tareas;
    }

    /**
     * metodo que sirve para modificar registros ya guardados en nuestra base de datos
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void modificarTareas(Tarea tarea) throws ClassNotFoundException, SQLException{
        cnx = ConexionBD.connect();
        String sql = "UPDATE TAREA SET REALIZADA = 'Si' WHERE NOMBRE = ?";
        try(PreparedStatement pr = cnx.prepareStatement(sql)){
            pr.setString(1, tarea.getNombre());
            pr.executeUpdate();
        }
    }

    /**
     * Metodo que sirve para eliminar una tarea de la base de datos
     * @param tarea
     * @return el numero de filas afectadas
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int eliminarTarea(Tarea tarea) throws ClassNotFoundException, SQLException{
        cnx = ConexionBD.connect();
        String sql = "DELETE FROM TAREA WHERE NOMBRE = ?";
        int filasAfectadas = 0;
        
        try(PreparedStatement st = cnx.prepareStatement(sql)){
            st.setString(1, tarea.getNombre());
            filasAfectadas = st.executeUpdate();
        }

        return filasAfectadas;
    }

    /**
     * Metodo que sirve para cambiar un boolean a un String y sirve para añadir a la base de datos
     * @param booleano
     * @return un texto Si o No
     */
    private String devolverSioNo(Boolean booleano){
        if(booleano) return "Si";

        if(!booleano) return "No";

        return null;
    }
    
}
