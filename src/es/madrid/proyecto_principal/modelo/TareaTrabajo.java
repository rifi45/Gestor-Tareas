package es.madrid.proyecto_principal.modelo;

/**
 * Extiende de la clase Tarea y implementa su metodo abstracto
 * define una Tarea de trabajo
 */

public class TareaTrabajo extends Tarea{

    public TareaTrabajo(String nombre, String descripcion, String fechaLimite, int prioridad) {
        super(nombre, descripcion, fechaLimite, prioridad);
    }

    @Override
    public void mostrarDetalles() {
    }
    
}
