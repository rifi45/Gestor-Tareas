package es.madrid.proyecto_principal.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Stack;

import es.madrid.proyecto_principal.modelo.GestorTareas;
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
import javafx.scene.input.MouseEvent;

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

    static GestorTareas gt = new GestorTareas();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        tareas = FXCollections.observableArrayList();

        //Aqui vinculamos cada celda con su respectivo atributo de la clase Tarea
       this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
       this.colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
       this.colFechaLimite.setCellValueFactory(new PropertyValueFactory("fechaLimite"));
       this.colPrioridad.setCellValueFactory(new PropertyValueFactory("prioridad"));

        try {
            iniciarBBDD();
        } catch (ClassNotFoundException | SQLException e) {
            }
    }

    /**
     * metodo que usamos cuando hacemos click en agregar tarea y le pasa un evento
     * este metodo se encarga de agregar tareas a la tabla
     * @param event
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    @FXML
    void agregarTarea(ActionEvent event) throws ClassNotFoundException, SQLException{
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

            if(comprobarFormatoFecha(fechaLimite) && fechaLimite.length() == 10){
                // añadimos los datos obtenidos a la tabla de la vista
                boolean comprobarNombreUnico = gt.anadirTarea(tarea);
                if(comprobarNombreUnico){
                    this.tareas.add(tarea);
                    this.tblTareas.setItems(tareas);
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("EL NOMBRE YA EXISTE, ELIJA OTRO");
                    alert.setContentText("ERROR EN EL NOMBRE");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("EL FORMATO DE LA FECHA ES INCORRECTO");
                alert.setContentText("SIGA EL SIGUIENTE: XX/XX/XXXX");
                alert.showAndWait();
            }           

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
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    @FXML
    void eliminarTarea(ActionEvent event) throws ClassNotFoundException, SQLException {
        Tarea t = this.tblTareas.getSelectionModel().getSelectedItem();

        if(t != null){
            this.tareas.remove(t);
            this.tblTareas.refresh();
            boolean comprobarEliminacion = gt.eliminarTarea(t);

            if(!comprobarEliminacion){
                Alert alert = new  Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("NO SE ENCOTRO A LA TAREA");
                alert.setContentText("No se pudo completar la operación.");
                alert.showAndWait();
            }
        }else{
            Alert alert = new  Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("DEBES SELECCIONAR UNA TAREA");
            alert.setContentText("No se pudo completar la operación.");
            alert.showAndWait();
        }
    }

    /**
     * metodo que usamos cuando hacemos click en ordernar tareas y le pasa un evento
     * este metodo se encarga de ordenar las tares segun deseado
     * @param event
     */
    @FXML
    void ordenarTareas(ActionEvent event) {

    }

    private boolean comprobarFormatoFecha(String fecha){
        if(fecha.charAt(2) == '/' && fecha.charAt(5) == '/'){
            return true;
        }else{
            return false;
        }

    }

    /**
     * Metodo para al seleccionar una tarea ponerla en las textfield
     * @param event
     */
    @FXML
    void seleccionar(MouseEvent event) {
        Tarea t = this.tblTareas.getSelectionModel().getSelectedItem();

        if(t != null){
            this.txtNombre.setText(t.getNombre());
            this.txtDescripcion.setText(t.getDescripcion());
            this.txtFechaLimite.setText(t.getFechaLimite());
            this.txtPrioridad.setText(t.getPrioridad() + "");
            this.txtTipoTarea.setText(((t instanceof TareaPersonal)? 1: 2) + "");
        }
    }

    private void iniciarBBDD() throws ClassNotFoundException, SQLException{
        GestorTareas gt = new GestorTareas();
        Stack<Tarea> tareas = gt.obtenerTareasDeBBDD();
        Tarea tarea = null;

        if(tareas != null){
            for(int i = 0; i < tareas.size(); i++){
                String nombre = tareas.get(i).getNombre();
                String descripcion = tareas.get(i).getDescripcion();
                String fecha = tareas.get(i).getFechaLimite();
                int prioridad = tareas.get(i).getPrioridad();
                if(tareas.get(i) instanceof TareaPersonal){
                    tarea = new TareaPersonal(nombre, descripcion, fecha, prioridad);
                    tarea.setRealizada(tareas.get(i).isRealizada());
                }else{
                    tarea = new TareaTrabajo(nombre, descripcion, fecha, prioridad);
                    tarea.setRealizada(tareas.get(i).isRealizada());
                }
                this.tareas.add(tarea);
            }
            this.tblTareas.setItems(this.tareas);
        }
    }

}

