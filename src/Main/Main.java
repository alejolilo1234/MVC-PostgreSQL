package Main;

public class Main {
    public static void main(String ...args) {
        /*View.Product view = new View.Product();
        view.setLocationRelativeTo(null);
        view.setSize(500, 300);
        view.add(view.panel);
        view.setResizable(false);
        view.setVisible(true);*/
        Model.Product mProduct = new Model.Product();
        Model.ProductDB pDB = new Model.ProductDB();
        View.Product vProduct = new View.Product();

        Controller.Product cProduct = new Controller.Product(mProduct, pDB, vProduct);
        cProduct.start();
        vProduct.setVisible(true);
        /*Model.Connection con = new Model.Connection();
        System.out.println(con.getConnection());*/
    }
}
