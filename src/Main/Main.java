package Main;

public class Main {
    public static void main(String ...args) {
        Model.Login mLogin = new Model.Login();
        Model.LoginDB pLB = new Model.LoginDB();
        View.Login vLogin = new View.Login();

        Controller.Login cProduct = new Controller.Login(mLogin, pLB, vLogin);
        cProduct.start();
        vLogin.setVisible(true);

        /*View.ChatServer vChatServer = new View.ChatServer();

        Controller.ChatServer cChatServer = new Controller.ChatServer(vChatServer);
        cChatServer.start();
        vChatServer.setVisible(true);

        View.ChatClient vChatClient = new View.ChatClient();

        Controller.ChatClient cChatClient = new Controller.ChatClient(vChatClient);
        cChatClient.start();
        vChatClient.setVisible(true);*/
    }
}
