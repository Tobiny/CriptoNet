import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import src.DecimalField;
import src.NumberTextField;
import src.Producto;
import src.Venta;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    public TableColumn<Venta, String> idProducto;
    public TableColumn<Venta, String> cantidaPro;
    public TableColumn<Venta, String> subtotal;
    public TableColumn<Venta, String> total;
    public TableView<Producto> ventasTable;

    //Menu consulta individual
    public ComboBox<String> idVC; //Id de la venta
    public TextField idCC; //Id de el cliente
    public DatePicker fechaC; //Fecha de la venta
    public ComboBox<String> idPC; // Id de el producto para venta
    public NumberTextField cantPC; // Cantidad de productos para venta
    public DecimalField subTC; // Subtotal del producto de arriba
    public DecimalField totC; //Total calculado


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

    public void eliminarVenta(MouseEvent actionEvent) throws IOException { }
    public void agregarVenta(MouseEvent actionEvent) throws IOException { }
    public void cancelarVenta(MouseEvent actionEvent) throws IOException { }
    public void agregarProducto(MouseEvent actionEvent) throws IOException { }


    public void exit(MouseEvent actionEvent) throws IOException { System.exit(0); }
    public void showLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(true);
    }
    public void hideLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
