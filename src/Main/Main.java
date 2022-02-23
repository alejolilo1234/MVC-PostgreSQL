package Main;

public class Main {
    public static void main(String ...args) {
        java.sql.Connection con = null;

        Model.Product mProduct = new Model.Product();
        Model.ProductDB pDB = new Model.ProductDB();
        View.Product vProduct = new View.Product();

        Controller.Product cProduct = new Controller.Product(mProduct, pDB, vProduct);
        cProduct.start();
        vProduct.setVisible(true);
    }
}
