import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import src.Cliente;
import src.Conexion;
import src.Empleado;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmpleadosController implements Initializable {

    private Object ProductosController;
    private Object VentasController;
    private Object MantenimientosController;
    private Object ClientesController;
    private Object EmpleadosController;
    private Object ServiciosController;

    public Label logoLblH;
    public Label logoLbl;

    public TextField idEmpA;
    public TextField nomEmpA;

    public ComboBox<String> idEmpM;
    public TextField nomEmpM;

    public ComboBox<String> idEmpE;
    public TextField nomEmpE;


    public ComboBox<String> idEmpI;
    public TextField nomEmpI;

    public Button btnEmpA;
    public Button btnEmpM;
    public Button btnEmpE;

    public TableView<Empleado> empTable;
    public TableColumn<Empleado, String> idEmpTable;
    public TableColumn<Empleado, String> nomEmpTable;

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
    public void agregarEmpleado(MouseEvent actionEvent) throws IOException {
        String sql = "INSERT INTO Empleados VALUES ('"+nomEmpA.getText()+"');";
        if (c.ejecutarQuery(sql)){
            JOptionPane.showMessageDialog(null, "El Empleado ha sido agregado", "Agregar Empleados", JOptionPane.INFORMATION_MESSAGE);
            updates();
        }else{
            JOptionPane.showMessageDialog(null, "El Empleado no se agregó", "Agregar Empleados", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void modificarEmpleado(MouseEvent actionEvent) throws IOException {
        String agregarSql = "UPDATE Empleados SET NomEmpleado = '"+nomEmpM.getText()+"' WHERE IdEmpleado = "+getId(idEmpM.getValue());
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea modificar este empleado?", "Modificar empleados - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(agregarSql)){
                JOptionPane.showMessageDialog(null, "El empleado ha sido modificado", "Modificar empleados", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El empleado no ha sido modificado", "Modificar empleados", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El empleado no ha sido modificado", "Modificar empleados", JOptionPane.INFORMATION_MESSAGE);
    }
    public void eliminarEmpleado(MouseEvent actionEvent) throws IOException {
        String agregarSql = "DELETE FROM Empleados WHERE IdEmpleado = "+getId(idEmpE.getValue());
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar este empleado?", "Eliminar empleados - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(agregarSql)){
                JOptionPane.showMessageDialog(null, "El empleado ha sido eliminado", "Eliminar empleados", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El empleado no ha sido eliminado", "Eliminar empleados", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El empleado no ha sido eliminado", "Eliminar empleados", JOptionPane.INFORMATION_MESSAGE);
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
        idEmpM.getItems().clear();
        idEmpE.getItems().clear();
        idEmpI.getItems().clear();
        //IdAgregar
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT TOP 1 IdEmpleado FROM Empleados ORDER BY IdEmpleado DESC");
            while (resultSet.next()){
                int id = resultSet.getInt("IdEmpleado")+1;
                idEmpA.setText(Integer.toString(id));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //Combobox empleados
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT IdEmpleado, NomEmpleado FROM Empleados");
            while (resultSet.next()){
                idEmpM.getItems().addAll(resultSet.getString("IdEmpleado")+"- "+resultSet.getString("NomEmpleado"));
                idEmpE.getItems().addAll(resultSet.getString("IdEmpleado")+"- "+resultSet.getString("NomEmpleado"));
                idEmpI.getItems().addAll(resultSet.getString("IdEmpleado")+"- "+resultSet.getString("NomEmpleado"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        idEmpM.setOnAction(event -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();){
                resultSet = statement.executeQuery("SELECT * FROM Empleados WHERE IdEmpleado = '"+getId(idEmpM.getValue())+"'");
                while (resultSet.next()){
                    nomEmpM.setText(resultSet.getString("NomEmpleado"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        idEmpE.setOnAction(event -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();){
                resultSet = statement.executeQuery("SELECT * FROM Empleados WHERE IdEmpleado = '"+getId(idEmpE.getValue())+"'");
                while (resultSet.next()){
                    nomEmpE.setText(resultSet.getString("NomEmpleado"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        idEmpI.setOnAction(event -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();){
                resultSet = statement.executeQuery("SELECT * FROM Empleados WHERE IdEmpleado = '"+getId(idEmpI.getValue())+"'");
                while (resultSet.next()){
                    nomEmpI.setText(resultSet.getString("NomEmpleado"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        ObservableList<Empleado> datosTablaEmp = FXCollections.observableArrayList();
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT * FROM Empleados");
            while (resultSet.next()){
                datosTablaEmp.add(new Empleado(resultSet.getString("IdEmpleado"), resultSet.getString("NomEmpleado")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        idEmpTable.setCellValueFactory(new PropertyValueFactory<Empleado, String>("idE"));
        nomEmpTable.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nom"));
        empTable.setItems(datosTablaEmp);
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
}
