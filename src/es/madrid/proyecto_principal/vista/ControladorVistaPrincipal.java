package es.madrid.proyecto_principal.vista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControladorVistaPrincipal {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnOrdenar;

    @FXML
    private TableColumn<?, ?> colDescripcion;

    @FXML
    private TableColumn<?, ?> colFechaLimite;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colPrioridad;

    @FXML
    private TableView<?> tblTareas;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtFechaLimite;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrioridad;

    @FXML
    void agregarTarea(ActionEvent event) {

    }

    @FXML
    void eliminarTarea(ActionEvent event) {

    }

    @FXML
    void ordenarTareas(ActionEvent event) {

    }

}

