package Model;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private final String database = "mvcpostgres";
    private final String user = "postgres";
    private final String password = "123456789";
    private final String url = "jdbc:postgresql://localhost:5432/" + this.database;
    private java.sql.Connection con = null;

    public java.sql.Connection getConnection() {
        try {
            con = DriverManager.getConnection(this.url, this.user, password);
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return con;
    }
}
