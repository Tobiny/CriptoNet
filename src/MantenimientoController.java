import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import src.Mantenimiento;
import src.NumberTextField;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
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
    public DatePicker fechaA;
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
    @FXML TableView<Mantenimiento> ventasTable;
    @FXML TableColumn<Mantenimiento, String> idMant;
    @FXML TableColumn<Mantenimiento, String> idCli;
    @FXML TableColumn<Mantenimiento, String> fecha;
    @FXML TableColumn<Mantenimiento, String> mant;
    @FXML TableColumn<Mantenimiento, String> idServ;
    @FXML TableColumn<Mantenimiento, String> cantidadServ;
    @FXML TableColumn<Mantenimiento, String> subtotal;
    @FXML TableColumn<Mantenimiento, String> total;

    //Menu consulta individual
    public ComboBox<String> idMI;
    public TextField idCI;
    public DatePicker fechaI;
    public TextArea idPI;
    public ComboBox<String> idSerI;
    public TextField cantSerI;
    public TextField subTI;
    public TextField totI;

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

    public void agregarMantenimiento(){}
    public void agregarServicio(){}
    public void cancelarMantenimiento(){}
    public void modificarMantenimiento(){}
    public void modificarServicio(){}
    public void elinarMantenimiento(){}

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
