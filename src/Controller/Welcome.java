package Controller;

import Model.Supplier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

public class Welcome implements ActionListener, MouseListener {
    public View.Welcome vWelcome = null;
    public Model.WelcomeDB mWelcomeDB = null;
    DefaultTableModel model = null;
    DefaultTableModel model2 = null;
    Model.Supplier selectedSupplier = null;
    int selectRowTableSupplier;

    public Welcome(View.Welcome _view, Model.WelcomeDB _model) {
        this.vWelcome = _view;
        this.mWelcomeDB = _model;
    }

    public void start() {
        this.vWelcome.setResizable(false);
        this.vWelcome.setTitle("Panel principal");
        this.vWelcome.setSize(800, 400);
        this.vWelcome.setLocationRelativeTo(null);
        this.vWelcome.add(this.vWelcome.panel);
        this.vWelcome.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.vWelcome.setVisible(false);
        // Supplier
        this.vWelcome.agregarButton.addActionListener(this);
        this.vWelcome.limpiarButton.addActionListener(this);
        this.vWelcome.guardarButton.addActionListener(this);
        this.vWelcome.table3.addMouseListener(this);
        // Product
        this.vWelcome.btnSave.addActionListener(this);
        // Supplies
        this.vWelcome.productsList.addActionListener(this);

        this.supplier();
        this.products();
        this.supplies();
        this.newSupplier();
    }

    // Supplier
    public void supplier() {
        String[] tableHeader = { "Nombre", "Materia prima", "Precio", "Descripción"};
        model = new DefaultTableModel(tableHeader, 0);
        this.vWelcome.table3.setModel(model);

        for(Model.Supplier sup: mWelcomeDB.getSuppliers()) {
            String[] item = { sup.getName(), sup.getSupply(), String.valueOf(sup.getPrice()), sup.getDescription() };
            model.addRow(item);
        }

        this.vWelcome.textField4.addItem("Seleccione");
        for(String pl: Model.Transform.stringToList(mWelcomeDB.selectSupplies()))
            this.vWelcome.textField4.addItem(pl.trim());
    }

    // Supplies
    public void supplies() {
        this.vWelcome.productsList.addItem("Seleccione");
        for(String pl: Model.Transform.stringToList(mWelcomeDB.readProducts()))
            this.vWelcome.productsList.addItem(pl.trim());
    }

    // Products
    public void products() {
        String[] tableHeader = { "Nombre", "Cantidad", "Precio", "Total", "Proveedor" };
        model2 = new DefaultTableModel(tableHeader, 0);
        this.vWelcome.table1.setModel(model2);

        String[] items = { "Nombre1", "Cantidad1", "Precio1", "Total1", "Proveedor1" };
        String[] items2 = { "Nombre2", "Cantidad2", "Precio2", "Total2", "Proveedor2" };
        model2.addRow(items);
        model2.addRow(items2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Products
        if(e.getSource() == this.vWelcome.btnSave) {
            int select = this.vWelcome.table1.getSelectedRow();
            System.out.println(model2.getValueAt(select, 0).toString());
            System.out.println(model2.getValueAt(select, 1).toString());
            System.out.println(model2.getValueAt(select, 2).toString());
            System.out.println(model2.getValueAt(select, 3).toString());
            System.out.println(model2.getValueAt(select, 4).toString());
        }
        if(e.getSource() == this.vWelcome.productsList) {
            String selected = (String) vWelcome.productsList.getSelectedItem();
            switch (selected) {
                case "Seleccione":
                    this.vWelcome.suppliesList.removeAllItems();
                    this.vWelcome.supplierList.removeAllItems();
                break;
                case "Papas rellenas":
                    for(String pl: Model.Transform.stringToList(mWelcomeDB.selectSupply("Papas rellenas")))
                        this.vWelcome.suppliesList.addItem(pl.trim());
                break;
                default:
                    System.out.println("Error");
                    this.vWelcome.suppliesList.removeAllItems();
                    this.vWelcome.supplierList.removeAllItems();
                break;
            }
        }
        // Supplier
        if(e.getSource() == this.vWelcome.agregarButton) {
            Model.Supplier supplier = new Supplier();
            supplier.setName(this.vWelcome.textField3.getText());
            supplier.setSupply((String) this.vWelcome.textField4.getSelectedItem());
            supplier.setPrice(Double.parseDouble(this.vWelcome.textField5.getText()));
            supplier.setDescription(this.vWelcome.textArea2.getText());

            if(
                    this.error(this.vWelcome.textField3.getText()) &&
                    this.error((String) this.vWelcome.textField4.getSelectedItem()) &&
                    this.error(this.vWelcome.textField5.getText())
            ) {
                if(this.mWelcomeDB.insertSupplier(supplier)){
                    this.vWelcome.exito();
                    model = (DefaultTableModel) this.vWelcome.table3.getModel();
                    String[] item = { supplier.getName(), supplier.getSupply(), String.valueOf(supplier.getPrice()), supplier.getDescription() };
                    model.addRow(item);
                    this.clear();
                } else this.vWelcome.error();
            }

        }
        if(e.getSource() == this.vWelcome.limpiarButton) {
            this.clear();
        }
        if(e.getSource() == this.vWelcome.guardarButton) {
            if(selectedSupplier != null) {
                Model.Supplier newSupplier = new Model.Supplier();
                int select2 = this.vWelcome.table3.getSelectedRow();
                newSupplier.setName(model.getValueAt(select2, 0).toString());
                newSupplier.setSupply(model.getValueAt(select2, 1).toString());
                newSupplier.setPrice(Double.parseDouble(model.getValueAt(select2, 2).toString()));
                newSupplier.setDescription(model.getValueAt(select2, 3).toString());
                if(this.mWelcomeDB.updateSupplier(selectedSupplier, newSupplier)) {
                    this.vWelcome.exito();
                } else this.vWelcome.error();
            }
        }
    }

    public boolean error(String check) {
        switch(check) {
            case "":
            case "Seleccione":
                return false;
            default:
                return true;
        }
    }

    public void clear() {
        this.vWelcome.textField3.setText(null);
        this.vWelcome.textField4.setSelectedIndex(0);
        this.vWelcome.textField5.setText(null);
        this.vWelcome.textArea2.setText(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        selectRowTableSupplier = this.vWelcome.table3.getSelectedRow();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == this.vWelcome.table3) {
            int select = this.vWelcome.table3.getSelectedRow();
            if(selectRowTableSupplier != select) {
                selectedSupplier = new Model.Supplier();
                selectedSupplier.setName(model.getValueAt(select, 0).toString());
                selectedSupplier.setSupply(model.getValueAt(select, 1).toString());
                selectedSupplier.setPrice(Double.parseDouble(model.getValueAt(select, 2).toString()));
                selectedSupplier.setDescription(model.getValueAt(select, 3).toString());
            }
        }
    }

    public void newSupplier() {
        selectRowTableSupplier = 9999;
        if(model.getRowCount() != 0) {
            selectedSupplier = new Model.Supplier();
            selectedSupplier.setName(model.getValueAt(0, 0).toString());
            selectedSupplier.setSupply(model.getValueAt(0, 1).toString());
            selectedSupplier.setPrice(Double.parseDouble(model.getValueAt(0, 2).toString()));
            selectedSupplier.setDescription(model.getValueAt(0, 3).toString());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
