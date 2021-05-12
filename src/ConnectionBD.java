import java.sql.*;

public class ConnectionBD {
    Statement stmt = null;
    Connection conect = null;
    public static Connection sql;

    public static void connect()


    {

        try {
            String connectionUrl = "jdbc:sqlserver://localhost:3306;database=CriptoNet;user=root; password = ;";
            sql = DriverManager.getConnection(connectionUrl);
            System.out.println("Conectado.");
        }
        catch (SQLException ex)
        {
            System.out.println("Error." + ex);
        }
    }


}
