package es.madrid.proyecto_principal.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import es.madrid.proyecto_principal.modelo.Tarea;
import es.madrid.proyecto_principal.modelo.TareaPersonal;
import es.madrid.proyecto_principal.modelo.TareaTrabajo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Clase controlador es la clase que nos permite controlar nuestra Vista
 */

public class ControladorVistaPrincipal implements Initializable{

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnOrdenar;

    @FXML
    private TableColumn colDescripcion;

    @FXML
    private TableColumn colFechaLimite;

    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colPrioridad;

    @FXML
    private TableView<Tarea> tblTareas;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtFechaLimite;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrioridad;

    @FXML
    private TextField txtTipoTarea;

    private ObservableList<Tarea> tareas;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        tareas = FXCollections.observableArrayList();

        //Aqui vinculamos cada celda con su respectivo atributo de la clase Tarea
       this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
       this.colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
       this.colFechaLimite.setCellValueFactory(new PropertyValueFactory("fechaLimite"));
       this.colPrioridad.setCellValueFactory(new PropertyValueFactory("prioridad"));
    }

    /**
     * metodo que usamos cuando hacemos click en agregar tarea y le pasa un evento
     * este metodo se encarga de agregar tareas a la tabla
     * @param event
     */
    @FXML
    void agregarTarea(ActionEvent event) {
        Tarea tarea = null;

        try{
            //Obtenemos los datos que el usuario mete por pantalla
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            String fechaLimite = txtFechaLimite.getText();
            int prioridad = Integer.parseInt(txtPrioridad.getText());

            //Comprobar la tarea elegida es la correcta.
            if(Integer.parseInt(this.txtTipoTarea.getText()) == 1){
                tarea = new TareaPersonal(nombre, descripcion, fechaLimite, prioridad);
            }else if(Integer.parseInt(this.txtTipoTarea.getText()) == 2){
                tarea = new TareaTrabajo(nombre, descripcion, fechaLimite, prioridad);
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Se ha producido un error en el campo tipo tarea");
                alert.setContentText("No se pudo completar la operación.");
                alert.showAndWait();
            }


            
            // añadimos los datos obtenidos a la tabla de la vista
            this.tareas.add(tarea);
            this.tblTareas.setItems(tareas);
            

        }catch(NumberFormatException e){
            // controlamos que el formato de edad es el correcto
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Se ha producido un error en un campo numerico");
            alert.setContentText("No se pudo completar la operación.");
            alert.showAndWait();
        }
    }

    /**
     * metodo que usamos cuando hacemos click en eliminar tarea y le pasa un evento
     * este metodo de eliminar una tarea
     * @param event
     */
    @FXML
    void eliminarTarea(ActionEvent event) {

    }

    /**
     * metodo que usamos cuando hacemos click en ordernar tareas y le pasa un evento
     * este metodo se encarga de ordenar las tares segun deseado
     * @param event
     */
    @FXML
    void ordenarTareas(ActionEvent event) {

    }

}

