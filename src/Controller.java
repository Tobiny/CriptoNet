//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javafx.collections .FXCollections;
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

    /*
    Fields de productos
     */

    //--------------------Menu agregar
    public TextField idProA = new TextField();
    public TextField nomProA;
    public NumberTextField cantProA;
    public DecimalField preProA;
    public Button btnProA;

    //--------------------Menu modificar
    //public ComboBox idProM;
    @FXML
    public ComboBox<String> nomProM = new ComboBox<>();
    public ComboBox<String> nomProE = new ComboBox<>();
    public TextField idProM;
    public NumberTextField cantProM;
    public DecimalField preProM;
    public Button btnProM;
    public TextField idProE;
    public NumberTextField cantProE;
    public DecimalField preProE;
    public Button btnProE;

    //Logo
    public Label logoLblH;
    public Label logoLbl;

    //Objetos Conexión
    public ResultSet resultSet = null;
    public static String connectionUrl;

    //Constructor
    public Controller()  {}

    //LogIn
    public void enviaLogin(MouseEvent actionEvent) throws IOException {
        connectionUrl = "jdbc:sqlserver://localhost:1433; database=CriptoNet; user="+userTxtF.getText()+"; password="+passFld.getText()+"; trustServerCertificate=false; loginTimeout=30;";
        logErrorLbl.setVisible(false);
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            changeSceneH();
        }
        catch (SQLException ex){
            logErrorLbl.setVisible(true);
            ex.printStackTrace();
        }

    }
    //Agregar Productos
    public void agregarProductos(MouseEvent actionEvent) throws IOException {
        try (Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            String selectSql = "INSERT INTO Productos VALUES ('"+nomProA.getText()+"', '"+preProA.getText()+" ',' "+cantProA.getText()+"');";
            statement.executeUpdate(selectSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Modificar Productos
    public void modificarProductos (MouseEvent actionEvent) throws IOException{
        try (Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();) {
            String selectSql = "UPDATE Productos SET ValorVenta = "+preProM.getText()+", Existencia = "+cantProM.getText()+" WHERE IdProducto = "+idProM.getText();
            statement.executeUpdate(selectSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void eliminarProductos(MouseEvent actionEvent) throws IOException{
        try (Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            String selectSql = "Delete Productos Where IdProducto = "+idProE.getText();
            statement.executeUpdate(selectSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public void showLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(true);
    }
    public void hideLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //CargarCombobox
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
        });
    }
}