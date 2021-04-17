package Service;

import Entity.Pack.PackDao;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleDao;
/**
 *  Questo semplice classe offre un servizio  alle classi , in particolar modo alle classi che fungono da view , Questa classe prende in input nel costruttore un vehicleDao;
 */

public class VehicleOperationInShippingService {
    private VehicleDao vehicleDao;


    public VehicleOperationInShippingService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }
    /**
     *  Semplicemente permettera' alla view che richiama questo servizio di aggiornare lo stato nel vehicle nel db
     */
    public void changeState(Vehicle vehicle) {
        vehicleDao.updateStateVehicle(vehicle);
    }
}
