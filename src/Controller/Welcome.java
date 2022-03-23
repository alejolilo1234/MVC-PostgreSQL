package Controller;

import Model.Supplier;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Welcome implements ActionListener, MouseListener, ChangeListener {
    public View.Welcome vWelcome = null;
    public Model.WelcomeDB mWelcomeDB = null;
    DefaultTableModel model = null;
    DefaultTableModel model2 = null;
    DefaultTableModel modelTableSupplies = null;
    Model.Supplier selectedSupplier = null;
    Model.AvailableSupply selectedAvailableSupply = null;
    int selectRowTableSupplier;
    int selectRowTableSupplies;

    public Welcome(View.Welcome _view, Model.WelcomeDB _model) {
        this.vWelcome = _view;
        this.mWelcomeDB = _model;
    }

    public void start() {
        this.vWelcome.setResizable(false);
        this.vWelcome.setTitle("Panel principal");
        this.vWelcome.setSize(1000, 500);
        this.vWelcome.setLocationRelativeTo(null);
        this.vWelcome.add(this.vWelcome.panel);
        this.vWelcome.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.vWelcome.setVisible(false);
        // Supplier
        this.vWelcome.agregarButton.addActionListener(this);
        this.vWelcome.limpiarButton.addActionListener(this);
        this.vWelcome.guardarButton.addActionListener(this);
        this.vWelcome.eliminarButton.addActionListener(this);
        this.vWelcome.table3.addMouseListener(this);
        // Product
        this.vWelcome.btnSave.addActionListener(this);
        this.vWelcome.limpiarButton2.addActionListener(this);
        this.vWelcome.btnDelete.addActionListener(this);
        // Supplies
        this.vWelcome.productsList.addActionListener(this);
        this.vWelcome.supplierList.addActionListener(this);
        this.vWelcome.suppliesList.addActionListener(this);
        this.vWelcome.quantitySpinner.addChangeListener(this);
        // Available Supplies
        this.vWelcome.agregarButton1.addActionListener(this);
        this.vWelcome.limpiarButton1.addActionListener(this);
        this.vWelcome.guardarButton1.addActionListener(this);
        this.vWelcome.eliminarButton1.addActionListener(this);
        this.vWelcome.table2.addMouseListener(this);

        this.supplier();
        this.products();
        this.supplies();
        this.newSupplier();
        this.newAvailableSupplier();
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
        String[] tableHeader = { "Producto", "Materia prima", "Proveedor", "Precio Actual", "Cantidad", "Notas", "Total"};
        modelTableSupplies = new DefaultTableModel(tableHeader, 0);
        this.vWelcome.table2.setModel(modelTableSupplies);

        for(Model.AvailableSupply sup: mWelcomeDB.getAvailableSupplies()) {
            String[] item = { sup.getProduct(), sup.getSupply(), sup.getSupplier(), String.valueOf(sup.getPriceToday()), String.valueOf(sup.getQuantity()), sup.getDescription(), String.valueOf(sup.getTotal()) };
            modelTableSupplies.addRow(item);
        }

        this.vWelcome.productsList.addItem("Seleccione");
        for(String pl: Model.Transform.stringToList(this.mWelcomeDB.readProducts()))
            this.vWelcome.productsList.addItem(pl.trim());
    }

    // Products
    public void products() {
        String[] tableHeader = { "Nombre", "Cantidad" };
        model2 = new DefaultTableModel(tableHeader, 0);
        this.vWelcome.table1.setModel(model2);

        for(Model.ProductsReady sup: this.mWelcomeDB.getProductsReady()) {
            String[] item = { sup.getName(), String.valueOf(sup.getQuantity()) };
            model2.addRow(item);
        }

        this.vWelcome.comboBox1.addItem("Seleccione");
        for(String pl: Model.Transform.stringToList(this.mWelcomeDB.readProducts()))
            this.vWelcome.comboBox1.addItem(pl.trim());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Products
        if(e.getSource() == this.vWelcome.limpiarButton2) {
            this.vWelcome.comboBox1.setSelectedIndex(0);
            this.vWelcome.textField1.setValue(0);
        }
        if(e.getSource() == this.vWelcome.btnDelete) {
            String[] tableHeader = { "Nombre", "Cantidad" };
            model2 = new DefaultTableModel(tableHeader, 0);
            this.vWelcome.table1.setModel(model2);
            this.mWelcomeDB.sellProducts();
            this.vWelcome.success("Productos vendidos");
        }
        if(e.getSource() == this.vWelcome.btnSave) {
            List<Model.AvailableSupply> list = mWelcomeDB.createProduct((String) this.vWelcome.comboBox1.getSelectedItem());

            List<Boolean> harina = new ArrayList<Boolean>();
            List<Boolean> carne = new ArrayList<Boolean>();
            List<Boolean> papa = new ArrayList<Boolean>();
            List<Boolean> aceite = new ArrayList<Boolean>();
            List<Boolean> pollo = new ArrayList<Boolean>();
            List<Boolean> queso = new ArrayList<Boolean>();

            if(!list.isEmpty()) {
                for(Model.AvailableSupply i: list) {
                    switch(i.getSupply()){
                        case "Harina":
                            harina.add(true);
                        break;
                        case "Carne":
                            carne.add(true);
                        break;
                        case "Papa":
                            papa.add(true);
                            break;
                        case "Aceite":
                            aceite.add(true);
                            break;
                        case "Pollo":
                            pollo.add(true);
                            break;
                        case "Queso":
                            queso.add(true);
                            break;
                        default:
                            this.vWelcome.error("Error");
                        break;
                    }
                }
                switch((String) this.vWelcome.comboBox1.getSelectedItem()) {
                    case "Papas rellenas":
                        if(!harina.isEmpty() && !carne.isEmpty() && !papa.isEmpty()){
                            if(
                                    this.error2(String.valueOf(this.vWelcome.comboBox1.getSelectedItem())) &&
                                            this.error2(String.valueOf(this.vWelcome.textField1.getValue()))
                            ) {
                                String name = String.valueOf(this.vWelcome.comboBox1.getSelectedItem());
                                int quantity = Integer.parseInt(String.valueOf(this.vWelcome.textField1.getValue()));
                                if(this.mWelcomeDB.insertProductReady(name, quantity)){
                                    this.vWelcome.success("Datos insertados correctamente!");
                                    model2 = (DefaultTableModel) this.vWelcome.table1.getModel();
                                    String[] item = { String.valueOf(this.vWelcome.comboBox1.getSelectedItem()), String.valueOf(this.vWelcome.textField1.getValue()) };
                                    model2.addRow(item);
                                    this.vWelcome.comboBox1.setSelectedIndex(0);
                                    this.vWelcome.textField1.setValue(0);
                                } else this.vWelcome.error("Hubo un error.");
                            } else this.vWelcome.error("No se pudo crear el producto.");
                        }
                        else this.vWelcome.error("No se pudo crear el producto");
                    break;
                    case "Pasteles de carne":
                        if(!harina.isEmpty() && !carne.isEmpty() && !aceite.isEmpty()) {
                            if(
                                    this.error2(String.valueOf(this.vWelcome.comboBox1.getSelectedItem())) &&
                                            this.error2(String.valueOf(this.vWelcome.textField1.getValue()))
                            ) {
                                String name = String.valueOf(this.vWelcome.comboBox1.getSelectedItem());
                                int quantity = Integer.parseInt(String.valueOf(this.vWelcome.textField1.getValue()));
                                if(this.mWelcomeDB.insertProductReady(name, quantity)){
                                    this.vWelcome.success("Datos insertados correctamente!");
                                    model2 = (DefaultTableModel) this.vWelcome.table1.getModel();
                                    String[] item = { String.valueOf(this.vWelcome.comboBox1.getSelectedItem()), String.valueOf(this.vWelcome.textField1.getValue()) };
                                    model2.addRow(item);
                                    this.vWelcome.comboBox1.setSelectedIndex(0);
                                    this.vWelcome.textField1.setValue(0);
                                } else this.vWelcome.error("Hubo un error.");
                            } else this.vWelcome.error("No se pudo crear el producto.");
                        }
                        else this.vWelcome.error("No se pudo crear el producto");
                    break;
                    case "Pasteles de pollo":
                        if(!harina.isEmpty() && !pollo.isEmpty() && !aceite.isEmpty()) {
                            if(
                                    this.error2(String.valueOf(this.vWelcome.comboBox1.getSelectedItem())) &&
                                            this.error2(String.valueOf(this.vWelcome.textField1.getValue()))
                            ) {
                                String name = String.valueOf(this.vWelcome.comboBox1.getSelectedItem());
                                int quantity = Integer.parseInt(String.valueOf(this.vWelcome.textField1.getValue()));
                                if(this.mWelcomeDB.insertProductReady(name, quantity)){
                                    this.vWelcome.success("Datos insertados correctamente!");
                                    model2 = (DefaultTableModel) this.vWelcome.table1.getModel();
                                    String[] item = { String.valueOf(this.vWelcome.comboBox1.getSelectedItem()), String.valueOf(this.vWelcome.textField1.getValue()) };
                                    model2.addRow(item);
                                    this.vWelcome.comboBox1.setSelectedIndex(0);
                                    this.vWelcome.textField1.setValue(0);
                                } else this.vWelcome.error("Hubo un error.");
                            } else this.vWelcome.error("No se pudo crear el producto.");
                        }
                        else this.vWelcome.error("No se pudo crear el producto");
                    break;
                    case "Churros/Dedos":
                        if(!harina.isEmpty() && !queso.isEmpty() && !aceite.isEmpty()) {
                            if(
                                    this.error2(String.valueOf(this.vWelcome.comboBox1.getSelectedItem())) &&
                                            this.error2(String.valueOf(this.vWelcome.textField1.getValue()))
                            ) {
                                String name = String.valueOf(this.vWelcome.comboBox1.getSelectedItem());
                                int quantity = Integer.parseInt(String.valueOf(this.vWelcome.textField1.getValue()));
                                if(this.mWelcomeDB.insertProductReady(name, quantity)){
                                    this.vWelcome.success("Datos insertados correctamente!");
                                    model2 = (DefaultTableModel) this.vWelcome.table1.getModel();
                                    String[] item = { String.valueOf(this.vWelcome.comboBox1.getSelectedItem()), String.valueOf(this.vWelcome.textField1.getValue()) };
                                    model2.addRow(item);
                                    this.vWelcome.comboBox1.setSelectedIndex(0);
                                    this.vWelcome.textField1.setValue(0);
                                } else this.vWelcome.error("Hubo un error.");
                            } else this.vWelcome.error("No se pudo crear el producto.");
                        }
                        else this.vWelcome.error("No se pudo crear el producto");
                    break;
                    case "Hojaldras":
                        if(!harina.isEmpty() && !aceite.isEmpty()) {
                            if(
                                    this.error2(String.valueOf(this.vWelcome.comboBox1.getSelectedItem())) &&
                                            this.error2(String.valueOf(this.vWelcome.textField1.getValue()))
                            ) {
                                String name = String.valueOf(this.vWelcome.comboBox1.getSelectedItem());
                                int quantity = Integer.parseInt(String.valueOf(this.vWelcome.textField1.getValue()));
                                if(this.mWelcomeDB.insertProductReady(name, quantity)){
                                    this.vWelcome.success("Datos insertados correctamente!");
                                    model2 = (DefaultTableModel) this.vWelcome.table1.getModel();
                                    String[] item = { String.valueOf(this.vWelcome.comboBox1.getSelectedItem()), String.valueOf(this.vWelcome.textField1.getValue()) };
                                    model2.addRow(item);
                                    this.vWelcome.comboBox1.setSelectedIndex(0);
                                    this.vWelcome.textField1.setValue(0);
                                } else this.vWelcome.error("Hubo un error.");
                            } else this.vWelcome.error("No se pudo crear el producto.");
                        }
                        else this.vWelcome.error("No se pudo crear el producto");
                    break;
                    case "Empanadas":
                        if(!harina.isEmpty() && !papa.isEmpty() && !aceite.isEmpty()) {
                            if(
                                    this.error2(String.valueOf(this.vWelcome.comboBox1.getSelectedItem())) &&
                                    this.error2(String.valueOf(this.vWelcome.textField1.getValue()))
                            ) {
                                String name = String.valueOf(this.vWelcome.comboBox1.getSelectedItem());
                                int quantity = Integer.parseInt(String.valueOf(this.vWelcome.textField1.getValue()));
                                if(this.mWelcomeDB.insertProductReady(name, quantity)){
                                    this.vWelcome.success("Datos insertados correctamente!");
                                    model2 = (DefaultTableModel) this.vWelcome.table1.getModel();
                                    String[] item = { String.valueOf(this.vWelcome.comboBox1.getSelectedItem()), String.valueOf(this.vWelcome.textField1.getValue()) };
                                    model2.addRow(item);
                                    this.vWelcome.comboBox1.setSelectedIndex(0);
                                    this.vWelcome.textField1.setValue(0);
                                } else this.vWelcome.error("Hubo un error.");
                            } else this.vWelcome.error("No se pudo crear el producto.");
                        }
                        else this.vWelcome.error("No se pudo crear el producto.");
                    break;
                    default:
                        this.vWelcome.error("Error");
                    break;
                }
            } else this.vWelcome.error("No existen materias primas para este producto.");
        }
        if(e.getSource() == this.vWelcome.productsList) {
            String selected = (String) vWelcome.productsList.getSelectedItem();
            switch (selected) {
                case "Seleccione":
                    this.resetSuppliesTab();
                break;
                case "Papas rellenas":
                    this.resetSuppliesTab();
                    for(String pl: Model.Transform.stringToList(mWelcomeDB.selectSupply("Papas rellenas")))
                        this.vWelcome.suppliesList.addItem(pl.trim());
                break;
                case "Empanadas":
                    this.resetSuppliesTab();
                    for(String pl: Model.Transform.stringToList(mWelcomeDB.selectSupply("Empanadas")))
                        this.vWelcome.suppliesList.addItem(pl.trim());
                break;
                case "Pasteles de carne":
                    this.resetSuppliesTab();
                    for(String pl: Model.Transform.stringToList(mWelcomeDB.selectSupply("Pasteles de carne")))
                        this.vWelcome.suppliesList.addItem(pl.trim());
                break;
                case "Pasteles de pollo":
                    this.resetSuppliesTab();
                    for(String pl: Model.Transform.stringToList(mWelcomeDB.selectSupply("Pasteles de pollo")))
                        this.vWelcome.suppliesList.addItem(pl.trim());
                break;
                case "Churros/Dedos":
                    this.resetSuppliesTab();
                    for(String pl: Model.Transform.stringToList(mWelcomeDB.selectSupply("Churros/Dedos")))
                        this.vWelcome.suppliesList.addItem(pl.trim());
                break;
                case "Hojaldras":
                    this.resetSuppliesTab();
                    for(String pl: Model.Transform.stringToList(mWelcomeDB.selectSupply("Hojaldras")))
                        this.vWelcome.suppliesList.addItem(pl.trim());
                break;
                default:
                    this.vWelcome.error("Hubo un error, por favor intentalo de nuevo.\nRevisa que ningún campo este vacío o con un dato incorrecto.");
                break;
            }
        }
        if(e.getSource() == this.vWelcome.supplierList) {
            double price = (int) this.mWelcomeDB.getPriceSupplier((String) this.vWelcome.supplierList.getSelectedItem());
            this.vWelcome.priceSpinner.setValue(price);
        }
        if(e.getSource() == this.vWelcome.suppliesList) {
            this.vWelcome.supplierList.removeAllItems();
            this.vWelcome.quantitySpinner.setValue(0);
            this.vWelcome.totalSpinner.setValue(0);
            for(Model.Supplier i: this.mWelcomeDB.getSupplier((String) this.vWelcome.suppliesList.getSelectedItem())) {
                this.vWelcome.supplierList.addItem(i.getName());
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
                    this.vWelcome.success("Datos insertados correctamente!");
                    model = (DefaultTableModel) this.vWelcome.table3.getModel();
                    String[] item = { supplier.getName(), supplier.getSupply(), String.valueOf(supplier.getPrice()), supplier.getDescription() };
                    model.addRow(item);
                    this.clear();
                } else this.vWelcome.error("Hubo un error, por favor intentalo de nuevo.\nRevisa que ningún campo este vacío o con un dato incorrecto.");
            } else this.vWelcome.error("Hubo un error, por favor intentalo de nuevo.\nRevisa que ningún campo este vacío o con un dato incorrecto.");
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
                try {
                    newSupplier.setPrice(Double.parseDouble(model.getValueAt(select2, 2).toString()));
                } catch(Exception ex) {
                    ex.printStackTrace();
                    this.vWelcome.error("Hubo un error, por favor intentalo de nuevo.\nRevisa que ningún campo este vacío o con un dato incorrecto.");
                }
                newSupplier.setDescription(model.getValueAt(select2, 3).toString());
                if(this.mWelcomeDB.updateSupplier(selectedSupplier, newSupplier)) {
                    this.vWelcome.success("Datos insertados correctamente!");
                } else this.vWelcome.error("Hubo un error, por favor intentalo de nuevo.\nRevisa que ningún campo este vacío o con un dato incorrecto.");
            }
        }
        if(e.getSource() == this.vWelcome.eliminarButton) {
            model = (DefaultTableModel) this.vWelcome.table3.getModel();
            if(this.mWelcomeDB.deleteSupplier(selectedSupplier)) {
                this.vWelcome.success("Datos insertados correctamente!");
                model.removeRow(selectRowTableSupplier);
            } else this.vWelcome.error("Hubo un error, por favor intentalo de nuevo.\nRevisa que ningún campo este vacío o con un dato incorrecto.");
        }
        // AvailableSupplies
        if(e.getSource() == this.vWelcome.agregarButton1) {
            Model.AvailableSupply supplier = new Model.AvailableSupply();
            supplier.setProduct((String) this.vWelcome.productsList.getSelectedItem());
            supplier.setSupply((String) this.vWelcome.suppliesList.getSelectedItem());
            supplier.setSupplier((String) this.vWelcome.supplierList.getSelectedItem());
            supplier.setPriceToday((double) this.vWelcome.priceSpinner.getValue());
            supplier.setQuantity((int) this.vWelcome.quantitySpinner.getValue());
            supplier.setDescription(this.vWelcome.textArea1.getText());
            supplier.setTotal((int) this.vWelcome.totalSpinner.getValue());

            if(
                    this.error2((String) this.vWelcome.productsList.getSelectedItem()) &&
                    this.error2((String) this.vWelcome.suppliesList.getSelectedItem()) &&
                    this.error2((String) this.vWelcome.supplierList.getSelectedItem()) &&
                    this.error2(String.valueOf(this.vWelcome.quantitySpinner.getValue())) &&
                    this.error2(this.vWelcome.textArea1.getText())
            ) {
                if(this.mWelcomeDB.insertAvailableSupply(supplier)){
                    this.vWelcome.success("Datos insertados correctamente!");
                    modelTableSupplies = (DefaultTableModel) this.vWelcome.table2.getModel();
                    String[] item = { supplier.getProduct(), supplier.getSupply(), supplier.getSupplier(), String.valueOf(supplier.getPriceToday()), String.valueOf(supplier.getQuantity()), supplier.getDescription(), String.valueOf(supplier.getTotal()) };
                    modelTableSupplies.addRow(item);
                    this.clear2();
                } else this.vWelcome.error("Hubo un error, por favor intentalo de nuevo.\nRevisa que ningún campo este vacío o con un dato incorrecto.");
            } else this.vWelcome.error("Hubo un error, por favor intentalo de nuevo.\nRevisa que ningún campo este vacío o con un dato incorrecto.");
        }
        if(e.getSource() == this.vWelcome.limpiarButton1) {
            this.clear2();
        }
        if(e.getSource() == this.vWelcome.guardarButton1) {
            if(selectedAvailableSupply != null) {
                Model.AvailableSupply newSupplier = new Model.AvailableSupply();
                int select3 = this.vWelcome.table2.getSelectedRow();
                newSupplier.setProduct(modelTableSupplies.getValueAt(select3, 0).toString());
                newSupplier.setSupply(modelTableSupplies.getValueAt(select3, 1).toString());
                newSupplier.setSupplier(modelTableSupplies.getValueAt(select3, 2).toString());
                try {
                    newSupplier.setPriceToday(Double.parseDouble(modelTableSupplies.getValueAt(select3, 3).toString()));
                    newSupplier.setQuantity(Integer.parseInt(modelTableSupplies.getValueAt(select3, 4).toString()));
                    newSupplier.setTotal(Integer.parseInt(modelTableSupplies.getValueAt(select3, 6).toString()));
                } catch(Exception ex) {
                    ex.printStackTrace();
                    this.vWelcome.error("Hubo un error, por favor intentalo de nuevo.\nRevisa que ningún campo este vacío o con un dato incorrecto.");
                }
                newSupplier.setDescription(modelTableSupplies.getValueAt(select3, 5).toString());
                if(this.mWelcomeDB.updateAvailableSupplier(selectedAvailableSupply, newSupplier)) {
                    this.vWelcome.success("Datos insertados correctamente!");
                } else this.vWelcome.error("Hubo un error, por favor intentalo de nuevo.\nRevisa que ningún campo este vacío o con un dato incorrecto.");
            }
        }
        if(e.getSource() == this.vWelcome.eliminarButton1) {
            modelTableSupplies = (DefaultTableModel) this.vWelcome.table2.getModel();
            if(this.mWelcomeDB.deleteAvailableSupplier(selectedAvailableSupply)) {
                this.vWelcome.success("Datos insertados correctamente!");
                modelTableSupplies.removeRow(selectRowTableSupplies);
            } else this.vWelcome.error("Hubo un error, por favor intentalo de nuevo.\nRevisa que ningún campo este vacío o con un dato incorrecto.");
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

    public boolean error2(String check) {
        switch(check) {
            case "":
            case "0":
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

    public void clear2() {
        this.vWelcome.productsList.setSelectedIndex(0);
        this.vWelcome.priceSpinner.setValue(0);
        this.vWelcome.quantitySpinner.setValue(0);
        this.vWelcome.textArea1.setText(null);
        this.vWelcome.totalSpinner.setValue(0);
    }

    public void resetSuppliesTab() {
        this.vWelcome.suppliesList.removeAllItems();
        this.vWelcome.supplierList.removeAllItems();
        this.vWelcome.priceSpinner.setValue(0.0);
        this.vWelcome.quantitySpinner.setValue(0);
        this.vWelcome.totalSpinner.setValue(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == this.vWelcome.table3) {
            selectRowTableSupplier = this.vWelcome.table3.getSelectedRow();
        }
        if(e.getSource() == this.vWelcome.table2) {
            selectRowTableSupplies = this.vWelcome.table2.getSelectedRow();
        }
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
        if(e.getSource() == this.vWelcome.table2) {
            int select = this.vWelcome.table2.getSelectedRow();
            if(selectRowTableSupplies != select) {
                selectedAvailableSupply = new Model.AvailableSupply();
                selectedAvailableSupply.setProduct(modelTableSupplies.getValueAt(select, 0).toString());
                selectedAvailableSupply.setSupply(modelTableSupplies.getValueAt(select, 1).toString());
                selectedAvailableSupply.setSupplier(modelTableSupplies.getValueAt(select, 2).toString());
                selectedAvailableSupply.setPriceToday(Double.parseDouble(modelTableSupplies.getValueAt(select, 3).toString()));
                selectedAvailableSupply.setQuantity(Integer.parseInt(modelTableSupplies.getValueAt(select, 4).toString()));
                selectedAvailableSupply.setDescription(modelTableSupplies.getValueAt(select, 5).toString());
                selectedAvailableSupply.setTotal(Integer.parseInt(modelTableSupplies.getValueAt(select, 6).toString()));
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

    public void newAvailableSupplier() {
        selectRowTableSupplies = 9999;
        if(modelTableSupplies.getRowCount() != 0) {
            selectedAvailableSupply = new Model.AvailableSupply();
            selectedAvailableSupply.setProduct(modelTableSupplies.getValueAt(0, 0).toString());
            selectedAvailableSupply.setSupply(modelTableSupplies.getValueAt(0, 1).toString());
            selectedAvailableSupply.setSupplier(modelTableSupplies.getValueAt(0, 2).toString());
            selectedAvailableSupply.setPriceToday(Double.parseDouble(modelTableSupplies.getValueAt(0, 3).toString()));
            selectedAvailableSupply.setQuantity(Integer.parseInt(modelTableSupplies.getValueAt(0, 4).toString()));
            selectedAvailableSupply.setDescription(modelTableSupplies.getValueAt(0, 5).toString());
            selectedAvailableSupply.setTotal(Integer.parseInt(modelTableSupplies.getValueAt(0, 6).toString()));
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

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == this.vWelcome.quantitySpinner) {
            double one = (double) this.vWelcome.priceSpinner.getValue();
            int two = (int) this.vWelcome.quantitySpinner.getValue();
            this.vWelcome.totalSpinner.setValue((int) one * two);
        }
    }
}
