package es.madrid.proyecto_principal.modelo;

/**
 * Extiende de la clase Tarea y implementa sus metodos
 * define una tarea profesional
 */

public class TareaPersonal extends Tarea{

    public TareaPersonal(String nombre, String descripcion, String fechaLimite, int prioridad) {
        super(nombre, descripcion, fechaLimite, prioridad);
    }

    @Override
    public void mostrarDetalles() {
        
    }
    
}
