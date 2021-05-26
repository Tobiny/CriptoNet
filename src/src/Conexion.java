package src;
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
        connectionUrl = "jdbc:sqlserver://localhost; database=CriptoNet; user="+User+"; password="+Password+"; trustServerCertificate=false; loginTimeout=30;";
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
}
