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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MantenimientoController implements Initializable {

    private Object ProductosController;
    private Object VentasController;
    private Object MantenimientosController;
    private Object ClientesController;
    private Object EmpleadosController;
    private Object ServiciosController;

    public Label logoLblH;
    public Label logoLbl;

    //Menu agregar
    public TextField idMA;
    public ComboBox<String> idCA;
    public DatePicker fechaAM;
    public TextArea idPA;
    public ComboBox<String> idSerA;
    public NumberTextField cantSerA;
    public TextField subTA;
    public TextField totA;
    public Button btnAM; //Agregar mantemiento
    public Button btnAS; //Agregar servicio
    public Button btnC; //Cancelar mantenimiento

    //Menu modificar
    public ComboBox<String> idMM;
    public ComboBox<String> idCM;
    public DatePicker fechaM;
    public TextArea idPM;
    public ComboBox<String> idSerM;
    public NumberTextField cantSerM;
    public TextField subTM;
    public TextField totM;
    public Button btnMM; //Modificar mantemiento
    public Button btnMS; //Modificar servicio

    //Menu eliminar
    public ComboBox<String> idME;
    public TextField idCE;
    public DatePicker fechaE;
    public TextArea idPE;
    public ComboBox<String> idSerE;
    public TextField cantSerE;
    public TextField subTE;
    public TextField totE;
    public Button btnEM; //Eliminar mantenimiento

    //Menu consulta general
    @FXML TableView<Mantenimiento> mantTable;
    @FXML TableColumn<Mantenimiento, String> idMant;
    @FXML TableColumn<Mantenimiento, String> idCli;
    @FXML TableColumn<Mantenimiento, String> fecha;
    @FXML TableColumn<Mantenimiento, String> obsev;
    @FXML TableColumn<Mantenimiento, String> total;
    //Consulta general detalle
    @FXML TableView<DetalleMantenimiento> detalleMantTable;
    @FXML TableColumn<DetalleMantenimiento, String> didMant;
    @FXML TableColumn<DetalleMantenimiento, String> didServ;
    @FXML TableColumn<DetalleMantenimiento, String> cantidadServ;
    @FXML TableColumn<DetalleMantenimiento, String> subtotal;
    //Menu consulta individual
    public ComboBox<String> idMI;
    public TextField idCI;
    public DatePicker fechaI;
    public TextArea idPI;
    public ComboBox<String> idSerI;
    public TextField cantSerI;
    public TextField subTI;
    public TextField totI;
    //Conexion
    static Conexion c = new Conexion();
    public static String connectionUrl = c.getConnectionUrl();
    public ResultSet resultSet;
    //ItemsClase
    double subtotalTA = 0;
    double totalTA = 0;
    public ArrayList<String> servicios = new ArrayList<>();

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

    public void agregarMantenimiento(){
        Date d = Date.valueOf(fechaAM.getValue());
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement()){
            String sql = "INSERT INTO Mantenimientos VALUES ('"+idMA.getText()+"', '"+getId(idCA.getValue())+"', '"+d+"', '"+idPA.getText()+"' , '"+totA.getText()+"')";
            if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea agregar esta venta?", "Terminar Venta - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                if(c.ejecutarQuery(sql)){
                    for (int x = 0; x < servicios.toArray().length; x++){
                        c.ejecutarQuery(servicios.get(x));
                    }
                    totalTA = 0;
                    totA.setText("0");
                    JOptionPane.showMessageDialog(null, "La venta ha sido agregada", "Venta exitosa", JOptionPane.INFORMATION_MESSAGE);
                    updates();
                }else{
                    JOptionPane.showMessageDialog(null, "La venta no fue agregada", "Venta", JOptionPane.INFORMATION_MESSAGE);
                }
                servicios.clear();
            }else JOptionPane.showMessageDialog(null, "La venta no fue agregada", "Venta", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void agregarServicio(){
        //Creo las strings de sentencia
        String insertDetalle = "INSERT INTO DetalleMantenimiento VALUES ('"+getId(idMA.getText())+"', '"+getId(idSerA.getValue())+"', '"+cantSerA.getText()+"', '"+subTA.getText()+"');";
        servicios.add(insertDetalle);
        totalTA += subtotalTA;
        totA.setText(Double.toString(totalTA));
        System.out.println(servicios);
        idSerA.setValue(" ");
        cantSerA.setText("0");
    }
    public void cancelarMantenimiento(){
        totalTA = 0;
        subtotalTA = 0;
        servicios.clear();
    }
    public void modificarServicio(){
        totalTA = 0;
        //Actualizar total SIN EL SUBTOTAL
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement()){
            resultSet = statement.executeQuery("SELECT Subtotal, Cantidad FROM DetalleMantenimiento WHERE IdMant = "+getId(idMM.getValue())+" AND IdServicio != "+getId(idSerM.getValue()));
            while (resultSet.next()){
                totalTA += resultSet.getDouble("Subtotal") * resultSet.getDouble("Cantidad");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //Sumarle el subtotal al total
        totalTA += subtotalTA;
        String modSql = "UPDATE DetalleMantenimiento SET Cantidad = "+cantSerM.getText()+", Subtotal = "+subTM.getText()+" WHERE IdMant = "+getId(idMM.getValue())+" AND IdServicio = "+getId(idSerM.getValue())
                        +"UPDATE Mantenimientos SET Total = "+totalTA+" WHERE IdMant = "+getId(idMM.getValue());
        updates();
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea modificar este producto?", "Modificar productos - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(modSql)){
                JOptionPane.showMessageDialog(null, "El producto ha sido modificado", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El producto no sufrio modificaciones", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El producto no sufrio modificaciones", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
    }
    public void modificarMantenimiento(){
        Date d = Date.valueOf(fechaM.getValue());
        String modSql = "UPDATE Mantenimientos SET IdCliente = "+getId(idCM.getValue())+", FechaMant = '"+d+"', Observaciones = '"+idPM.getText()+"' WHERE IdMant = "+getId(idMM.getValue());
        updates();
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea modificar este producto?", "Modificar productos - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(modSql)){
                JOptionPane.showMessageDialog(null, "El producto ha sido modificado", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "El producto no sufrio modificaciones", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "El producto no sufrio modificaciones", "Modificar productos", JOptionPane.INFORMATION_MESSAGE);
    }
    public void eliminarMantenimiento(){
        String delSql = "Delete Mantenimientos WHERE IdMant ="+idME.getValue()+";"+
                        "Delete DetalleMantenimiento Where DetalleMantenimiento.IdMant =  "+idME.getValue();
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar esta venta?", "Eliminar productos - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(delSql)){
                JOptionPane.showMessageDialog(null, "la venta ha sido eliminada", "Eliminar Ventas", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "La Venta no fue eliminada", "Eliminar Ventas", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "La Venta no fue eliminada", "Eliminar Ventas", JOptionPane.INFORMATION_MESSAGE);
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
        updates();
    }
    public void updates(){
        idCA.getItems().clear();
        idSerA.getItems().clear();
        idCM.getItems().clear();
        idMM.getItems().clear();
        idSerM.getItems().clear();
        idME.getItems().clear();
        idSerE.getItems().clear();
        //IdAgregar
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement()){
            resultSet = statement.executeQuery("SELECT TOP 1 IdMant FROM Mantenimientos ORDER BY IdMant DESC");
            if(resultSet.next()){
                int id = resultSet.getInt("IdMant")+1;
                idMA.setText(Integer.toString(id));
            }else idMA.setText("1");
        }catch (SQLException e){
            e.printStackTrace();
        }
        //Actualización de subtotal dinamica agregar
        cantSerA.textProperty().addListener(((observable, oldValue, newValue) -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery("SELECT PrecioServicio FROM Servicios WHERE IdServicio = '"+getId(idSerA.getValue())+"'");
                if(cantSerA.getText() == null || cantSerA.getText().equals("")){
                    cantSerA.setText("0");
                }
                while (resultSet.next()){
                    subtotalTA = (resultSet.getDouble("PrecioServicio")*Integer.parseInt(cantSerA.getText()));
                    subTA.setText(Double.toString(subtotalTA));
                }
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }));
        //Combobox servicios agregar
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement()){
            resultSet = statement.executeQuery("SELECT IdServicio, NombreServicio, PrecioServicio FROM Servicios");
            while (resultSet.next()){
                idSerA.getItems().addAll(resultSet.getString("IdServicio")+"- "+resultSet.getString("NombreServicio")+", $"+resultSet.getString("PrecioServicio"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //Actualizar pantalla modificar
        idMM.setOnAction(event ->{
            idSerM.getItems().clear();
            //Llenar datos del mantenimiento
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery("SELECT IdMant, Mantenimientos.IdCliente, NomCliente, FechaMant, Observaciones, Total FROM  Mantenimientos, Clientes WHERE IdMant = "+getId(idMM.getValue())+" and Mantenimientos.IdCliente=Clientes.IdCliente;");
                while (resultSet.next()){
                    fechaM.setValue(LOCAL_DATE(resultSet.getString("FechaMant")));
                    idPM.setText(resultSet.getString("Observaciones"));
                    idCM.setValue(resultSet.getString("IdCliente")+"- "+resultSet.getString("NomCliente"));
                    totalTA = resultSet.getDouble("Total");
                    totM.setText(Double.toString(totalTA));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            //Llenar datos de detalleMant
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                //Llenar datos del mantenimiento
                resultSet = statement.executeQuery("SELECT DetalleMantenimiento.IdServicio, NombreServicio FROM  DetalleMantenimiento, Servicios  WHERE IdMant = "+getId(idMM.getValue())+" AND DetalleMantenimiento.IdServicio = Servicios.IdServicio ORDER BY DetalleMantenimiento.IdServicio ASC ");
                while (resultSet.next()){
                    idSerM.getItems().addAll(resultSet.getString("IdServicio")+"- "+resultSet.getString("NombreServicio"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        //Llenar datos de cada servicio en relacion a un mantenimiento Modificar
        idSerM.setOnAction(event -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery("SELECT * FROM DetalleMantenimiento WHERE IdMant = "+getId(idMM.getValue())+" AND IdServicio = "+getId(idSerM.getValue()));
                while (resultSet.next()){
                    cantSerM.setText(resultSet.getString("Cantidad"));
                    subtotalTA = resultSet.getDouble("Subtotal");
                    subTM.setText(Double.toString(subtotalTA));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        //Actualización de subtotal dinamica modificar
        cantSerM.textProperty().addListener(((observable, oldValue, newValue) -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery("SELECT PrecioServicio FROM Servicios WHERE IdServicio = '"+getId(idSerM.getValue())+"'");
                if(cantSerM.getText() == null || cantSerM.getText().equals("")){
                    cantSerM.setText("0");
                }
                while (resultSet.next()){
                    subtotalTA = (resultSet.getDouble("PrecioServicio")*Integer.parseInt(cantSerM.getText()));
                    subTM.setText(Double.toString(subtotalTA));
                }
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }));
        //Actualizar Pantalla Eliminar
        idME.setOnAction(event ->{
            idSerE.getItems().clear();
            //Llenar datos del mantenimiento
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery("SELECT IdMant, Mantenimientos.IdCliente, NomCliente, FechaMant, Observaciones, Total FROM  Mantenimientos, Clientes WHERE IdMant = "+getId(idME.getValue())+" and Mantenimientos.IdCliente=Clientes.IdCliente;");
                while (resultSet.next()){
                    fechaE.setValue(LOCAL_DATE(resultSet.getString("FechaMant")));
                    idPE.setText(resultSet.getString("Observaciones"));
                    idCE.setText(resultSet.getString("IdCliente")+"- "+resultSet.getString("NomCliente"));
                    totalTA = resultSet.getDouble("Total");
                    totE.setText(Double.toString(totalTA));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            //Llenar datos de detalleMant
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                //Llenar datos del mantenimiento
                resultSet = statement.executeQuery("SELECT DetalleMantenimiento.IdServicio, NombreServicio FROM  DetalleMantenimiento, Servicios  WHERE IdMant = "+getId(idME.getValue())+" AND DetalleMantenimiento.IdServicio = Servicios.IdServicio ORDER BY DetalleMantenimiento.IdServicio ASC ");
                while (resultSet.next()){
                    idSerE.getItems().addAll(resultSet.getString("IdServicio")+"- "+resultSet.getString("NombreServicio"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        //Llenar datos de cada servicio en relacion a un mantenimiento Eliminar
        idSerE.setOnAction(event -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery("SELECT * FROM DetalleMantenimiento WHERE IdMant = "+getId(idME.getValue())+" AND IdServicio = "+getId(idSerE.getValue()));
                while (resultSet.next()){
                    cantSerE.setText(resultSet.getString("Cantidad"));
                    subtotalTA = resultSet.getDouble("Subtotal");
                    subTE.setText(Double.toString(subtotalTA));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        //Actualizar Pantalla Consulta Individual
        idMI.setOnAction(event ->{
            idSerI.getItems().clear();
            //Llenar datos del mantenimiento
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery("SELECT IdMant, Mantenimientos.IdCliente, NomCliente, FechaMant, Observaciones, Total FROM  Mantenimientos, Clientes WHERE IdMant = "+getId(idMI.getValue())+" and Mantenimientos.IdCliente=Clientes.IdCliente;");
                while (resultSet.next()){
                    fechaI.setValue(LOCAL_DATE(resultSet.getString("FechaMant")));
                    idPI.setText(resultSet.getString("Observaciones"));
                    idCI.setText(resultSet.getString("IdCliente")+"- "+resultSet.getString("NomCliente"));
                    totalTA = resultSet.getDouble("Total");
                    totI.setText(Double.toString(totalTA));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            //Llenar datos de detalleMant
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                //Llenar datos del mantenimiento
                resultSet = statement.executeQuery("SELECT DetalleMantenimiento.IdServicio, NombreServicio FROM  DetalleMantenimiento, Servicios  WHERE IdMant = "+getId(idMI.getValue())+" AND DetalleMantenimiento.IdServicio = Servicios.IdServicio ORDER BY DetalleMantenimiento.IdServicio ASC ");
                while (resultSet.next()){
                    idSerI.getItems().addAll(resultSet.getString("IdServicio")+"- "+resultSet.getString("NombreServicio"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        //Llenar datos de cada servicio en relacion a un mantenimiento Consulta Individual
        idSerI.setOnAction(event -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery("SELECT * FROM DetalleMantenimiento WHERE IdMant = "+getId(idMI.getValue())+" AND IdServicio = "+getId(idSerI.getValue()));
                while (resultSet.next()){
                    cantSerI.setText(resultSet.getString("Cantidad"));
                    subtotalTA = resultSet.getDouble("Subtotal");
                    subTI.setText(Double.toString(subtotalTA));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        //Combobox Mantenimientos
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement()){
            resultSet = statement.executeQuery("SELECT IdMant FROM Mantenimientos");
            while (resultSet.next()){
                idMM.getItems().addAll(resultSet.getString("IdMant"));
                idME.getItems().addAll(resultSet.getString("IdMant"));
                idMI.getItems().addAll(resultSet.getString("IdMant"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //Combobox Clientes
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement()){
            resultSet = statement.executeQuery("SELECT IdCliente, NomCliente FROM Clientes");
            while (resultSet.next()){
                idCA.getItems().addAll(resultSet.getString("IdCliente")+"- "+resultSet.getString("NomCliente"));
                idCM.getItems().addAll(resultSet.getString("IdCliente")+"- "+resultSet.getString("NomCliente"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //Consulta General Mantenimientos
        ObservableList<Mantenimiento> datosTablaMant = FXCollections.observableArrayList();
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT * FROM Mantenimientos");
            while (resultSet.next()){
                datosTablaMant.add(new Mantenimiento(resultSet.getString("IdMant"),resultSet.getString("IdCliente"), resultSet.getString("FechaMant"), resultSet.getString("Observaciones"), resultSet.getString("Total")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        idMant.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("idM"));
        idCli.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("idC"));
        fecha.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("fechaM"));
        obsev.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("obsev"));
        total.setCellValueFactory(new PropertyValueFactory<Mantenimiento, String>("tot"));
        mantTable.setItems(datosTablaMant);

        //Consulta detalle Mantenimientos
        ObservableList<DetalleMantenimiento> detalleTablaMant = FXCollections.observableArrayList();
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT * FROM DetalleMantenimiento");
            while (resultSet.next()){
                detalleTablaMant.add(new DetalleMantenimiento(resultSet.getString("IdMant"), resultSet.getString("IdServicio"), resultSet.getString("Cantidad"), resultSet.getString("Subtotal")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        didMant.setCellValueFactory(new PropertyValueFactory<DetalleMantenimiento, String>("dM"));
        didServ.setCellValueFactory(new PropertyValueFactory<DetalleMantenimiento, String>("dS"));
        cantidadServ.setCellValueFactory(new PropertyValueFactory<DetalleMantenimiento, String>("cant"));
        subtotal.setCellValueFactory(new PropertyValueFactory<DetalleMantenimiento, String>("subt"));
        detalleMantTable.setItems(detalleTablaMant);
    }
    //Obtener Id
    public String getId(String textoEnCombo){
        String idnull = "1";
        if(textoEnCombo == null) return idnull;
        else{
            String[] parts = textoEnCombo.split("-" );
            return parts[0];
        }
    }
    //Convertir Fecha
    public static LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
