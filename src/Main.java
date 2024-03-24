
//Importaciones de clases para JavaFX
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import es.madrid.proyecto_principal.controlador.ControladorVistaPrincipal;


/**
 * @autor Mohamed Afallah
 * @version 1.0
 */

public class Main extends Application {

    /**
     * Sobreescribimos el metoso start de la clase Application de la cual hereda mi clase Main
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar la vista desde el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("es/madrid/proyecto_principal/vista/VistaPrincipal.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la vista
        ControladorVistaPrincipal controller = loader.getController();

        // Configurar la escena con la vista cargada
        Scene scene = new Scene(root, 800, 600);

        // Configurar la escena en el escenario principal
        primaryStage.setScene(scene);

        // Establecer el título de la ventana
        primaryStage.setTitle("Mi Aplicación JavaFX");

        // Mostrar la ventana
        primaryStage.show();
    }

    /**
     * Mi metodo principal main en la cual unicamente llamo a un metodo
     * el cual me recupera la llamada del metodo que sobreEscribo
     * @param args
     */
    public static void main(String[] args){
        launch(args);
    }
}
