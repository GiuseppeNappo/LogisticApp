package Service;

import Entity.Pack.Pack;
import Entity.Pack.PackDao;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleDao;

/**
 * Si occpua della gestione dei colli all'interno del veicolo il costruttore prende in input un PackDao ed un VehicleDao
 */
public class ManagePackInVehicleService {
    private VehicleDao vehicleDao;
    private PackDao packDao;
    public ManagePackInVehicleService(PackDao packDao , VehicleDao vehicleDao){
        this.vehicleDao = vehicleDao;
        this.packDao = packDao;
    }

    /**
     * Elimina il collo dal veicolo
     */
    public void delete(Pack pack){
        packDao.deletePackFromVehicle(pack);
    }

    /**
     * carica i colli sul veicolo e aggiorna la loro capacita'
     */
    public void loadPacks(Vehicle vehicle) {
        vehicleDao.savePackInVehicle(vehicle);
        vehicleDao.updateAvailableCapacity(vehicle);
    }
}
