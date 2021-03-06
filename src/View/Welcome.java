package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Welcome extends JFrame {
    // Main Frame
    public JLabel txtTitle;
    public JPanel panel;
    public JTabbedPane tabbedPane1;
    // Supplier
    public JTable table3;
    public JTextField textField3;
    public JButton guardarButton;
    public JButton eliminarButton;
    public JButton agregarButton;
    public JButton limpiarButton;
    public JComboBox textField4;
    public JTextField textField5;
    public JTextArea textArea2;
    // Supplies
    public JSpinner priceSpinner;
    public JSpinner quantitySpinner;
    public JSpinner totalSpinner;
    public JComboBox productsList;
    public JComboBox supplierList;
    public JComboBox suppliesList;
    public JTextArea textArea1;
    public JTable table2;
    public JButton agregarButton1;
    public JButton limpiarButton1;
    public JButton guardarButton1;
    public JButton eliminarButton1;
    // Products
    public JPanel products;
    public JTable table1;
    public JButton btnDelete;
    public JButton btnSave;
    public JComboBox comboBox1;
    public JSpinner textField1;
    public JButton limpiarButton2;
    // Privates

    public Welcome() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public void error(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void success(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
