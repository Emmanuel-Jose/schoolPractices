import java.sql.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JDBCConnection {

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    private final String username = "root";
    private final String password = "";


    JDBCConnection() {
        createDB();
        createTableUsers();
        setConnection();
    }

    void createDB() {
        String url = "jdbc:mysql://localhost:3307";
        String sql = "CREATE DATABASE IF NOT EXISTS school";
        try{
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
        }

    }

    void createTableUsers(){
        String url = "jdbc:mysql://localhost:3307/school";
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	id integer AUTO_INCREMENT PRIMARY KEY,\n"
                + "	name VARCHAR(80),\n"
                + "	lastname VARCHAR(80)\n"
                + ");";

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
        }

    }

    void setConnection() {
        String url = "jdbc:mysql://localhost:3307/school";
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void readAllUsers() {
        String query = "SELECT * FROM users";
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                System.out.println(name + " " + lastname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void insertUser( String name, String lastname ) {
        String query = "INSERT INTO users (name, lastname) VALUES ('" + name + "', '" + lastname + "')";
        try {
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

