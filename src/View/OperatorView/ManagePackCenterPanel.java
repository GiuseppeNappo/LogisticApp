package View.OperatorView;

import ContextApplication.ContextApplicationOperator;
import Entity.Pack.Pack;
import Entity.Pack.PackDaoImplement;
import Service.ManagePackInCenterService;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * In questa classe che estende JPANEL l'operatore attraverso appositi
 * comani potra inserire e rimuovere dei colli nel centro di smistamento
 */

public class ManagePackCenterPanel extends JPanel {

    private static ManagePackCenterPanel instance = null;
    private JPanel crudBarPanel;
    private JButton insertButton;
    private JButton deleteButton;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private Object[] rowData;
    private ManagePackInCenterService managePackInCenterService;


    public static ManagePackCenterPanel getInstance() {
        if (instance == null)
            instance = new ManagePackCenterPanel();
        return instance;
    }

    private ManagePackCenterPanel() {
        setVariables();
        setLayoutManager();
        addComponentsToContainer();
        addActionsListener();
        setLocationAndSize();
    }

    private void setLayoutManager() {
        setLayout(null);
        crudBarPanel.setLayout(null);
        table.setEnabled(false);
    }

    private void setVariables() {
        crudBarPanel = new JPanel();
        insertButton = new JButton("Aggiungi");
        deleteButton = new JButton("Rimuovi");
        scrollPane = new JScrollPane();
        model = new DefaultTableModel();
        table = new JTable(model);
        rowData = new Object[4];
        managePackInCenterService = new ManagePackInCenterService(new PackDaoImplement());
    }


    private void addComponentsToContainer() {
        //ADD TO CRUDBAR
        crudBarPanel.add(insertButton);
        crudBarPanel.add(deleteButton);

        //ADD CRUDBAR TO THIS
        add(crudBarPanel);

        //ADD TABLE TO SCROLLPANE
        scrollPane.setViewportView(table);


        //ADD ITEMS TO TABLE
        addItemsToTable();

        //ADD SCROLLPANE TO THIS
        add(scrollPane);


    }

    private void setLocationAndSize() {
        crudBarPanel.setBounds(0, 0, 600, 60);
        insertButton.setBounds(50, 15, 83, 30);
        deleteButton.setBounds(150, 15, 83, 30);


        Dimension preferredSize = new Dimension();
        crudBarPanel.setMinimumSize(preferredSize);
        crudBarPanel.setPreferredSize(preferredSize);
        scrollPane.setPreferredSize(preferredSize);
        scrollPane.setPreferredSize(preferredSize);
        scrollPane.setBounds(0, 60, 500, 500);

    }

    private void addActionsListener() {
        addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InsertPackFrame(ManagePackCenterPanel.getInstance());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeletePackFrame(ManagePackCenterPanel.getInstance());
            }
        });
    }

    /**
     * Questo metodo aggiungerà dei colli alla tabella prelevatai dall'apposito servizio
     * per gestire questo caso d'uso ossia managePackInCenterService.getPacksByCenterId che dato
     * l'id del centro di smistamento ritornera' tutti i colli di qeust'ultimo
     */
    public void addItemsToTable() {
        ArrayList<Pack> packs = managePackInCenterService.getPacksByCenterId(ContextApplicationOperator.getInstance().getCenterSorting().getId());
        Object[] columnsName = new Object[4];
        columnsName[0] = "ID COLLO";
        columnsName[1] = "PESO COLLO";
        columnsName[2] = "MITTENTE";
        columnsName[3] = "DESTINATARIO";
        model.setColumnIdentifiers(columnsName);


        for (Pack pack : packs) {
            rowData[0] = pack.getId();
            rowData[1] = pack.getWeight();
            rowData[2] = pack.getSender().getCity() + "," + pack.getSender().getAddress();
            rowData[3] = pack.getRecipient().getCity() + ", " + pack.getRecipient().getAddress();
            model.addRow(rowData);
            model.fireTableDataChanged();
        }
    }

    /**
     * aggiunge una riga alla tabella
     */
    public void addRow() {
        ArrayList<Pack> packs = managePackInCenterService.getPacksByCenterId(ContextApplicationOperator.getInstance().getCenterSorting().getId());

        Object[] row = new Object[4];

        int lastPack = packs.size() - 1;

        row[0] = packs.get(lastPack).getId();
        row[1] = packs.get(lastPack).getWeight();
        row[2] = packs.get(lastPack).getSender().getCity() + "," + packs.get(lastPack).getSender().getAddress();
        row[3] = packs.get(lastPack).getRecipient().getCity() + "," + packs.get(lastPack).getRecipient().getAddress();
        model.addRow(row);
    }

    /**
     * remuove una riga dalla tabella
     */

    public void removeRow(String id) {
        ArrayList<Pack> packs = managePackInCenterService.getPacksByCenterId(ContextApplicationOperator.getInstance().getCenterSorting().getId());

        int j = -1;
        for (int i = 0; i < packs.size(); i++) {
            if (packs.get(i).getId() == Integer.parseInt(id)) {
                managePackInCenterService.delete(packs.get(i));
                j = i;
            }
        }
        packs.remove(j);
        model.removeRow(j);
    }
}