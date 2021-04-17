package Service;

import Entity.Dao;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleDao;

import java.util.ArrayList;
/**
 *  Questo  classe offre un servizio  alle classi , in particolar modo alle classi che fungono da view , Questa classe prende in input nel costruttore un vehicleDao;
 */
public class VehicleForEveryCompanyService {

    private VehicleDao vehicleDao;

    public VehicleForEveryCompanyService(VehicleDao vehicleDao){
        this.vehicleDao = vehicleDao;
    }

    /**
     *  Restituisce al richiedente di tale servizio tutti i veicoli in base all'id dell'azienda di appartenenza
     */

    public ArrayList<Vehicle> getVehicles(String vatNumber){

        return vehicleDao.getAllByVatNumber(vatNumber);
    }

    /**
     *  Restituisce al richiedente di tale servizio tutti i veicoli in base all'id dell'azienda di appartenenza che in quel momento non sono in movimento
     *  e quindi disposinibili per essere richiamati
     */
    public  ArrayList<Vehicle> getNotRunningVehicles(String vatNumber){

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (Vehicle vehicle : vehicleDao.getAllByVatNumber(vatNumber)) {
            if(!vehicle.getState().getNameState().equals("RUNNING"))
                vehicles.add(vehicle);
        }
        return vehicles;
    }
}
