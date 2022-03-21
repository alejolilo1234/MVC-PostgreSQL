package Controller;

import View.ChatClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public View.ChatServer vChatServer;
    Socket socket = new Socket();
    PrintWriter writer = null;
    ServerSocket serverSocket = null;
    BufferedReader reader = null;

    public ChatServer(View.ChatServer _view) {
        this.vChatServer = _view;
    }

    public void start() {
        this.vChatServer.setResizable(false);
        this.vChatServer.setTitle("Server");
        this.vChatServer.setSize(250, 250);
        this.vChatServer.setLocationRelativeTo(null);
        this.vChatServer.add(this.vChatServer.panel);
        this.vChatServer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.vChatServer.setVisible(false);

        Thread principal = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(9000);
                    while (true) {
                        socket = serverSocket.accept();
                        read();
                        write();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        principal.start();
    }


    public void read() {
        Thread readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while(true) {
                        String receivedMessage = reader.readLine();
                        vChatServer.txtArea.append("Client says: " + receivedMessage + "\n");
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
        readThread.start();
    }

    public void write() {
        Thread writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    writer = new PrintWriter(socket.getOutputStream(), true);
                    vChatServer.btnSubmit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String sendMessage = vChatServer.txtMessage.getText();
                            writer.println(sendMessage);
                            vChatServer.txtMessage.setText(null);
                        }
                    });
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
        writeThread.start();
    }
}
