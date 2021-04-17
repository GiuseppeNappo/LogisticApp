package View.CourierView;

import ContextApplication.ContextApplicationCourier;
import ContextApplication.ContextApplicationOperator;
import Entity.Pack.Pack;
import Entity.Pack.PackDaoImplement;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleDaoImplement;
import Service.ManagePackInVehicleService;
import Service.ManageShippingService;
import Service.VehicleOperationInShippingService;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Questa classe e' un punto di controllo del corriere dove potrà decidere se iniziare la spedizione ,
 * fermarla defininamente oppure stopparla per consegnare un collo
 */

public class CourierView extends JFrame {

    private static CourierView instance = null;
    private JPanel crudBarPanel;
    private JButton shipmentDeliveredButton;
    private JButton stopButton;
    private JButton startButton;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;
    private JLabel infoVehicleLabel;
    private Object[] rowData;
    private Object[] columnsName;
    private ArrayList<Pack> packs;
    private ManageShippingService manageShippingService;
    private VehicleOperationInShippingService vehicleOperationInShippingService;
    private Vehicle vehicle;
    private ManagePackInVehicleService managePackInVehicleService;

    public static CourierView getInstance() {
        if (instance == null)
            instance = new CourierView();
        return instance;
    }

    private CourierView() {
        setVariables();
        setLayoutManager();
        addComponentsToContainer();
        addActionsListener();
        setLocationAndSize();
        System.out.println(vehicle.getVehicleIsTraveling());
        shipmentDeliveredButton.setEnabled(vehicle.getVehicleIsTraveling());
        stopButton.setEnabled(vehicle.getVehicleIsTraveling());
    }

    private void setLayoutManager() {

        setLayout(null);
        crudBarPanel.setLayout(null);
        table.setEnabled(false);


    }

    private void setVariables() {
        //Components
        crudBarPanel = new JPanel();
        shipmentDeliveredButton = new JButton("CONSEGNA");
        startButton = new JButton("INIZIA SPEDIZIONE");
        stopButton = new JButton("TERMINA SPEDIZIONE ");
        scrollPane = new JScrollPane();
        infoVehicleLabel = new JLabel();
        model = new DefaultTableModel();
        table = new JTable(model);
        //Services
        manageShippingService = new ManageShippingService(new VehicleDaoImplement(), new PackDaoImplement());
        vehicleOperationInShippingService = new VehicleOperationInShippingService(new VehicleDaoImplement());
        managePackInVehicleService = new ManagePackInVehicleService( new PackDaoImplement() , new VehicleDaoImplement());
        //Data Containers
        rowData = new Object[4];
        columnsName = new Object[4];
        packs = new ArrayList<>();
        vehicle = manageShippingService.getVehicle(ContextApplicationCourier.getInstance().getVehicleId());

    }


    private void addComponentsToContainer() {

        //ADD TO JLABEL
        showTextFieldInfo();
        //ADD TO CRUDBAR
        crudBarPanel.add(shipmentDeliveredButton);
        shipmentDeliveredButton.setEnabled(false);
        crudBarPanel.add(startButton);
        crudBarPanel.add(stopButton);
        crudBarPanel.add(infoVehicleLabel);
        stopButton.setEnabled(false);

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
        setVisible(true);
        this.setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());
        setSize(new Dimension(800, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crudBarPanel.setBounds(0, 0, 600, 60);
        startButton.setBounds(50, 20, 200, 30);
        shipmentDeliveredButton.setBounds(50, 100, 200, 30);
        stopButton.setBounds(50, 180, 200, 30);
        infoVehicleLabel.setBounds(50, 230, 400, 30);


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
        /**
         *  In questo evento useremo un servizio per cambiare lo stato nel db
         *  mentre l'entitò veicolo fa uso del pattern state per cambiare stato ed
         *  effettuare operazioni a seconda del suo stato
         */

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicle.setPacks(packs);
                vehicle.getState().startShipping();
                vehicleOperationInShippingService.changeState(vehicle);

                for (Pack pack : vehicle.getPacks())
                    manageShippingService.changePackState(pack);

                shipmentDeliveredButton.setEnabled(vehicle.getVehicleIsTraveling());
                stopButton.setEnabled(vehicle.getVehicleIsTraveling());

                showTextFieldInfo();
            }
        });
        /**
         *  In questo evento useremo un servizio per cambiare lo stato nel db
         *  mentre l'entitò veicolo fa uso del pattern state per cambiare stato ed
         *  effettuare operazioni a seconda del suo stato
         */
        shipmentDeliveredButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shipmentDeliveredButton.setEnabled(false);
                stopButton.setEnabled(false);

                vehicle.setPacks(packs);
                vehicle.getState().stopShipping();
                vehicleOperationInShippingService.changeState(vehicle);


                new PackDeliveredFrame();
                showTextFieldInfo();

            }
        });

        /**
         *  In questo evento useremo un servizio per cambiare lo stato nel db
         *  mentre l'entitò veicolo fa uso del pattern state per cambiare stato ed
         *  effettuare operazioni a seconda del suo stato
         */

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicle.setPacks(packs);
                vehicle.getState().stopShipping();
                vehicleOperationInShippingService.changeState(vehicle);

                for (Pack pack : vehicle.getPacks())
                    manageShippingService.changePackState(pack);

                shipmentDeliveredButton.setEnabled(vehicle.getVehicleIsTraveling());
                stopButton.setEnabled(vehicle.getVehicleIsTraveling());

                showTextFieldInfo();
            }
        });
    }

    public void addItemsToTable() {
        packs = manageShippingService.getPacksByVehicleId(ContextApplicationCourier.getInstance().getVehicleId());


        columnsName[0] = "ID COLLO";
        columnsName[1] = "PESO COLLO";
        columnsName[2] = "MITTENTE";
        columnsName[3] = "DESTINATARIO";
        model.setColumnIdentifiers(columnsName);


        for (Pack pack : packs) {
            rowData[0] = pack.getId();
            rowData[1] = pack.getWeight();
            rowData[2] = pack.getSender().getName() + "," + pack.getSender().getAddress();
            rowData[3] = pack.getRecipient().getName() + ", " + pack.getRecipient().getAddress();
            model.addRow(rowData);
            model.fireTableDataChanged();
        }
    }

    public void addRow() {
        packs = new PackDaoImplement().getPackFromCenter(ContextApplicationOperator.getInstance().getCenterSorting().getId());


        Object[] row = new Object[4];

        int lastPack = packs.size() - 1;

        row[0] = packs.get(lastPack).getId();
        row[1] = packs.get(lastPack).getWeight();
        row[2] = packs.get(lastPack).getSender().getName() + "," + packs.get(lastPack).getSender().getAddress();
        row[3] = packs.get(lastPack).getRecipient().getName() + "," + packs.get(lastPack).getRecipient().getAddress();
        model.addRow(row);
    }


    public void  removeRow(String id) {

        int j = -1;
        for (int i = 0; i < packs.size(); i++) {
            if (packs.get(i).getId() == Integer.parseInt(id)) {
                j = i;
            }
        }

        /** Cambiamo stato al vehicolo quando entra in modalità Lock
         **/
        vehicle.getState().onLockShipping(j);
        vehicleOperationInShippingService.changeState(vehicle);

        /** Cambiamo stato al collo
         **/
        manageShippingService.changePackState(packs.get(j));

        /** Rimuoviamo il collo dal veicolo e aggiorniamo lo stato del veicolo
         **/
        managePackInVehicleService.delete(packs.get(j));
        manageShippingService.updateVehicleCapacity(vehicle);


        /**Rimuoviamo il collo dal dalla tabella e dalla struttra dati**/
        packs.remove(j);
        model.removeRow(j);



        /**Il veicolo cambierà stato **/
        vehicle.getState().startShipping();
        vehicleOperationInShippingService.changeState(vehicle);

        showTextFieldInfo();

    }

    public void showTextFieldInfo() {
        infoVehicleLabel.setText( "Capacità attuale " + vehicle.getAvailableCapacity() + " Stato Veicolo: " + vehicle.getState().getNameState());
    }


    public Vehicle getVehicle() {
        return vehicle;
    }
}
