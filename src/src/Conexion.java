package src;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.sql.*;

public class Conexion {
    public Conexion(){}
    //Objetos Conexión
    public ResultSet resultSet = null;
    public static String connectionUrl;
    public String getConnectionUrl(){
        return connectionUrl;
    }
    public boolean conectar(String User, String Password){
        //Conexión local
        connectionUrl = "jdbc:sqlserver://localhost; database=CriptoNet; user="+User+"; password="+Password+"; trustServerCertificate=false; loginTimeout=30;";
        //-------------directa
        //connectionUrl = "jdbc:sqlserver://localhost; database=CriptoNet; user=ChaJimLuis; password=ChaJimLuis; trustServerCertificate=false; loginTimeout=30;";
        //Conexión remota
        //connectionUrl = "jdbc:sqlserver://187.198.140.105; database=CriptoNet; user="+User+"; password="+Password+"; trustServerCertificate=false; loginTimeout=30;";
        //-------------directa
        //connectionUrl = "jdbc:sqlserver://187.198.203.253; database=CriptoNet; user=ChaJimLuis; password=ChaJimLuis; trustServerCertificate=false; loginTimeout=30;";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);
            return true;
        }
        catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    public boolean ejecutarQuery(String query){
        try (Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement();){
            statement.executeUpdate(query);
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean tfieldNotNull(TextField f){
        if(f.getText().trim().isEmpty() || f.getText()==null || f.getText() == "" || f.getText() == " ") return false;
        else return true;
    }
    public boolean dfieldNotNull(DecimalField f){
        if(f.getText().trim().isEmpty() || f.getText()==null || f.getText() == "" || f.getText() == " ") return false;
        else return true;
    }
    public boolean nfieldNotNull(NumberTextField f){
        if(f.getText().trim().isEmpty() || f.getText()==null || f.getText() == "" || f.getText() == " ") return false;
        else return true;
    }
    public boolean tareaNotNull(TextArea f){
        if(f.getText().trim().isEmpty() || f.getText()==null || f.getText() == "" || f.getText() == " ") return false;
        else return true;
    }
}
