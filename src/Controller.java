//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class Controller {
    public Button loginBtn;
    public Pane proBtn;
    public Pane cliBtn;
    public Pane mantBtn;
    public Pane venBtn;
    public Pane userBtn;
    public Pane exitBtn;
    public Label logErrorLbl;

    //Fields de login
    public PasswordField passFld;
    public TextField userTxtF;

    public Label logoLblH;
    public Label logoLbl;

    public Controller() {
    }
    public void changeSceneH() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
        Stage window = (Stage) loginBtn.getScene().getWindow();
        window.setScene(new Scene(root, 1280.0D, 720.0D));
    }
    public void changeSceneP(MouseEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Productos.fxml")));
        Stage window = (Stage) proBtn.getScene().getWindow();
        window.setScene(new Scene(root, 1280.0D, 720.0D));
    }
    public void changeSceneM(MouseEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Mantenimientos.fxml")));
        Stage window = (Stage) mantBtn.getScene().getWindow();
        window.setScene(new Scene(root, 1280.0D, 720.0D));
    }
    public void changeSceneV(MouseEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Ventas.fxml")));
        Stage window = (Stage) venBtn.getScene().getWindow();
        window.setScene(new Scene(root, 1280.0D, 720.0D));
    }
    public void changeSceneC(MouseEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Clientes.fxml")));
        Stage window = (Stage) cliBtn.getScene().getWindow();
        window.setScene(new Scene(root, 1280.0D, 720.0D));
    }
    public void changeSceneU(MouseEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UsuariosBD.fxml")));
        Stage window = (Stage) userBtn.getScene().getWindow();
        window.setScene(new Scene(root, 1280.0D, 720.0D));
    }

    public void exit(MouseEvent actionEvent) throws IOException {
        System.exit(0);
    }


    public void enviaLogin(MouseEvent actionEvent) throws IOException {
        logErrorLbl.setVisible(false);
        String connectionUrl =
                "jdbc:sqlserver://localhost:1433;"
                        + "database=CriptoNet;"
                        + "user="+userTxtF.getText()+";"
                        + "password="+passFld.getText()+";"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            changeSceneH();
        }
        catch (SQLException ex){
            logErrorLbl.setVisible(true);
            ex.printStackTrace();
        }

    }

    public void showLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(true);
    }
    public void hideLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(false);
    }

}