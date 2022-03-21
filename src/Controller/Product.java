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
        this.vProduct.btnUpdate.addActionListener(this);
        this.vProduct.btnDelete.addActionListener(this);
        this.vProduct.btnClear.addActionListener(this);
        this.vProduct.btnSearch.addActionListener(this);
    }

    public void start() {
        this.vProduct.setResizable(false);
        this.vProduct.setTitle("Productos");
        this.vProduct.setSize(500, 300);
        this.vProduct.setLocationRelativeTo(null);
        this.vProduct.add(this.vProduct.panel);
        this.vProduct.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.vProduct.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vProduct.btnSave) {
            mProduct.setCode(this.vProduct.txtCode.getText());
            mProduct.setName(this.vProduct.txtName.getText());
            mProduct.setPrice(Double.parseDouble(this.vProduct.txtPrice.getText()));
            mProduct.setQuantity(Integer.parseInt(this.vProduct.txtQuantity.getText()));

            if (mPDB.create(mProduct)) {
                JOptionPane.showMessageDialog(null, "Exito al crear!!");
                this.clear();
            } else {
                JOptionPane.showMessageDialog(null, "Error");
                this.clear();
            }
        } else if(e.getSource() == vProduct.btnUpdate) {
            mProduct.setId(Integer.parseInt(this.vProduct.txtId.getText()));
            mProduct.setCode(this.vProduct.txtCode.getText());
            mProduct.setName(this.vProduct.txtName.getText());
            mProduct.setPrice(Double.parseDouble(this.vProduct.txtPrice.getText()));
            mProduct.setQuantity(Integer.parseInt(this.vProduct.txtQuantity.getText()));

            if (mPDB.update(mProduct)) {
                JOptionPane.showMessageDialog(null, "Exito al actualizar!!");
                this.clear();
            } else {
                JOptionPane.showMessageDialog(null, "Error");
                this.clear();
            }
        } else if(e.getSource() == vProduct.btnDelete) {
            mProduct.setId(Integer.parseInt(this.vProduct.txtId.getText()));

            if (mPDB.delete(mProduct)) {
                JOptionPane.showMessageDialog(null, "Exito al eliminar!!");
                this.clear();
            } else {
                JOptionPane.showMessageDialog(null, "Error");
                this.clear();
            }
        } else if(e.getSource() == vProduct.btnSearch) {
            mProduct.setCode(this.vProduct.txtCode.getText());

            if (mPDB.read(mProduct)) {
                this.vProduct.txtId.setText(String.valueOf(mProduct.getId()));
                this.vProduct.txtCode.setText(mProduct.getCode());
                this.vProduct.txtName.setText(mProduct.getName());
                this.vProduct.txtPrice.setText(String.valueOf(mProduct.getPrice()));
                this.vProduct.txtQuantity.setText(String.valueOf(mProduct.getQuantity()));
            } else {
                JOptionPane.showMessageDialog(null, "Error al buscar");
                this.clear();
            }
        } else if(e.getSource() == vProduct.btnClear) this.clear();
    }

    public void clear() {
        this.vProduct.txtCode.setText(null);
        this.vProduct.txtName.setText(null);
        this.vProduct.txtPrice.setText(null);
        this.vProduct.txtQuantity.setText(null);
    }
}
