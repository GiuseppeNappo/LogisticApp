package View.CourierView;

import ContextApplication.ContextApplicationCourier;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleDaoImplement;
import Service.VehicleForEveryCompanyService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *  In questa classe il corriere potra scegliere il veicolo da cui presterà servizio
 */
public class ChoiceVehicleFrame extends JFrame {
    private JScrollPane scrollPane;
    private JList list;
    private ArrayList<Vehicle> tmpVehicle;
    private ArrayList<Vehicle> vehicles;
    private VehicleForEveryCompanyService vehicleForEveryCompanyService;

    public ChoiceVehicleFrame() {
        setVariables();
        setLayoutManager();
        addComponentsToContainer();
        addActionsListener();
    }

    private void setLayoutManager() {
        setVisible(true);
        this.setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());
        setSize(new Dimension(400, 200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void setVariables() {
        scrollPane = new JScrollPane();
        list = new JList();
        vehicleForEveryCompanyService = new VehicleForEveryCompanyService(new VehicleDaoImplement());
        tmpVehicle = getData();
        vehicles = new ArrayList<Vehicle>();


    }

    private void addComponentsToContainer() {
        this.add(scrollPane);
        ArrayList<String> choices = new ArrayList<String>();

        for (Vehicle vehicle : tmpVehicle) {
            if (vehicle.getCompany().getVatNumber().equals(ContextApplicationCourier.getInstance().getWorker().getCompany().getVatNumber())) {
                choices.add(vehicle.getId() + " , " + vehicle.getType() + " , " + vehicle.getAvailableCapacity());
                vehicles.add(vehicle);
            }
        }
        list.setListData(choices.toArray());
        scrollPane.setViewportView(list);
    }

    private void addActionsListener() {
        JFrame self = this;
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                    ContextApplicationCourier.getInstance().setVehicleId(vehicles.get(list.locationToIndex(evt.getPoint())).getId());
                    CourierView.getInstance();
                    self.dispose();
                }
            }
        });
    }


    private ArrayList<Vehicle> getData() {
        return vehicleForEveryCompanyService.getVehicles(ContextApplicationCourier.getInstance().getWorker().getCompany().getVatNumber());
    }
}
