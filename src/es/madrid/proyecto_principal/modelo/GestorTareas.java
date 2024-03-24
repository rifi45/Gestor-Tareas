package es.madrid.proyecto_principal.modelo;

import java.sql.SQLException;
import java.util.Stack;

import es.madrid.proyecto_principal.modelo.dao_bbdd.DAOBaseDatos;

public class GestorTareas {
    private Stack<Tarea> tareas;

    public GestorTareas() {
        this.tareas = new Stack<>();
    }

    public boolean anadirTarea(Tarea tarea) throws ClassNotFoundException, SQLException{
        boolean aniadidoPerfectamente = false;
        DAOBaseDatos db = new DAOBaseDatos();
        this.tareas = obtenerTareasDeBBDD();
        if(!comprobarNombreUnico(tarea.getNombre())){
            this.tareas.add(tarea);
            db.insertarTareas(tarea);
            aniadidoPerfectamente = true;
        }

        return aniadidoPerfectamente;
    }

    public void eliminarTarea(Tarea tarea) throws ClassNotFoundException, SQLException{
        DAOBaseDatos db = new DAOBaseDatos();
        this.tareas.removeElement(tarea);
        db.eliminarTarea(tarea);
    }

    public Stack<Tarea> verTareasPendientes(){
        Stack<Tarea> tareasPendientes = new Stack<>();
        for(int i = 0; i < this.tareas.size(); i++){
            if(this.tareas.get(i).isRealizada() == false){
                tareasPendientes.add(this.tareas.get(i));
            }
        }
        return tareasPendientes;
    }

    public Stack<Tarea> verTareasRealizadas(){
        Stack<Tarea> TareasRealizadas = new Stack<>();
        for(int i = 0; i < this.tareas.size(); i++){
            if(this.tareas.get(i).isRealizada() == true){
                TareasRealizadas.add(this.tareas.get(i));
            }
        }
        return TareasRealizadas;
    }

    public void ejecutarTarea(){
        this.tareas.remove(0);
    }

    public void ordenarPila(){

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
        for(int i = 0; i < this.tareas.size(); i++){
            if(this.tareas.get(i).getNombre().equalsIgnoreCase(nombre)){
                existe = true;
            }
        }

        return existe;
    }
}
