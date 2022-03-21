package Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Welcome {
    public View.Welcome vWelcome = null;

    public Welcome(View.Welcome _view) {
        this.vWelcome = _view;
    }

    public void start() {
        this.vWelcome.setResizable(false);
        this.vWelcome.setTitle("Inicio de sesión");
        this.vWelcome.setSize(500, 400);
        this.vWelcome.setLocationRelativeTo(null);
        this.vWelcome.add(this.vWelcome.panel);
        this.vWelcome.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.vWelcome.setVisible(false);

        String[] tableHeader = { "Nombre", "Cantidad", "Precio", "Total", "Proveedor" };
        DefaultTableModel model = new DefaultTableModel(tableHeader, 0);
        this.vWelcome.table1.setModel(model);
        String[] items = { "Nombre", "Cantidad", "Precio", "Total", "Proveedor" };
        model.addRow(items);
    }
}
