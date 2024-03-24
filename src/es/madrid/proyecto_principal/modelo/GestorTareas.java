package es.madrid.proyecto_principal.modelo;

import java.sql.SQLException;
import java.util.Stack;

import es.madrid.proyecto_principal.modelo.dao_bbdd.DAOBaseDatos;

public class GestorTareas {
    private Stack<Tarea> tareas;

    public GestorTareas(){
        this.tareas = new Stack<>();
        try {
            this.tareas = obtenerTareasDeBBDD();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public boolean anadirTarea(Tarea tarea) throws ClassNotFoundException, SQLException{
        boolean aniadidoPerfectamente = false;
        DAOBaseDatos db = new DAOBaseDatos();

        if(!comprobarNombreUnico(tarea.getNombre())){
            this.tareas.add(tarea);
            db.insertarTareas(tarea);
            aniadidoPerfectamente = true;
        }

        return aniadidoPerfectamente;
    }

    public boolean eliminarTarea(Tarea tarea) throws ClassNotFoundException, SQLException{
        DAOBaseDatos db = new DAOBaseDatos();
        this.tareas.removeElement(tarea);
        int filasAfectadas = db.eliminarTarea(tarea);

        if(filasAfectadas > 0){
            return true;
        }else{
            return false;
        }
    }

    public Stack<Tarea> verTareasPendientes(){
        Stack<Tarea> tareasPendientes = new Stack<>();
        for(Tarea p : this.tareas){
            if(p.isRealizada() == false){
                tareasPendientes.add(p);
            }
        }
        return tareasPendientes;
    }

    public Stack<Tarea> verTareasRealizadas(){
        Stack<Tarea> TareasRealizadas = new Stack<>();
        for(Tarea p : this.tareas){
            if(p.isRealizada() == true){
                TareasRealizadas.add(p);
            }
        }
        return TareasRealizadas;
    }

    public void ejecutarTarea(){
        this.tareas.get(0).setRealizada(true);
        this.tareas.remove(0);
    }

    public void ordenarPila(){
        this.tareas.sort((t1, t2) -> Integer.compare(t1.getPrioridad(), t2.getPrioridad()));
        System.out.println(this.tareas.get(0).getNombre());
    }

    public Stack<Tarea> obtenerTareasDeBBDD() throws ClassNotFoundException, SQLException{
        DAOBaseDatos db = new DAOBaseDatos();
        if(!db.obtenerTareas().isEmpty()){
            return db.obtenerTareas();
        }else{
            return null;
        }
    }

    public Stack<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(Stack<Tarea> tareas) {
        this.tareas = tareas;
    }

    // Clases de comprobacion necesarias y privadas

    public boolean comprobarNombreUnico(String nombre){
        boolean existe = false;
        for(Tarea tarea : this.tareas){
            if(tarea.getNombre().equalsIgnoreCase(nombre)){
                existe = true;
            }
        }

        return existe;
    }
}
