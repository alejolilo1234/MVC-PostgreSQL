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
        this.vLogin.btnOne.addActionListener(this);
        this.vLogin.btnTwo.addActionListener(this);
        this.vLogin.btnThree.addActionListener(this);
        this.vLogin.btnFour.addActionListener(this);
        this.vLogin.btnFive.addActionListener(this);
        this.vLogin.btnSix.addActionListener(this);
        this.vLogin.btnSeven.addActionListener(this);
        this.vLogin.btnEight.addActionListener(this);
        this.vLogin.btnNine.addActionListener(this);
        this.vLogin.btnCero.addActionListener(this);
        this.vLogin.btnDelete.addActionListener(this);
        this.vLogin.btnClear.addActionListener(this);
    }

    public void start() {
        this.vLogin.setResizable(false);
        this.vLogin.setTitle("Inicio de sesión");
        this.vLogin.setSize(250, 400);
        this.vLogin.setLocationRelativeTo(null);
        this.vLogin.add(this.vLogin.panel);
        this.vLogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.vLogin.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vLogin.btnLogin) {
            mLogin.setName(this.vLogin.txtUsername.getText().toUpperCase().trim());
            mLogin.setPassword(Integer.parseInt(String.valueOf(this.vLogin.txtPassword.getPassword())));

            if (mLDB.login(mLogin)) {
                JOptionPane.showMessageDialog(null, "¡Bienvenid@!");

                View.Welcome vWelcome= new View.Welcome();

                Controller.Welcome cWelcome = new Controller.Welcome(vWelcome);
                cWelcome.start();
                this.vLogin.setVisible(false);
                vWelcome.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Error al ingresar. Revisa tu usuario o contraseña.");
            }
        } else if(e.getSource() == vLogin.btnOne) this.vLogin.txtPassword.setText(String.valueOf(this.vLogin.txtPassword.getPassword()) + "1");
        else if(e.getSource() == vLogin.btnTwo) this.vLogin.txtPassword.setText(String.valueOf(this.vLogin.txtPassword.getPassword()) + "2");
        else if(e.getSource() == vLogin.btnThree) this.vLogin.txtPassword.setText(String.valueOf(this.vLogin.txtPassword.getPassword()) + "3");
        else if(e.getSource() == vLogin.btnFour) this.vLogin.txtPassword.setText(String.valueOf(this.vLogin.txtPassword.getPassword()) + "4");
        else if(e.getSource() == vLogin.btnFive) this.vLogin.txtPassword.setText(String.valueOf(this.vLogin.txtPassword.getPassword()) + "5");
        else if(e.getSource() == vLogin.btnSix) this.vLogin.txtPassword.setText(String.valueOf(this.vLogin.txtPassword.getPassword()) + "6");
        else if(e.getSource() == vLogin.btnSeven) this.vLogin.txtPassword.setText(String.valueOf(this.vLogin.txtPassword.getPassword()) + "7");
        else if(e.getSource() == vLogin.btnEight) this.vLogin.txtPassword.setText(String.valueOf(this.vLogin.txtPassword.getPassword()) + "8");
        else if(e.getSource() == vLogin.btnNine) this.vLogin.txtPassword.setText(String.valueOf(this.vLogin.txtPassword.getPassword()) + "9");
        else if(e.getSource() == vLogin.btnCero) this.vLogin.txtPassword.setText(String.valueOf(this.vLogin.txtPassword.getPassword()) + "0");
        else if(e.getSource() == vLogin.btnDelete) {
            String pass = String.valueOf(this.vLogin.txtPassword.getPassword()).replaceFirst(".$","");
            this.vLogin.txtPassword.setText(pass);
        }
        else if(e.getSource() == vLogin.btnClear) this.vLogin.txtPassword.setText(null);
    }
}
