# Gestor de Tareas
### Un resumen de Herramientas.

Para desarrollar nuestra aplicacion, hemos usado una interfaz grafica JavaFX para que interactue con el usuario, y tambien para las bases de datos hemos utilizado SQL con oracle y para el control de versiones hemos usado git y el repositorio se encuentra en la nube [GitHub](https://github.com/rifi45/Gestor-Tareas)

### El Diseño de Nuestra Aplicacion(Orientado a java)
```mermaid
classDiagram
    class Tarea {
        - nombre: String
        - Descripcion: String
        - fechaLimite: String
        - prioridad: int
        + mostrarDetalles(): void
    }

    class TareaPersonal {
       
    }

    class TareaTrabajo {
        
    }

    Tarea <|-- TareaPersonal
    Tarea <|-- TareaTrabajo


```
### Modelo Entidad Relacion(Bases de Datos)
```mermaid
erDiagram

tarea {
    int ID_TAREA
    String NOMBRE
    String DESCRIPCION
    Date FECHA_LIMITE
    int PRIORIDAD
    String REALIZADA
}

tipo_tarea {
    int id_tipo_tarea
    String tipo_tarea
}

registros_tareas_realizadas{
    int id_registros
    Date fechaRealizacion
}

tipo_tarea ||--o{ tarea : puede_ser
tarea ||--||registros_tareas_realizadas : registran
```
### Prototipo de Interfaz de Usuario(Java FX)

Solo hay una ventana en la cual hay una tabla que realiza todas las funciones necesarias de nuestra app.