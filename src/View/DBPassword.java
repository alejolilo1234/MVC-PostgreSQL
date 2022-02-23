package View;

import javax.swing.*;

public class DBPassword extends JFrame {
    public JPanel panel;
    public JTextField txtUser;
    public JPasswordField txtPassword;
    public JButton btnSubmit;

    public DBPassword() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
