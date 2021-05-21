//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.DecimalField;
import src.NumberTextField;

import javax.swing.*;
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

    public Button loginBtn;
    //Botones de paneles
    public Pane proBtn;
    public Pane cliBtn;
    public Pane mantBtn;
    public Pane venBtn;
    public Pane userBtn;
    public Pane exitBtn;


    //Fields de login
    public PasswordField passFld;
    public TextField userTxtF;
    public Label logErrorLbl;

    /*
    Fields de productos
     */

    //--------------------Menu agregar
    public TextField idProA;
    public TextField nomProA;
    public NumberTextField cantProA;
    public DecimalField preProA;
    public Button btnProA;

    //--------------------Menu modificar
    //public ComboBox idProM;
    @FXML
    public ComboBox<String> idProM = new ComboBox<>();
    public TextField nomProM;
    public NumberTextField cantProM;
    public DecimalField preProM;
    public Button btnProM;

    //Logo
    public Label logoLblH;
    public Label logoLbl;

    public Controller()  {
        //prueba observable list
    }
    public void agregarProductos(MouseEvent actionEvent) throws IOException {
        //La id de los productos es ideProA - ahi lo ves tú.
        nomProA.getText(); //Nombre del producto
        cantProA.getText(); //Cantidad de productos
        preProA.getText(); //Precio
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
                "jdbc:sqlserver://CriptoNet.mssql.somee.com;"
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> idProM_items = FXCollections.observableArrayList("a", "b", "c", "d");
        idProM.setItems(idProM_items);
    }
}