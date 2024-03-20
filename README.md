# Gestor de Tareas
### Un resumen de Herramientas.

Para desarrollar nuestra aplicacion, hemos usado una interfaz grafica JavaFX para que interactue con el usuario, y tambien para las bases de datos hemos utilizado SQL con oracle y para el control de versiones hemos usado git y el repositorio se encuentra en la nube [GitHub](https://github.com/rifi45/Gestor-Tareas)

### El Diseño de Nuestra Aplicacion(Orientado a java)
Para lo referente al diagrama de clases, tenemos una clase Abstracta llamada tarea de la cual heredan dos subclases y implementan el metodo abstracto mostrarDetalles().
```mermaid
classDiagram
    class GestorTareas {
        - tareas: Stack<Tarea>
        +añadirTarea(Tarea tarea) : void
        +eliminarTarea() : Tarea
        +verTareasPendientes(): Stack<Tarea>
        +verTareasRealizadas(): Stack<Tarea>
        +ejecutarTarea(): void
        +ordenarPorPrioridad(): void
    }
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
### Bases de Datos
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
- **Adjunto aqui la creacion de mis tablas en SQL y la creacion de un trigger**

```sql

-- Creacion de tablas
create table tipo_tarea(
ID_TIPO_TAREA INTEGER PRIMARY KEY,
NOMBRE_TIPO VARCHAR(255) unique
);

create table tarea(
ID_TAREA INTEGER PRIMARY KEY,
NOMBRE VARCHAR(255) unique,
DESCRIPCION VARCHAR(255),
FECHA_LIMITE DATE,
PRIORIDAD INTEGER,
REALIZADA VARCHAR(2),
ID_TIPO_TAREA INTEGER,
FOREIGN KEY (ID_TIPO_TAREA) REFERENCES tipo_tarea(ID_TIPO_TAREA)
);

create table registro_tareas_realizadas(
ID_REGISTRO INTEGER PRIMARY KEY,
FECHA_REALIZACION DATE,
ID_TAREA INTEGER,
FOREIGN KEY (ID_TAREA) REFERENCES tarea(ID_TAREA)
);

--ALTERAR LA TABLA PARA QUE NOS GUARDE LA FECHA DE HOY.
ALTER TABLE registro_tareas_realizadas
MODIFY FECHA_REALIZACION TIMESTAMP DEFAULT CURRENT_TIMESTAMP;


--Secuencias para ir incrementando el id
CREATE SEQUENCE ID_INCRE_TAREA
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE ID_REGISTRO
START WITH 1
INCREMENT BY 1;

-- insertar datos por defecto
insert into tipo_tarea values(1, 'Tarea Personal');
insert into tipo_tarea values(2, 'Tarea Trabajo');

--Creacion de un trigger para guardar las fechas y las tareas que se reaizaron

CREATE OR REPLACE TRIGGER registrar_tareas
AFTER UPDATE ON tarea
FOR EACH ROW
BEGIN
    IF:OLD.REALIZADA = 'NO' AND :NEW.REALIZADA = 'SI' THEN
    INSERT INTO registro_tareas_realizadas(ID_REGISTRO, ID_TAREA) VALUES(id_registro.NEXTVAL, :NEW.ID_TAREA);
    END IF;
END;
/
```
### Prototipo de Interfaz de Usuario(Java FX)

Solo hay una ventana en la cual hay una tabla que realiza todas las funciones necesarias de nuestra app.