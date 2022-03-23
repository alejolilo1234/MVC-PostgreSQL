package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WelcomeDB extends Model.Connection {

    public String readProducts() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "SELECT name FROM products;";
        String lines = "";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                lines += rs.getString("name") + ",";
            }
            return lines;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String selectSupply(String _supply) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "SELECT supplies FROM products WHERE name = ?;";
        String lines = "";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, _supply);
            rs = ps.executeQuery();

            if(rs.next()) {
                lines += rs.getString("supplies");
            }
            return lines;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String selectSupplies() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "SELECT name FROM supplies;";
        String lines = "";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()) {
                lines += rs.getString("name") + ",";
            }
            return lines;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List <Model.Supplier> getSuppliers() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "SELECT * FROM supplier;";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            List <Model.Supplier> list = new ArrayList<>();

            while(rs.next()) {
                Model.Supplier sup = new Model.Supplier();
                sup.setName(rs.getString("name"));
                sup.setSupply(rs.getString("supply"));
                sup.setPrice(rs.getDouble("price"));
                sup.setDescription(rs.getString("description"));
                list.add(sup);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List <Model.Supplier> getSupplier(String _query) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "SELECT * FROM supplier WHERE supply = ?;";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, _query);
            rs = ps.executeQuery();

            List <Model.Supplier> list = new ArrayList<>();

            while(rs.next()) {
                Model.Supplier sup = new Model.Supplier();
                sup.setName(rs.getString("name"));
                sup.setSupply(rs.getString("supply"));
                sup.setPrice(rs.getDouble("price"));
                sup.setDescription(rs.getString("description"));
                list.add(sup);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public double getPriceSupplier(String _query) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "SELECT price FROM supplier WHERE name = ?;";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, _query);
            rs = ps.executeQuery();

            if(rs.next()) {
                double price = rs.getDouble("price");
                return price;
            }
            return 0.0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0.0;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean insertSupplier(Model.Supplier _sup) {
        PreparedStatement ps = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "INSERT INTO supplier(name, supply, price, description) VALUES(?, ?, ?, ?);";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, _sup.getName());
            ps.setString(2, _sup.getSupply());
            ps.setDouble(3, _sup.getPrice());
            ps.setString(4, _sup.getDescription());
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

    public boolean updateSupplier(Model.Supplier _actualSupplier, Model.Supplier _newSupplier) {
        PreparedStatement ps = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "UPDATE supplier SET name = ?, supply = ?, price = ?, description = ? WHERE name = ?;";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, _newSupplier.getName());
            ps.setString(2, _newSupplier.getSupply());
            ps.setDouble(3, _newSupplier.getPrice());
            ps.setString(4, _newSupplier.getDescription());
            ps.setString(5, _actualSupplier.getName());
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

    public boolean deleteSupplier(Model.Supplier _actualSupplier) {
        PreparedStatement ps = null;
        java.sql.Connection con = (java.sql.Connection) this.getConnection();

        String sql = "DELETE FROM supplier WHERE name = ?;";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, _actualSupplier.getName());
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
