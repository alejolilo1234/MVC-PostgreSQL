package View;

import javax.swing.*;

public class Login extends JFrame {
    public JPanel panel;
    public JTextField txtUsername;
    public JPasswordField txtPassword;
    public JButton btnLogin;
    public JButton btnOne;
    public JButton btnThree;
    public JButton btnTwo;
    public JButton btnFour;
    public JButton btnFive;
    public JButton btnSix;
    public JButton btnSeven;
    public JButton btnEight;
    public JButton btnNine;
    public JButton btnCero;
    public JButton btnDelete;
    public JButton btnClear;

    public Login() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
