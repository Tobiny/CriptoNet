//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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



    //Logo
    public Label logoLblH;
    public Label logoLbl;

    //Objetos Conexión
    public ResultSet resultSet = null;
    public static String connectionUrl;
    private Object HomeController;

    //Constructor
    public Controller() { }

    //LogIn
    public void enviaLogin(MouseEvent actionEvent) throws IOException {
        connectionUrl = "jdbc:sqlserver://187.198.140.105; database=CriptoNet; user="+userTxtF.getText()+"; password="+passFld.getText()+"; trustServerCertificate=false; loginTimeout=30;";
        logErrorLbl.setVisible(false);
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            changeSceneH(actionEvent);
        }
        catch (SQLException ex){
            logErrorLbl.setVisible(true);
            ex.printStackTrace();
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
        /*CargarCombobox
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT NomProd FROM Productos");
            while (resultSet.next()){
                nomProM.getItems().addAll(resultSet.getString("NomProd"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //ComboDinamica
        nomProM.setOnAction(event ->{
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();){
                resultSet = statement.executeQuery("SELECT * FROM  Productos WHERE NomProd = '"+nomProM.getValue()+"'");
                while (resultSet.next()){
                    idProM.setText(resultSet.getString("IdProducto"));
                    preProM.setText(resultSet.getString("ValorVenta"));
                    cantProM.setText(resultSet.getString("Existencia"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });

        //CargarComboboxE
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT NomProd FROM Productos");
            while (resultSet.next()){
                nomProE.getItems().addAll(resultSet.getString("NomProd"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //ComboDinamicaE
        nomProE.setOnAction(event ->{
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();){
                resultSet = statement.executeQuery("SELECT * FROM  Productos WHERE NomProd = '"+nomProE.getValue()+"'");
                while (resultSet.next()){
                    idProE.setText(resultSet.getString("IdProducto"));
                    preProE.setText(resultSet.getString("ValorVenta"));
                    cantProE.setText(resultSet.getString("Existencia"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });*/
    }
}