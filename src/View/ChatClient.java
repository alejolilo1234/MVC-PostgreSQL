package View;

import javax.swing.*;

public class ChatClient extends JFrame {
    public JTextArea txtArea;
    public JPanel panel;
    public JTextField txtMessage;
    public JButton btnSubmit;

    public ChatClient() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
