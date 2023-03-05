/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chrio
 */
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;


public class TestThread {

    public static void main(final String[] arguments) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        try {
            executor.submit(new Task());
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        } finally {

            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            // check if the database exists
            try {
                while(true){
                    Connection conn = null;
                    Statement st = null;
                    Class.forName("com.mysql.jdbc.Driver");
                    String pass = "kiop4.5F#";
                    String user = "root";
                    String db = "test";
                    String port = "3306";
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/" + db, user, pass);
                    st = conn.createStatement();
                    String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + db + "'";
                    ResultSet rs = st.executeQuery(sql);

                    if (rs.next()) {
                        System.out.println("Database exists");
                    }
                    Thread.sleep(5000);
                }

            } catch (ClassNotFoundException ignored) {

            } catch (SQLException ex) {
                System.out.println("Database does not exist");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
