package Controller;

import Model.ProductDB;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Product implements ActionListener {
    private Model.Product mProduct;
    private Model.ProductDB mPDB;
    private View.Product vProduct;

    public Product(Model.Product _model, Model.ProductDB _modelDB, View.Product _view) {
        this.mProduct = _model;
        this.mPDB = _modelDB;
        this.vProduct = _view;
        this.vProduct.btnSave.addActionListener(this);
        /*this.vProduct.btnUpdate.addActionListener(this);
        this.vProduct.btnDelete.addActionListener(this);
        this.vProduct.btnClear.addActionListener(this);
        this.vProduct.btnSearch.addActionListener(this);*/
    }

    public void start() {
        this.vProduct.setResizable(false);
        this.vProduct.setTitle("Productos");
        this.vProduct.setSize(500, 300);
        this.vProduct.setLocationRelativeTo(null);
        this.vProduct.add(this.vProduct.panel);
        this.vProduct.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vProduct.btnSave) {
            mProduct.setCode(this.vProduct.txtCode.getText());
            mProduct.setName(this.vProduct.txtName.getText());
            mProduct.setPrice(Double.parseDouble(this.vProduct.txtPrice.getText()));
            mProduct.setQuantity(Integer.parseInt(this.vProduct.txtQuantity.getText()));

            if (mPDB.save(mProduct)) {
                JOptionPane.showMessageDialog(null, "Exito!!");
                this.clear();
            } else {
                JOptionPane.showMessageDialog(null, "Error");
                this.clear();
            }
        }
    }

    public void clear() {
        this.vProduct.txtCode.setText(null);
        this.vProduct.txtName.setText(null);
        this.vProduct.txtPrice.setText(null);
        this.vProduct.txtQuantity.setText(null);
    }
}
