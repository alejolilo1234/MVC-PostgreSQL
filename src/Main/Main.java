package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String ...args) {
        Model.Login mLogin = new Model.Login();
        Model.LoginDB mLoginDB = new Model.LoginDB();
        View.Login vLogin = new View.Login();

        Controller.Login cProduct = new Controller.Login(mLogin, mLoginDB, vLogin);
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
