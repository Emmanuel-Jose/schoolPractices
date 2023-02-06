import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root", "root");

            Statement statement;
            ResultSet result;

            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM users");

            while (result.next()) {
                System.out.println(result.getString("name"));
                System.out.println(result.getString("lastName"));
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
