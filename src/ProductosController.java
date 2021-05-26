import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import src.Conexion;
import src.DecimalField;
import src.NumberTextField;
import java.sql.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class ProductosController implements Initializable {

    private Object ProductosController;
    private Object VentasController;
    private Object MantenimientosController;
    private Object ClientesController;
    private Object EmpleadosController;
    private Object ServiciosController;

    public ProductosController(){}

    //Logo
    public Label logoLblH;
    public Label logoLbl;
    //Fields de productos
    //--------------------Menu agregar
    public TextField nomProA;
    public NumberTextField cantProA;
    public DecimalField preProA;
    public Button btnProA;
    //--------------------Menu modificar
    @FXML
    public ComboBox<String> nomProM;
    public TextField idProM;
    public NumberTextField cantProM;
    public DecimalField preProM;
    public Button btnProM;
    //--------------------Menu eliminar
    public ComboBox<String> nomProE;
    public TextField idProE;
    public NumberTextField cantProE;
    public DecimalField preProE;
    public Button btnProE;
    //Conexion
    static Conexion c = new Conexion();
    public static String connectionUrl = c.getConnectionUrl();
    public ResultSet resultSet;
    public void changeSceneP(MouseEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Productos.fxml"));
        loader.setController(ProductosController);
        Parent home_parent = (Parent) loader.load();
        Scene home_scene = new Scene(home_parent, 1280.0D, 720.0D);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_scene);
        app_stage.show();
    }
    public void changeSceneM(MouseEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Mantenimientos.fxml"));
        loader.setController(MantenimientosController);
        Parent home_parent = (Parent) loader.load();
        Scene home_scene = new Scene(home_parent, 1280.0D, 720.0D);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_scene);
        app_stage.show();
    }
    public void changeSceneV(MouseEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Ventas.fxml"));
        loader.setController(VentasController);
        Parent home_parent = (Parent) loader.load();
        Scene home_scene = new Scene(home_parent, 1280.0D, 720.0D);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_scene);
        app_stage.show();
    }
    public void changeSceneC(MouseEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Clientes.fxml"));
        loader.setController(ClientesController);
        Parent home_parent = (Parent) loader.load();
        Scene home_scene = new Scene(home_parent, 1280.0D, 720.0D);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_scene);
        app_stage.show();
    }
    public void changeSceneE(MouseEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Empleados.fxml"));
        loader.setController(EmpleadosController);
        Parent home_parent = (Parent) loader.load();
        Scene home_scene = new Scene(home_parent, 1280.0D, 720.0D);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_scene);
        app_stage.show();
    }
    public void changeSceneS(MouseEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Servicios.fxml"));
        loader.setController(ServiciosController);
        Parent home_parent = (Parent) loader.load();
        Scene home_scene = new Scene(home_parent, 1280.0D, 720.0D);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_scene);
        app_stage.show();
    }

    //Agregar Productos
   public void agregarProductos(MouseEvent actionEvent) throws IOException {
        String agregarSql = "INSERT INTO Productos VALUES ('"+nomProA.getText()+"', '"+preProA.getText()+" ',' "+cantProA.getText()+"');";
        if (c.ejecutarQuery(agregarSql)){
            JOptionPane.showMessageDialog(null, "El producto ha sido agregado", "Agregar productos", JOptionPane.INFORMATION_MESSAGE);
            updates();
        }else{
            JOptionPane.showMessageDialog(null, "El producto no sufrio modificaciones", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    //Modificar Productos
    public void modificarProductos (MouseEvent actionEvent) throws IOException{
        String modSql = "UPDATE Productos SET ValorVenta = "+preProM.getText()+", Existencia = "+cantProM.getText()+" WHERE IdProducto = "+idProM.getText();
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea modificar este producto?", "Modificar productos - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(modSql)){
                JOptionPane.showMessageDialog(null, "El producto ha sido modificado", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El producto no sufrio modificaciones", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El producto no sufrio modificaciones", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
    }

    public void eliminarProductos(MouseEvent actionEvent) throws IOException{
        String delSql = "Delete Productos Where IdProducto = "+idProE.getText();
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar este producto?", "Eliminar productos - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(delSql)){
                JOptionPane.showMessageDialog(null, "El producto ha sido eliminado", "Eliminar productos", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El producto no fue eliminado", "Eliminar productos", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El producto no fue eliminado", "Eliminar productos", JOptionPane.INFORMATION_MESSAGE);
    }
    public void exit(MouseEvent actionEvent) throws IOException { System.exit(0); }
    public void showLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(true);
    }
    public void hideLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         updates();
    }
    public void updates(){
        nomProE.getItems().clear();
        nomProM.getItems().clear();
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

        cantProA.setText("");
        nomProA.setText("");
        preProA.setText("");

        idProM.setText("");
        cantProM.setText("");
        preProM.setText("");

        idProE.setText("");
        cantProE.setText("");
        preProE.setText("");
    }
}
