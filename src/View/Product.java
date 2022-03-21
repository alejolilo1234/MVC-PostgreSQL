package View;

import javax.swing.*;

public class Product extends JFrame {
    public JTextField txtCode;
    public JTextField txtName;
    public JTextField txtPrice;
    public JTextField txtQuantity;
    public JButton btnSave;
    public JButton btnDelete;
    public JButton btnUpdate;
    public JButton btnClear;
    public JButton btnSearch;
    public JTextField txtId;
    public JPanel panel;

    public Product() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
