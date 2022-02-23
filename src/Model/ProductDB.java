package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDB extends Model.Connection {
    public boolean save(Product _product) {
        PreparedStatement ps = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "INSERT INTO products (id, code, name, price, quantity) VALUES(DEFAULT, ?, ?, ?, ?);";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, _product.getCode());
            ps.setString(2, _product.getName());
            ps.setDouble(3, _product.getPrice());
            ps.setInt(4, _product.getQuantity());
            ps.execute();
            return true;
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
