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
import src.*;

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
    //--------------------Menu modificar
    @FXML
    public ComboBox<String> idCC;
    public TextField nomC;
    public TextArea domC;
    public TextField rfcC;
    public ComboBox<String> tmC;
    public ComboBox<String> idEC;
    public Button btnC;
    //--------------------Tabla
    public TableColumn<Cliente, String> idCTable;
    public TableColumn<Cliente, String> idTTable;
    public TableColumn<Cliente, String> nomTable;
    public TableColumn<Cliente, String> domTable;
    public TableColumn<Cliente, String> rfcTable;
    public TableColumn<Cliente, String> tipoMTable;
    public TableView<Cliente> clienteTable;

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
        String agregarSql = "INSERT INTO Clientes VALUES ('"+nomA.getText()+"','"+domA.getText()+"', '"+rfcA.getText()+"', '"+getId(idEA.getValue())+"', '"+tmA.getValue()+"');";
        if (c.ejecutarQuery(agregarSql)){
            JOptionPane.showMessageDialog(null, "El Cliente ha sido agregado", "Agregar productos", JOptionPane.INFORMATION_MESSAGE);
            updates();
        }else{
            JOptionPane.showMessageDialog(null, "El Cliente no sufrio modificaciones", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //Modificar Cliente
    public void modificarCliente(MouseEvent actionEvent) throws IOException{
        String agregarSql = "UPDATE Clientes Set NomCliente = '"+nomM.getText()+"', DomCliente = '"+domM.getText()+"', RFC_Cliente ='"+rfcM.getText()
                +"', IdEmpleado = "+getId(idEM.getValue())+", TipoMinero = '"+tmM.getValue()+"' WHERE IdCliente ="+getId(idCM.getValue());
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea modificar este cliente?", "Modificar clientes - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(agregarSql)){
                JOptionPane.showMessageDialog(null, "El Cliente ha sido modificado", "Modificar clientes", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El Cliente no sufrio modificaciones", "Modificar clientes", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El Cliente no sufrio modificaciones", "Modificar clientes", JOptionPane.INFORMATION_MESSAGE);
    }
    //Eliminar Cliente
    public void eliminarCliente(MouseEvent actionEvent) throws IOException{
       String delSql = "Delete Clientes WHERE IdCliente ="+getId(idCE.getValue());
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar este producto?", "Eliminar productos - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(delSql)){
                JOptionPane.showMessageDialog(null, "El Cliente ha sido eliminado", "Eliminar productos", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El Cliente no fue eliminado", "Eliminar productos", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El Cliente no fue eliminado", "Eliminar productos", JOptionPane.INFORMATION_MESSAGE);
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
        domA.setWrapText(true);
        updates();
    }
    //Get id de un combobox
   public String getId(String textoEnCombo){
       String idnull = "1";
       if(textoEnCombo == null) return idnull;
       else{
           String[] parts = textoEnCombo.split("-" );
           return parts[0];
       }
   }
   //Updates
   public void updates(){
       idCE.getItems().clear();
       idCC.getItems().clear();
       idCM.getItems().clear();
       idEA.getItems().clear();
       tmA.getItems().clear();
       tmM.getItems().clear();
       tmE.getItems().clear();
       tmC.getItems().clear();
       //IdAgregarProductos
       try(Connection connection = DriverManager.getConnection(connectionUrl);
           Statement statement = connection.createStatement();){
           resultSet = statement.executeQuery("SELECT TOP 1 IdCliente FROM Clientes ORDER BY IdCliente DESC");
           while (resultSet.next()){
               int id = resultSet.getInt("IdCliente")+1;
               idCA.setText(Integer.toString(id));
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       String[] tipoMinero = {"Eth","SHA","ZC", "N/A"};
       //Cargar Combobox de Algos
       for(int x = 0; x < tipoMinero.length; x++){
           tmA.getItems().add(tipoMinero[x]);
           tmM.getItems().add(tipoMinero[x]);
           tmE.getItems().add(tipoMinero[x]);
           tmC.getItems().add(tipoMinero[x]);
       }
       //Cargar Combobox de Empleados
       try(Connection connection = DriverManager.getConnection(connectionUrl);
           Statement statement = connection.createStatement();){
           resultSet = statement.executeQuery("SELECT IdEmpleado, NomEmpleado FROM Empleados");
           while (resultSet.next()){
               idEA.getItems().addAll(resultSet.getString("IdEmpleado")+"- "+resultSet.getString("NomEmpleado"));
               idEM.getItems().addAll(resultSet.getString("IdEmpleado")+"- "+resultSet.getString("NomEmpleado"));
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       //CargarComboboxM
       try(Connection connection = DriverManager.getConnection(connectionUrl);
           Statement statement = connection.createStatement();){
           resultSet = statement.executeQuery("SELECT IdCliente, NomCliente FROM Clientes");
           while (resultSet.next()){
               idCM.getItems().addAll(resultSet.getString("IdCliente")+"- "+resultSet.getString("NomCliente"));
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       //ComboDinamicaM
       idCM.setOnAction(event ->{
           try(Connection connection = DriverManager.getConnection(connectionUrl);
               Statement statement = connection.createStatement();){
               resultSet = statement.executeQuery("SELECT * FROM  Clientes WHERE IdCliente = '"+getId(idCM.getValue())+"'");
               while (resultSet.next()){
                   idEM.setValue(resultSet.getString("IdEmpleado"));
                   nomM.setText(resultSet.getString("NomCliente"));
                   domM.setText(resultSet.getString("DomCliente"));
                   rfcM.setText(resultSet.getString("RFC_Cliente"));
                   tmM.setValue(resultSet.getString("TipoMinero"));
               }
           }catch (SQLException e){
               e.printStackTrace();
           }
       });
       //CargarComboboxE
       try(Connection connection = DriverManager.getConnection(connectionUrl);
           Statement statement = connection.createStatement();){
           resultSet = statement.executeQuery("SELECT IdCliente, NomCliente FROM Clientes");
           while (resultSet.next()){
               idCE.getItems().addAll(resultSet.getString("IdCliente")+"- "+resultSet.getString("NomCliente"));
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       //ComboDinamicaE
       idCE.setOnAction(event ->{
           try(Connection connection = DriverManager.getConnection(connectionUrl);
               Statement statement = connection.createStatement();){
               resultSet = statement.executeQuery("SELECT * FROM  Clientes WHERE IdCliente = '"+getId(idCE.getValue())+"'");
               while (resultSet.next()){
                   idEE.setValue(resultSet.getString("IdEmpleado"));
                   nomE.setText(resultSet.getString("NomCliente"));
                   domE.setText(resultSet.getString("DomCliente"));
                   rfcE.setText(resultSet.getString("RFC_Cliente"));
                   tmE.setValue(resultSet.getString("TipoMinero"));
               }
           }catch (SQLException e){
               e.printStackTrace();
           }
       });
       //CargarComboboxC
       try(Connection connection = DriverManager.getConnection(connectionUrl);
           Statement statement = connection.createStatement();){
           resultSet = statement.executeQuery("SELECT IdCliente, NomCliente FROM Clientes");
           while (resultSet.next()){
               idCC.getItems().addAll(resultSet.getString("IdCliente")+"- "+resultSet.getString("NomCliente"));
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       //ComboDinamicaC
       idCC.setOnAction(event ->{
           try(Connection connection = DriverManager.getConnection(connectionUrl);
               Statement statement = connection.createStatement();){
               resultSet = statement.executeQuery("SELECT * FROM  Clientes WHERE IdCliente = '"+getId(idCC.getValue())+"'");
               while (resultSet.next()){
                   idEC.setValue(resultSet.getString("IdEmpleado"));
                   nomC.setText(resultSet.getString("NomCliente"));
                   domC.setText(resultSet.getString("DomCliente"));
                   rfcC.setText(resultSet.getString("RFC_Cliente"));
                   tmC.setValue(resultSet.getString("TipoMinero"));
               }
           }catch (SQLException e){
               e.printStackTrace();
           }
       });

       //Tabla de consulta
       //proTable.getColumns().addAll(idProTable, nomProTable, costProTable, uniProTable);
       ObservableList<Cliente> datosTablaPro = FXCollections.observableArrayList();
       try(Connection connection = DriverManager.getConnection(connectionUrl);
           Statement statement = connection.createStatement();){
           resultSet = statement.executeQuery("SELECT * FROM Clientes");
           while (resultSet.next()){
               datosTablaPro.add(new Cliente(resultSet.getString("IdCliente"), resultSet.getString("IdEmpleado"), resultSet.getString("NomCliente"), resultSet.getString("DomCliente"), resultSet.getString("RFC_Cliente"), resultSet.getString("TipoMinero")));
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       idCTable.setCellValueFactory(new PropertyValueFactory<Cliente, String>("idC"));
       idTTable.setCellValueFactory(new PropertyValueFactory<Cliente, String>("idE"));
       nomTable.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nom"));
       domTable.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dom"));
       rfcTable.setCellValueFactory(new PropertyValueFactory<Cliente, String>("rfc"));
       tipoMTable.setCellValueFactory(new PropertyValueFactory<Cliente, String>("tipoM"));
       clienteTable.setItems(datosTablaPro);
   }
}
