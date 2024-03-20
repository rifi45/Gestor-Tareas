

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import es.madrid.proyecto_principal.vista.*;

public class Main extends Application {

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

    public static void main(String[] args) {
        System.out.println("MOHAAAA");
        launch(args);
    }
}
