package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Login implements ActionListener {
    private Model.Login mLogin;
    private Model.LoginDB mLDB;
    private View.Login vLogin;

    public Login(Model.Login _model, Model.LoginDB _modelDB, View.Login _view) {
        this.mLogin = _model;
        this.mLDB = _modelDB;
        this.vLogin = _view;
        this.vLogin.btnLogin.addActionListener(this);
    }

    public void start() {
        this.vLogin.setResizable(false);
        this.vLogin.setTitle("Inicio de sesión");
        this.vLogin.setSize(250, 250);
        this.vLogin.setLocationRelativeTo(null);
        this.vLogin.add(this.vLogin.panel1);
        this.vLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.vLogin.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vLogin.btnLogin) {
            mLogin.setName(this.vLogin.txtUsername.getText().toUpperCase().trim());
            mLogin.setPassword(Integer.parseInt(String.valueOf(this.vLogin.txtPassword.getPassword())));

            if (mLDB.login(mLogin)) {
                JOptionPane.showMessageDialog(null, "Bienvenido");
                Model.Product mProduct = new Model.Product();
                Model.ProductDB pDB = new Model.ProductDB();
                View.Product vProduct = new View.Product();

                Controller.Product cProduct = new Controller.Product(mProduct, pDB, vProduct);
                cProduct.start();
                this.vLogin.setVisible(false);
                vProduct.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
    }
}
