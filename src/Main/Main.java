package Main;

public class Main {
    public static void main(String ...args) {
        Model.Login mLogin = new Model.Login();
        Model.LoginDB pLB = new Model.LoginDB();
        View.Login vLogin = new View.Login();

        Controller.Login cProduct = new Controller.Login(mLogin, pLB, vLogin);
        cProduct.start();
        vLogin.setVisible(true);
    }
}
