package es.madrid.proyecto_principal.vista;

import java.net.URL;
import java.util.ResourceBundle;

import es.madrid.proyecto_principal.modelo.Tarea;
import es.madrid.proyecto_principal.modelo.TareaPersonal;
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

    private ObservableList<Tarea> tareas;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        tareas = FXCollections.observableArrayList();

       this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
       this.colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
       this.colFechaLimite.setCellValueFactory(new PropertyValueFactory("fechaLimite"));
       this.colPrioridad.setCellValueFactory(new PropertyValueFactory("prioridad"));
    }

    @FXML
    void agregarTarea(ActionEvent event) {

        try{
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            String fechaLimite = txtFechaLimite.getText();
            int prioridad = Integer.parseInt(txtPrioridad.getText());

            Tarea tarea = new TareaPersonal(nombre, descripcion, fechaLimite, prioridad);

            if(!this.tareas.contains(tarea)){

                this.tareas.add(tarea);
                this.tblTareas.setItems(tareas);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("LA TREA YA EXISTE");
                alert.setContentText("No se pudo completar la operación.");
                alert.showAndWait();
            }

        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Se ha producido un error en el campo edad");
            alert.setContentText("No se pudo completar la operación.");
            alert.showAndWait();
        }
    }

    @FXML
    void eliminarTarea(ActionEvent event) {

    }

    @FXML
    void ordenarTareas(ActionEvent event) {

    }

}

