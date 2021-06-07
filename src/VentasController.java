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
import src.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VentasController implements Initializable {

    private Object ProductosController;
    private Object VentasController;
    private Object MantenimientosController;
    private Object ClientesController;
    private Object EmpleadosController;
    private Object ServiciosController;

    public Label logoLblH;
    public Label logoLbl;

    //Menu agregar venta
    public TextField idVA; //Id de la venta
    public ComboBox<String> idCA; //Id de el cliente
    public DatePicker fechaA; //Fecha de la venta
    public ComboBox<String> idPA; // Id de el producto para venta
    public NumberTextField cantA; // Cantidad de productos para venta
    public DecimalField subTA; // Subtotal del producto de arriba
    public DecimalField totA; //Total calculado
    public Button btnAV; //Botón agregar venta
    public Button btnAP; //Botón agregar producto
    public Button btnC; //Botón cancelar venta

    //Menu eliminar venta
    public ComboBox<String> idVE; //Id de la venta
    public TextField idEC; //Id de el cliente
    public DatePicker fechaE; //Fecha de la venta
    public ComboBox<String> idPE; // Id de el producto para venta
    public NumberTextField cantPE; // Cantidad de productos para venta
    public DecimalField subTE; // Subtotal del producto de arriba
    public DecimalField totE; //Total calculado
    public Button btnEV; //Botón eliminar venta

    //Menu Consulta general (Tablas)
    public TableColumn<Venta, String> idVenta;
    public TableColumn<Venta, String> idCliente;
    public TableColumn<Venta, String> fecha;
    public TableColumn<Venta, String> total;
    public TableView<Venta> ventasTable;

    //Menu Consulta general (Tablas)
    public TableColumn<DetalleVenta, String> idVentaD;
    public TableColumn<DetalleVenta, String> idProducto;
    public TableColumn<DetalleVenta, String> cantidadPro;
    public TableColumn<DetalleVenta, String> subtotal;
    public TableView<DetalleVenta> detalleVentaTable;

    //Menu consulta individual
    public ComboBox<String> idVC; //Id de la venta
    public TextField idCC; //Id de el cliente
    public DatePicker fechaC; //Fecha de la venta
    public ComboBox<String> idPC; // Id de el producto para venta
    public NumberTextField cantPC; // Cantidad de productos para venta
    public DecimalField subTC; // Subtotal del producto de arriba
    public DecimalField totC; //Total calculado

    //Conexion
    static Conexion c = new Conexion();
    public static String connectionUrl = c.getConnectionUrl();
    public ResultSet resultSet;
    //ItemsClase
    double subtotalTA = 0;
    double totalTA = 0;
    public ArrayList<String> productos = new ArrayList<String>();

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
    public void eliminarVenta(MouseEvent actionEvent) throws IOException {
        String delSql = "Delete Ventas WHERE IdVenta ="+idVE.getValue()+";\n"+
                        "Delete DetalleVentas Where DetalleVentas.IdVenta =  "+idVE.getValue();
        if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar esta venta?", "Eliminar venta - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(c.ejecutarQuery(delSql)){
                JOptionPane.showMessageDialog(null, "la venta ha sido eliminada", "Eliminar Ventas", JOptionPane.INFORMATION_MESSAGE);
                updates();
            }else{
                JOptionPane.showMessageDialog(null, "La Venta no fue eliminada", "Eliminar Ventas", JOptionPane.INFORMATION_MESSAGE);
            }
        }else JOptionPane.showMessageDialog(null, "La Venta no fue eliminada", "Eliminar Ventas", JOptionPane.INFORMATION_MESSAGE);
    }
    public void agregarVenta(MouseEvent actionEvent) throws IOException {
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            String sql = "INSERT INTO Ventas VALUES ('"+idVA.getText()+"', '"+getId(idCA.getValue())+"', '"+totalTA+"', '"+fechaA.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"')";
            if(JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea agregar esta venta?", "Terminar Venta - Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                if(c.ejecutarQuery(sql)){
                    for (int x = 0; x < productos.toArray().length; x++){
                        c.ejecutarQuery(productos.get(x));
                    }
                    totalTA = 0;
                    totA.setText("0");
                    JOptionPane.showMessageDialog(null, "La venta ha sido agregada", "Venta exitosa", JOptionPane.INFORMATION_MESSAGE);
                    updates();
                }else{
                    JOptionPane.showMessageDialog(null, "La venta no fue agregada", "Venta", JOptionPane.INFORMATION_MESSAGE);
                }
                productos.clear();
            }else JOptionPane.showMessageDialog(null, "La venta no fue agregada", "Venta", JOptionPane.INFORMATION_MESSAGE);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void cancelarVenta(MouseEvent actionEvent) throws IOException {
        totalTA = 0;
        subtotalTA = 0;
        productos.clear();
        subTA.setText(Double.toString(subtotalTA));
        totA.setText(" ");
    }
    public void agregarProducto(MouseEvent actionEvent) throws IOException {
        //Ver existencia de producto
        int ex = 0;
        try(Connection connection = DriverManager.getConnection(connectionUrl);
            Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT Existencia FROM Productos WHERE IdProducto = "+getId(idPA.getValue()));
            if(resultSet.next()){
                ex = resultSet.getInt("Existencia");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //Creo las strings de sentencia
        String insertDetalle = "INSERT INTO DetalleVentas VALUES ('"+getId(idVA.getText())+"', '"+getId(idPA.getValue())+"', '"+cantA.getText()+"', '"+subTA.getText()+"');";
        String updateEx ="UPDATE Productos SET Existencia = "+Integer.toString(ex-Integer.parseInt(cantA.getText()))+" WHERE IdProducto = "+getId(idPA.getValue());
        //Compruebo stock
        if((ex-Integer.parseInt(cantA.getText())) >= 0){
            productos.add(insertDetalle+updateEx);
            totalTA += subtotalTA;
        }else JOptionPane.showMessageDialog(null, "No hay existencia de este producto, actualmente hay "+ex+" en stock", "Error", JOptionPane.INFORMATION_MESSAGE);
        totA.setText(Double.toString(totalTA));
        System.out.println(productos);
        idPA.setValue(" ");
        cantA.setText("0");
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
        idCA.getItems().clear();
        idPA.getItems().clear();
        idVE.getItems().clear();
        idPE.getItems().clear();
        idVC.getItems().clear();
        idPC.getItems().clear();
        //Set list null
        productos.clear();
        //CargarCombobox ind
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT IdVenta FROM Ventas");
            while (resultSet.next()){
                idVC.getItems().addAll(resultSet.getString("IdVenta"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //Comboboxdinamica individual
        idVC.setOnAction(event ->{
            idPC.getItems().clear();
            cantPC.setText(" ");
            subTC.setText(" ");
            totC.setText(" ");
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();){
                resultSet = statement.executeQuery("SELECT * FROM  Ventas WHERE IdVenta = '"+idVC.getValue()+"'");
                while (resultSet.next()){
                    idCC.setText(resultSet.getString("IdCliente"));
                    fechaC.setValue(LOCAL_DATE(resultSet.getString("FechaVenta")));
                    totC.setText(resultSet.getString("Total"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                //Llenar datos del mantenimiento
                resultSet = statement.executeQuery("SELECT DetalleVentas.IdProducto, NomProd FROM  DetalleVentas, Productos  WHERE IdVenta = "+getId(idVC.getValue())+" AND DetalleVentas.IdProducto = Productos.IdProducto ORDER BY DetalleVentas.IdProducto ASC ");
                while (resultSet.next()){
                    idPC.getItems().addAll(resultSet.getString("IdProducto")+"- "+resultSet.getString("NomProd"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        //Llenar datos de cada servicio en relacion a una venta Consulta Individual
        idPC.setOnAction(event -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery("SELECT * FROM DetalleVentas WHERE IdVenta = "+getId(idVC.getValue())+" AND IdProducto = "+getId(idPC.getValue()));
                while (resultSet.next()){
                    cantPC.setText(resultSet.getString("Cantidad"));
                    subtotalTA = resultSet.getDouble("Subtotal");
                    subTC.setText(Double.toString(subtotalTA));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        //Comboboxdinamica eliminar
        idVE.setOnAction(event ->{
            idPE.getItems().clear();
            cantPE.setText(" ");
            subTE.setText(" ");
            totE.setText(" ");
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();){
                resultSet = statement.executeQuery("SELECT * FROM  Ventas WHERE IdVenta = '"+idVE.getValue()+"'");
                while (resultSet.next()){
                    idEC.setText(resultSet.getString("IdCliente"));
                    fechaE.setValue(LOCAL_DATE(resultSet.getString("FechaVenta")));
                    totE.setText(resultSet.getString("Total"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                //Llenar datos del mantenimiento
                resultSet = statement.executeQuery("SELECT DetalleVentas.IdProducto, NomProd FROM  DetalleVentas, Productos  WHERE IdVenta = "+getId(idVE.getValue())+" AND DetalleVentas.IdProducto = Productos.IdProducto ORDER BY DetalleVentas.IdProducto ASC ");
                while (resultSet.next()){
                    idPE.getItems().addAll(resultSet.getString("IdProducto")+"- "+resultSet.getString("NomProd"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        //Llenar datos de cada servicio en relacion a una venta Consulta Individual
        idPE.setOnAction(event -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement()){
                resultSet = statement.executeQuery("SELECT * FROM DetalleVentas WHERE IdVenta = "+getId(idVE.getValue())+" AND IdProducto = "+getId(idPE.getValue()));
                while (resultSet.next()){
                    cantPE.setText(resultSet.getString("Cantidad"));
                    subtotalTA = resultSet.getDouble("Subtotal");
                    subTE.setText(Double.toString(subtotalTA));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        //IdAgregar
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT TOP 1 IdVenta FROM Ventas ORDER BY IdVenta DESC");
            if(resultSet.next()){
                int id = resultSet.getInt("IdVenta")+1;
                idVA.setText(Integer.toString(id));
            }else idVA.setText("1");
        }catch (SQLException e){
            e.printStackTrace();
        }
        //CargarComboboxCA
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT IdCliente, NomCliente FROM Clientes");
            while (resultSet.next()){
                idCA.getItems().addAll(resultSet.getString("IdCliente")+"- "+resultSet.getString("NomCliente"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //CargarComboboxPA
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT IdProducto, NomProd, ValorVenta FROM Productos");
            while (resultSet.next()){
                idPA.getItems().addAll(resultSet.getString("IdProducto")+"- "+resultSet.getString("NomProd")+" $"+resultSet.getString("ValorVenta"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //CargarCombobox id
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT IdVenta FROM Ventas");
            while (resultSet.next()){
                idVE.getItems().addAll(resultSet.getString("IdVenta"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT IdProducto, NomProd, ValorVenta FROM Productos");
            while (resultSet.next()){
                idPA.getItems().addAll(resultSet.getString("IdProducto")+"- "+resultSet.getString("NomProd")+" $"+resultSet.getString("ValorVenta"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        //Actualización de subtotal dinamica
        cantA.textProperty().addListener(((observable, oldValue, newValue) -> {
            try(Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();){
                resultSet = statement.executeQuery("SELECT ValorVenta FROM Productos WHERE IdProducto = '"+getId(idPA.getValue())+"'");
                if(cantA.getText() == null || cantA.getText().equals("")){
                    cantA.setText("0");
                }
                while (resultSet.next()){
                    subtotalTA = (resultSet.getDouble("ValorVenta")*Integer.parseInt(cantA.getText()));
                    subTA.setText(Double.toString(subtotalTA));
                }
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }));
        //Consulta General
        ObservableList<Venta> datosTablaVen = FXCollections.observableArrayList();
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT * FROM Ventas");
            while (resultSet.next()){
                datosTablaVen.add(new Venta(resultSet.getString("IdVenta"), resultSet.getString("IdCliente"), resultSet.getString("Total"), resultSet.getString("FechaVenta")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        idVenta.setCellValueFactory(new PropertyValueFactory<Venta, String>("idV"));
        idCliente.setCellValueFactory(new PropertyValueFactory<Venta, String>("idC"));
        total.setCellValueFactory(new PropertyValueFactory<Venta, String>("tot"));
        fecha.setCellValueFactory(new PropertyValueFactory<Venta, String>("date"));
        ventasTable.setItems(datosTablaVen);
        //Consulta General2
        ObservableList<DetalleVenta> datosTablaDVen = FXCollections.observableArrayList();
        try(Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement();){
            resultSet = statement.executeQuery("SELECT * FROM DetalleVentas");
            while (resultSet.next()){
                datosTablaDVen.add(new DetalleVenta(resultSet.getString("IdVenta"), resultSet.getString("IdProducto"), resultSet.getString("Cantidad"), resultSet.getString("Subtotal")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        idVentaD.setCellValueFactory(new PropertyValueFactory<DetalleVenta, String>("IdV"));
        idProducto.setCellValueFactory(new PropertyValueFactory<DetalleVenta, String>("IdP"));
        subtotal.setCellValueFactory(new PropertyValueFactory<DetalleVenta, String>("subt"));
        cantidadPro.setCellValueFactory(new PropertyValueFactory<DetalleVenta, String>("cant"));
        detalleVentaTable.setItems(datosTablaDVen);
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
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
