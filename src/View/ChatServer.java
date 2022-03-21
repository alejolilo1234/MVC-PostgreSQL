package View;

import javax.swing.*;
import java.net.*;
import java.io.*;

public class ChatServer extends JFrame {
    public JTextField txtMessage;
    public JButton btnSubmit;
    public JTextArea txtArea;
    public JPanel panel;

    public ChatServer() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
