import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import src.Conexion;
import src.DecimalField;
import src.NumberTextField;
import src.Producto;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ClientesController implements Initializable {

    //Cambiar de vistas
    private Object ProductosController;
    private Object VentasController;
    private Object MantenimientosController;
    private Object ClientesController;
    private Object EmpleadosController;
    private Object ServiciosController;

    //Logo
    public Label logoLblH;
    public Label logoLbl;

    //Fields de clientes
    //--------------------Menu agregar
    public TextField idCA;
    public TextField nomA;
    public TextArea domA;
    public TextField rfcA;
    public ComboBox<String> tmA;
    public ComboBox<String> idEA;
    public Button btnA;
    //--------------------Menu modificar
    @FXML
    public ComboBox<String> idCM;
    public TextField nomM;
    public TextArea domM;
    public TextField rfcM;
    public ComboBox<String> tmM;
    public ComboBox<String> idEM;
    public Button btnM;
    //--------------------Menu eliminar
    public ComboBox<String> idCE;
    public TextField nomE;
    public TextArea domE;
    public TextField rfcE;
    public ComboBox<String> tmE;
    public ComboBox<String> idEE;
    public Button btnE;
    //--------------------Menu consultar

    public TableColumn<Producto, String> idCTable;
    public TableColumn<Producto, String> idTTable;
    public TableColumn<Producto, String> nomTable;
    public TableColumn<Producto, String> domTable;
    public TableColumn<Producto, String> rfcTable;
    public TableColumn<Producto, String> tipoMTable;
    public TableView<Producto> proTable;

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

    //Agregar Cliente
    public void agregarCliente(MouseEvent actionEvent) throws IOException {
       /* String agregarSql = "INSERT INTO Productos VALUES ('"+nomProA.getText()+"', '"+preProA.getText()+" ',' "+cantProA.getText()+"');";
        if (c.ejecutarQuery(agregarSql)){
            JOptionPane.showMessageDialog(null, "El producto ha sido agregado", "Agregar productos", JOptionPane.INFORMATION_MESSAGE);
            updates();
        }else{
            JOptionPane.showMessageDialog(null, "El producto no sufrio modificaciones", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
        }*/

    }
    //Modificar Cliente
    public void modificarCliente (MouseEvent actionEvent) throws IOException{
        /*String modSql = "UPDATE Productos SET ValorVenta = "+preProM.getText()+", Existencia = "+cantProM.getText()+" WHERE IdProducto = "+idProM.getText();
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea modificar este producto?", "Modificar productos - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(modSql)){
                JOptionPane.showMessageDialog(null, "El producto ha sido modificado", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El producto no sufrio modificaciones", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El producto no sufrio modificaciones", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);*/
    }
    //Modificar Cliente
    public void eliminarClientes(MouseEvent actionEvent) throws IOException{
       /* String delSql = "Delete Productos Where IdProducto = "+idProE.getText();
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar este producto?", "Eliminar productos - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(delSql)){
                JOptionPane.showMessageDialog(null, "El producto ha sido eliminado", "Eliminar productos", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El producto no fue eliminado", "Eliminar productos", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El producto no fue eliminado", "Eliminar productos", JOptionPane.INFORMATION_MESSAGE);*/
    }


    public void exit(MouseEvent actionEvent) throws IOException { System.exit(0); }
    public void showLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(true);
    }
    public void hideLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(false);
    }

    //@Override
    public void initialize(URL location, ResourceBundle resources) {
        domA.setWrapText(true);
        //updates();
    }
   /* public void updates(){
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

        //Tabla de consulta --- esto si quieres yo lo hago
        //proTable.getColumns().addAll(idProTable, nomProTable, costProTable, uniProTable);
        ObservableList<Producto> datosTablaPro = FXCollections.observableArrayList();
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT * FROM Productos");
            while (resultSet.next()){
                datosTablaPro.add(new Cliente(id cliente, id empleado, nombre cliente, domicio, rfc, tipo minero));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        idProTable.setCellValueFactory(new PropertyValueFactory<Producto, String>("id"));
        nomProTable.setCellValueFactory(new PropertyValueFactory<Producto, String>("nom"));
        costProTable.setCellValueFactory(new PropertyValueFactory<Producto, String>("costo"));
        uniProTable.setCellValueFactory(new PropertyValueFactory<Producto, String>("unidad"));
        proTable.setItems(datosTablaPro);


    }*/
}
