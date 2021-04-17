package View.OperatorView;

import ContextApplication.ContextApplicationOperator;
import Entity.Pack.Pack;
import Entity.Pack.PackDaoImplement;
import Entity.Track.Track;
import Entity.Track.TrackDaoImplement;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleDaoImplement;
import Service.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *  In questa classe l'utente potrà visualizzare tutti i veicoli in quel momento disponibili per essere caricati
 *  tale disponibilita' potra' cambiare in base alla volonta' del corrirere del veicolo
 */
public class LoadVehiclePanel extends JPanel {

    private JPanel crudBarPanel;
    private JButton loadButton;
    private JScrollPane scrollPane;
    private JTable table;
    DefaultTableModel model;
    Object[] rowData;
    VehicleForEveryCompanyService vehicleForEveryCompanyService;
    NextFitService nextFitService;
    SaveTrackService saveTrackService;
    ManagePackInVehicleService managePackInVehicleService;
    ManagePackInCenterService managePackInCenterService;


    public LoadVehiclePanel() {
        setVariables();
        setLayoutManager();
        setService();
        addComponentsToContainer();
        addActionsListener();
        setLocationAndSize();
    }

    private void setLayoutManager() {
        //setBorder();
        setLayout(null);
        crudBarPanel.setLayout(null);
        table.setEnabled(false);

    }

    private void setVariables() {
        crudBarPanel = new JPanel();
        loadButton = new JButton("Carica");
        scrollPane = new JScrollPane();
        model = new DefaultTableModel();
        table = new JTable(model);
        rowData = new Object[4];
        vehicleForEveryCompanyService = new VehicleForEveryCompanyService(new VehicleDaoImplement());
        managePackInCenterService = new ManagePackInCenterService(new PackDaoImplement());
        nextFitService = new NextFitService();
        managePackInVehicleService = new ManagePackInVehicleService(new PackDaoImplement() , new VehicleDaoImplement());
        saveTrackService = new SaveTrackService(new TrackDaoImplement());

    }


    private void addComponentsToContainer() {
        //ADD TO CRUDBAR
        crudBarPanel.add(loadButton);

        //ADD CRUDBAR TO THIS
        add(crudBarPanel);

        //ADD TABLE TO SCROLLPANE
        scrollPane.setViewportView(table);


        //ADD ITEMS TO TABLE
        addItemsToTable();

        //ADD SCROLLPANE TO THIS
        add(scrollPane);


    }

    public void setService() {
    }

    private void setLocationAndSize() {
        crudBarPanel.setBounds(0, 0, 600, 60);
        loadButton.setBounds(50, 15, 83, 30);


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

        LoadVehiclePanel self = this;

        /**
         *  In questo evento andremo a a richiamare tutti i servizi per caricare i nostri veicoli come i servizi di prelazione dei dati e quelli del
         *  next fit per risolvere il problema del bin packing
         */
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Pack> packs = managePackInCenterService.getPacksByCenterId(ContextApplicationOperator.getInstance().getCenterSorting().getId());
                ArrayList<Vehicle> vehicles = vehicleForEveryCompanyService.getNotRunningVehicles(ContextApplicationOperator.getInstance().getCenterSorting().getCompany().getVatNumber());
                vehicles = nextFitService.getService(vehicles, packs);

                for (Vehicle vehicle : vehicles) {
                        managePackInVehicleService.loadPacks(vehicle);
                        for (Pack pack : vehicle.getPacks()) {
                            saveTrackService.saveTrack(new Track(pack, vehicle, ContextApplicationOperator.getInstance().getCenterSorting(), LocalDate.now()));
                            ManagePackCenterPanel.getInstance().removeRow(((Integer)pack.getId()).toString());
                            managePackInCenterService.delete(pack);
                        }
                }
                vehicles.removeAll(vehicles);
                packs.removeAll(packs);
                self.updateTable();
            }
        });

    }

    public void addItemsToTable() {
        Object[] columnsName = new Object[4];
        columnsName[0] = "ID VEICOLO";
        columnsName[1] = "TIPO";
        columnsName[2] = "CAPACITA' MAX";
        columnsName[3] = "CAPACITA' ATTUALE";
        model.setColumnIdentifiers(columnsName);

        ArrayList<Vehicle> vehicles1 = vehicleForEveryCompanyService.getNotRunningVehicles(ContextApplicationOperator.getInstance().getCenterSorting().getCompany().getVatNumber());
        System.out.println(vehicles1.get(0).getAvailableCapacity());
        for (Vehicle vehicle : vehicles1) {
            rowData[0] = vehicle.getId();
            rowData[1] = vehicle.getType();
            rowData[2] = vehicle.getMaxCapacity();
            rowData[3] = vehicle.getAvailableCapacity();

            model.addRow(rowData);
            model.fireTableDataChanged();
        }
    }

    public  void updateTable(){
        int rowsToRemove = model.getRowCount();
        for (int i = rowsToRemove - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        addItemsToTable();
    }
}
