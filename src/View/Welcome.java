package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Welcome extends JFrame {
    public JLabel txt;
    public JPanel panel;
    public JTabbedPane tabbedPane1;
    public JPanel Materia;
    public JTable table1;
    public JPanel Productos;
    private JButton eliminarButton;
    private JButton guardarButton;

    public Welcome() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
