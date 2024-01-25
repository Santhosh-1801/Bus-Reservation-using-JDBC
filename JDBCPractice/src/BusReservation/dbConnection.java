package BusReservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static  final String url = "jdbc:mysql://localhost:5000/busrev";
    private static final String username = "root";
    private static final String password = "123456789";


    public static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
