import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.Objects;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
        primaryStage.setTitle("CriptoNet - Base de datos");
        primaryStage.setScene(new Scene(root, 1280.0D, 720.0D));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
