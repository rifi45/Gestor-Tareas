
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



