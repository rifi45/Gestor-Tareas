package es.madrid.proyecto_principal.modelo;

public class TareaTrabajo extends Tarea{

    public TareaTrabajo(String nombre, String descripcion, String fechaLimite, int prioridad) {
        super(nombre, descripcion, fechaLimite, prioridad);
    }

    @Override
    public void mostrarDetalles() {
    }
    
}
