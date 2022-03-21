package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDB extends Model.Connection {
    public boolean create(Product _product) {
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

    public boolean update(Product _product) {
        PreparedStatement ps = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "UPDATE products SET code = ?, name = ?, price = ?, quantity = ? WHERE id = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, _product.getCode());
            ps.setString(2, _product.getName());
            ps.setDouble(3, _product.getPrice());
            ps.setInt(4, _product.getQuantity());
            ps.setInt(5, _product.getId());
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

    public boolean delete(Product _product) {
        PreparedStatement ps = null;

        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "DELETE FROM products WHERE id = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, _product.getId());
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

    public boolean read(Product _product) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "SELECT * FROM products WHERE code = ?;";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, _product.getCode());
            rs = ps.executeQuery();

            if(rs.next()) {
                _product.setId(rs.getInt("id"));
                _product.setCode(rs.getString("code"));
                _product.setName(rs.getString("name"));
                _product.setPrice(rs.getDouble("price"));
                _product.setQuantity(rs.getInt("quantity"));
                return true;
            }
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
