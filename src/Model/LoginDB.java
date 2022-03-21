package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB extends Model.Connection {
    public boolean login(Login _login) {
        PreparedStatement ps = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();
        ResultSet rs = null;

        String sql = "SELECT password FROM users WHERE username = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, _login.getName());
            rs = ps.executeQuery();
            if(rs.next()) return rs.getInt("password") == _login.getPassword();
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
