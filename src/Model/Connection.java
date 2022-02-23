package Model;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private final String database = "mvcpostgres";
    private final String user = "postgres";
    private final String password = "teamoangie878889";
    private final String url = "jdbc:postgresql://localhost:5432/" + this.database;
    private java.sql.Connection con = null;

    public java.sql.Connection getConnection() {
        try {
            con = DriverManager.getConnection(this.url, this.user, this.password);
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        return con;
    }
}
