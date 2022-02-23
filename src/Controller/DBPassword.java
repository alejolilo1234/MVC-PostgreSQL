package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBPassword implements ActionListener {
    private Model.DBPassword mDBPassword;
    private View.DBPassword vDBPassword;
    private java.sql.Connection con = null;

    public DBPassword(Model.DBPassword mDBPassword, View.DBPassword vDBPassword) {
        this.mDBPassword = mDBPassword;
        this.vDBPassword = vDBPassword;
        this.vDBPassword.btnSubmit.addActionListener(this);
    }

    public void star() {
        this.vDBPassword.setResizable(false);
        this.vDBPassword.setTitle("Productos");
        this.vDBPassword.setSize(250, 250);
        this.vDBPassword.setLocationRelativeTo(null);
        this.vDBPassword.add(this.vDBPassword.panel);
        this.vDBPassword.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mDBPassword.setUser(this.vDBPassword.txtUser.getText());
        mDBPassword.setPassword(String.valueOf(this.vDBPassword.txtPassword.getPassword()));

        if (0 == 0) {
            JOptionPane.showMessageDialog(null, "Exito!!");
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
