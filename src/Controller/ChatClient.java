package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    private View.ChatClient vChatClient;
    Socket socket = new Socket();
    PrintWriter writer = null;
    BufferedReader reader = null;

    public ChatClient(View.ChatClient _view) {
        this.vChatClient = _view;
    }

    public void start() {
        this.vChatClient.setResizable(false);
        this.vChatClient.setTitle("Client");
        this.vChatClient.setSize(250, 250);
        this.vChatClient.setLocationRelativeTo(null);
        this.vChatClient.add(this.vChatClient.panel);
        this.vChatClient.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.vChatClient.setVisible(false);

        Thread principal = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        socket = new Socket("localhost", 9000);
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
                        vChatClient.txtArea.append("Server says: " + receivedMessage + "\n");
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
                    vChatClient.btnSubmit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String sendMessage = vChatClient.txtMessage.getText();
                            writer.println(sendMessage);
                            vChatClient.txtMessage.setText(null);
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
