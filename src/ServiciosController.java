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
import src.Conexion;
import src.DecimalField;
import src.Empleado;
import src.Servicio;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ServiciosController implements Initializable {

    private Object ProductosController;
    private Object VentasController;
    private Object MantenimientosController;
    private Object ClientesController;
    private Object EmpleadosController;
    private Object ServiciosController;

    public Label logoLblH;
    public Label logoLbl;

    //Menú agregar
    public TextField idServA;
    public TextField nomServA;
    public DecimalField preServA;
    public Button btnServA;

    //Menú modificar
    public ComboBox<String> idServM;
    public TextField nomServM;
    public DecimalField preServM;
    public Button btnServM;

    //Menú eliminar
    public ComboBox<String> idServE;
    public TextField nomServE;
    public TextField preServE;
    public Button btnServE;

    //Menu consulta general
    public TableView<Servicio> servTable;
    public TableColumn<Servicio, String> idSerTable;
    public TableColumn<Servicio, String> nomSerTable;
    public TableColumn<Servicio, String> preSerTable;

    //Menu consulta idividual
    public ComboBox<String> idServI;
    public TextField nomServI;
    public TextField preServI;
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
    public void agregarServicio(){
        String sql = "INSERT INTO Servicios VALUES ('"+nomServA.getText()+"', '"+preServA.getText()+"');";
        if (c.ejecutarQuery(sql)){
            JOptionPane.showMessageDialog(null, "El Servicio ha sido agregado", "Agregar Servicios", JOptionPane.INFORMATION_MESSAGE);
            updates();
        }else{
            JOptionPane.showMessageDialog(null, "El Servicio no se agregó", "Agregar Servicios", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void modificarServicio(){
        String agregarSql = "UPDATE Servicios SET NombreServicio = '"+nomServM.getText()+"', PrecioServicio = '"+preServM.getText()+"' WHERE IdServicio = "+getId(idServM.getValue());
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea modificar este Servicio?", "Modificar Servicios - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(agregarSql)){
                JOptionPane.showMessageDialog(null, "El Servicio ha sido modificado", "Modificar Servicios", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El Servicio no ha sido modificado", "Modificar Servicio", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El Servicio no ha sido modificado", "Modificar Servicio", JOptionPane.INFORMATION_MESSAGE);
    }
    public void eliminarServicio(){
        String agregarSql = "DELETE FROM Servicios WHERE IdServicio = "+getId(idServE.getValue());
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar este servicio?", "Eliminar Servicios - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(agregarSql)){
                JOptionPane.showMessageDialog(null, "El servicio ha sido eliminado", "Eliminar servicio", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El servicio no ha sido eliminado", "Eliminar servicio", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El servicio no ha sido eliminado", "Eliminar servicio", JOptionPane.INFORMATION_MESSAGE);
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
        idServE.getItems().clear();
        idServI.getItems().clear();
        idServM.getItems().clear();
        //IdAgregar
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT TOP 1 IdServicio FROM Servicios ORDER BY IdServicio DESC");
            while (resultSet.next()){
                int id = resultSet.getInt("IdServicio")+1;
                idServA.setText(Integer.toString(id));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //Combobox empleados
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT IdServicio, NombreServicio FROM Servicios");
            while (resultSet.next()){
                idServE.getItems().addAll(resultSet.getString("IdServicio")+"- "+resultSet.getString("NombreServicio"));
                idServI.getItems().addAll(resultSet.getString("IdServicio")+"- "+resultSet.getString("NombreServicio"));
                idServM.getItems().addAll(resultSet.getString("IdServicio")+"- "+resultSet.getString("NombreServicio"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        idServM.setOnAction(event -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();){
                resultSet = statement.executeQuery("SELECT * FROM Servicios WHERE IdServicio = '"+getId(idServM.getValue())+"'");
                while (resultSet.next()){
                    nomServM.setText(resultSet.getString("NombreServicio"));
                    preServM.setText(resultSet.getString("PrecioServicio"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        idServE.setOnAction(event -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();){
                resultSet = statement.executeQuery("SELECT * FROM Servicios WHERE IdServicio = '"+getId(idServE.getValue())+"'");
                while (resultSet.next()){
                    nomServE.setText(resultSet.getString("NombreServicio"));
                    preServE.setText(resultSet.getString("PrecioServicio"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        idServI.setOnAction(event -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();){
                resultSet = statement.executeQuery("SELECT * FROM Servicios WHERE IdServicio = '"+getId(idServI.getValue())+"'");
                while (resultSet.next()){
                    nomServI.setText(resultSet.getString("NombreServicio"));
                    preServI.setText(resultSet.getString("PrecioServicio"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });

        ObservableList<Servicio> datosTablaServ = FXCollections.observableArrayList();
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT * FROM Servicios");
            while (resultSet.next()){
                datosTablaServ.add(new Servicio(resultSet.getString("IdServicio"), resultSet.getString("NombreServicio"), resultSet.getString("PrecioServicio")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        idSerTable.setCellValueFactory(new PropertyValueFactory <Servicio, String>("idS"));
        nomSerTable.setCellValueFactory(new PropertyValueFactory<Servicio, String>("nomS"));
        preSerTable.setCellValueFactory(new PropertyValueFactory<Servicio, String>("preS"));
        servTable.setItems(datosTablaServ);
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
