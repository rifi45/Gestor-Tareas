package es.madrid.proyecto_principal.modelo;

public abstract class Tarea {
    private String nombre;
    private String descripcion;
    private String fechaLimite;
    private int prioridad;
    private boolean realizada;

    public Tarea(String nombre, String descripcion, String fechaLimite, int prioridad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.prioridad = prioridad;
        this.realizada = false;
    }

    public abstract void mostrarDetalles();

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getFechaLimite() {
        return fechaLimite;
    }
    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    public int getPrioridad() {
        return prioridad;
    }
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    

    


}
