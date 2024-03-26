package es.madrid.proyecto_principal.modelo;

import java.sql.SQLException;
import java.util.Stack;

import es.madrid.proyecto_principal.modelo.dao_bbdd.DAOBaseDatos;
import es.madrid.proyecto_principal.modelo.imprimir_fichero.ImprimirFichero;

/**
 * Clase GestorTareas la clase que sirve para gestionar nuestras tareas.
 * @author Mohamed Afallah
 * @version 1.0
 */

public class GestorTareas {
    private Stack<Tarea> tareas;
    private Stack<Tarea> tareasRealizadas;

    //Constructor el cual usamos para iniciar nuestras pilas.
    public GestorTareas(){
        this.tareas = new Stack<>();
        this.tareasRealizadas = new Stack<>();
        try {
            this.tareas = obtenerTareasDeBBDD();
            this.tareasRealizadas = obtenerTareasRealizadasDeBBDD();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    /**
     * añadimos una tarea a nuestras tareas, y las añadimos a la base de datos igual
     * @param tarea
     * @return si se añadio o no correctamente
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean anadirTarea(Tarea tarea) throws ClassNotFoundException, SQLException{
        boolean aniadidoPerfectamente = false;
        DAOBaseDatos db = new DAOBaseDatos();

        //comprobamos que las tareas no tenga mismos nombres da igual que la tarea este realizada o no.
        if(!comprobarNombreUnico(tarea.getNombre()) && !comprobarNombreUnicoTareasRealizadas(tarea.getNombre())){
            this.tareas.add(tarea);
            db.insertarTareas(tarea);
            aniadidoPerfectamente = true;
        }

        return aniadidoPerfectamente;
    }

    /**
     * metodo que sirve para eliminar una tarea, sin realizarla se elimina directamente
     * @param tarea
     * @return si se elimino correctamente o no
     * @throws ClassNotFoundException
     * @throws SQLException
     */
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

    /**
     * metodo que sirve para ver las tareas realizadas
     * @return si se imprimio correctamente o no
     */
    public boolean verTareasRealizadas(){
        boolean seImprimio = ImprimirFichero.escribe(this.tareasRealizadas);
        return seImprimio;
    }

    /**
     * metodo que sirve para eliminar la tarea de arriba del todo y se añade a las tareas realizadas
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void ejecutarTarea() throws ClassNotFoundException, SQLException{
        DAOBaseDatos db = new DAOBaseDatos();
        this.tareas.get(0).setRealizada(true);
        db.modificarTareas(tareas.get(0));
        this.tareasRealizadas.add(this.tareas.get(0));
        this.tareas.remove(0);
    }

    //metodo que sirve para ordenar la pila
    public void ordenarPila(){
        this.tareas.sort((t1, t2) -> Integer.compare(t1.getPrioridad(), t2.getPrioridad()));
    }

    public Stack<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(Stack<Tarea> tareas) {
        this.tareas = tareas;
    }

    public Stack<Tarea> getTareasRealizadas() {
        return tareasRealizadas;
    }

    public void setTareasRealizadas(Stack<Tarea> tareasRealizadas) {
        this.tareasRealizadas = tareasRealizadas;
    }

    

    // Clases de comprobacion necesarias y privadas

    /**
     * comprobar si el nombre es unico en las tareas pendientes
     * @param nombre
     * @return devuelve si existe o no
     */
    private boolean comprobarNombreUnico(String nombre){
        boolean existe = false;
        for(Tarea tarea : this.tareas){
            if(tarea.getNombre().equalsIgnoreCase(nombre)){
                existe = true;
            }
        }

        return existe;
    }

    /**
     * comprobar si el nombre es unico en las tareas realizadas
     * @param nombre
     * @return devuelve si existe o no el nombre
     */
    private boolean comprobarNombreUnicoTareasRealizadas(String nombre){
        boolean existe = false;
        for(Tarea tarea : this.tareasRealizadas){
            if(tarea.getNombre().equalsIgnoreCase(nombre)){
                existe = true;
            }
        }

        return existe;
    }

    /**
     * obtener tareas pendientes de la base de datos
     * @return una pila de tareas pendientes
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Stack<Tarea> obtenerTareasDeBBDD() throws ClassNotFoundException, SQLException{
        DAOBaseDatos db = new DAOBaseDatos();
        if(!db.obtenerTareas().isEmpty()){
            return db.obtenerTareas();
        }else{
            return null;
        }
    }

    /**
     * obtener tareas realizadas de la base de datos
     * @return una pila de tareas realizadas
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Stack<Tarea> obtenerTareasRealizadasDeBBDD() throws ClassNotFoundException, SQLException{
        DAOBaseDatos db = new DAOBaseDatos();
        if(!db.obtenerTareasRealizadas().isEmpty()){
            return db.obtenerTareasRealizadas();
        }else{
            return null;
        }
    }
}
