package View;

import javax.swing.*;

public class Login extends JFrame {
    public JPanel panel1;
    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JButton btnLogin;

    public Login() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
