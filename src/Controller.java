//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Controller {
    public Button loginBtn;
    public Pane proBtn;
    public Pane cliBtn;
    public Pane mantBtn;
    public Pane venBtn;
    public Pane exitBtn;
    public Label lbl1;

    //Fields de login
    public PasswordField passFld;
    public TextField userTxtF;

    public Label logoLblH;
    public Label logoLbl;

    public Controller() {
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
        Stage window = (Stage) mantBtn.getScene().getWindow();
        window.setScene(new Scene(root, 1280.0D, 720.0D));
    }
    public void changeSceneC(MouseEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
        Stage window = (Stage) cliBtn.getScene().getWindow();
        window.setScene(new Scene(root, 1280.0D, 720.0D));
    }
    public void changeSceneV(MouseEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
        Stage window = (Stage) venBtn.getScene().getWindow();
        window.setScene(new Scene(root, 1280.0D, 720.0D));
    }

    public void exit(MouseEvent actionEvent) throws IOException {
        System.exit(0);
    }


    public void enviaLogin(MouseEvent actionEvent) throws IOException {
        //Saca el texto que está en el textfild cuando se presiona el botón
        System.out.println(userTxtF.getText());
        System.out.println(passFld.getText());
        if(userTxtF.getText().equals("puta") && passFld.getText().equals("memogay")){
            changeSceneH();
        }
    }

    public void showLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(true);
    }
    public void hideLogo(MouseEvent actionEvent) {
        this.logoLblH.setVisible(false);
    }

}