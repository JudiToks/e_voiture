package cloudy.e_voiture.models.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect
{
    public static Connection connectToPostgre() throws SQLException
    {
        Connection connection = null;
        String url = "jdbc:postgresql://monorail.proxy.rlwy.net:24257/railway";
        String user = "postgres";
        String password = "-G5a*f1GF45BCEC--FG1EgBF3-g-41gd";
        try
        {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("PostgreSQL JDBC driver not found!");
            e.printStackTrace();
            connection.close();
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed!");
            e.printStackTrace();
            connection.close();
        }
        return connection;
    }
}
