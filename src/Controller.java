import javafx.collections .FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.Conexion;
import src.DecimalField;
import src.NumberTextField;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Observable;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    //Botones de paneles
    public Pane proBtn;
    public Pane cliBtn;
    public Pane mantBtn;
    public Pane venBtn;
    public Pane userBtn;
    public Pane exitBtn;
    public Button loginBtn;

    //Fields de login
    public PasswordField passFld;
    public TextField userTxtF;
    public Label logErrorLbl;

    //Logo
    public Label logoLblH;
    public Label logoLbl;

    //Conexion
    Conexion c = new Conexion();
    private Object HomeController;

    //Constructor
    public Controller() { }

    //LogIn
    public void enviaLogin(MouseEvent actionEvent) throws IOException {
        logErrorLbl.setVisible(false);
        if(c.conectar(userTxtF.getText(),passFld.getText())){
            changeSceneH(actionEvent);
        }else{
            logErrorLbl.setVisible(true);
        }
    }

    public void changeSceneH(MouseEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        loader.setController(HomeController);
        Parent home_parent = (Parent) loader.load();
        Scene home_scene = new Scene(home_parent, 1280.0D, 720.0D);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_scene);
        app_stage.show();
    }

    public void exit(MouseEvent actionEvent) throws IOException {
        System.exit(0);
    }
    public void showLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(true);
    }
    public void hideLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(false);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}