package es.madrid.proyecto_principal.modelo.imprimir_fichero;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Stack;

import es.madrid.proyecto_principal.modelo.Tarea;

/**
 * Clase que sirve para imprimir un fichero txt
 */

public class ImprimirFichero {

        /**
         * metodo que escribe en mi fichero las tareas que se han realizado.
         * le pasamos una pila de tareas y las guarda en otra auxiliar mientras va alterando
         * borra la tarea imprimiendo sus datos, hasta que la pila se quede sin elementos
         * @param tareas
         */
        public static boolean escribe(Stack<Tarea> tareas){
        Stack<Tarea> tareasAux = tareas;
        int numeroTareas = tareasAux.size();

        try(BufferedWriter bf = new BufferedWriter(new FileWriter("tareasRelizadas.txt", true))){
            while(!tareasAux.isEmpty()){
                Tarea tarea = tareasAux.pop();
                if(tarea.isRealizada()){
                    bf.write("Nombre: " + tarea.getNombre() + "\nDescripcion: " + tarea.getDescripcion() + "\nPrioridad: " + tarea.getPrioridad() + "\nFecha Limite: " + tarea.getFechaLimite());
                    bf.write("\n-----------------------------------------------------------------------");
                    bf.newLine();
                    
                }
            }
            return true;
        }catch(IOException e){
            System.out.println("ERROR ESCRITURA FICHERO");
            return false;
        }
    }
}
