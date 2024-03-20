package es.madrid.proyecto_principal.modelo;

import java.util.Stack;

public class GestorTareas {
    private Stack<Tarea> tareas;

    public GestorTareas() {
        this.tareas = new Stack<>();
    }

    public void anadirTarea(Tarea tarea){
        this.tareas.add(tarea);
    }

    public void eliminarTarea(Tarea tarea){
        this.tareas.removeElement(tarea);
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

    public Stack<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(Stack<Tarea> tareas) {
        this.tareas = tareas;
    }
}
