package Service;

import Entity.Pack.Pack;
import Entity.Pack.PackDao;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleDao;
import State.State;

import java.util.ArrayList;
/**
 *  Questo  classe offre un servizio  alle classi , in particolar modo alle classi che fungono da view , Questa classe prende in input nel costruttore un vehicleDao e un packDao;
 *  questo servizio si occupera di tutti i servizi richiesti per la gestione della spedizione ossia tutto quello riguarda il corriere e il collo al momento della gestione della consegna
 *  o dei colli per un veicolo
 */
public class ManageShippingService {


    private VehicleDao vehicleDao;
    private PackDao packDao;

    public ManageShippingService(VehicleDao vehicleDao  , PackDao packDao){
        this.vehicleDao = vehicleDao;
        this.packDao = packDao;
    }

    /**
     * Restituisce un veicolo in base al suo id
     */
    public Vehicle getVehicle(String id) {
        return vehicleDao.get(id);
    }
    /**
     * Cambia lo stato del pacco
     */
    public void changePackState(Pack pack){
        packDao.updateState(pack);
    }

    /**
     * aggiorna la capacità del veicolo
     */
    public void updateVehicleCapacity(Vehicle vehicle){
        vehicleDao.updateAvailableCapacity(vehicle);
    }

    /**
     * Restituisce tutti i colli appartenenti all'id del veicolo
     */
    public ArrayList<Pack> getPacksByVehicleId(String idVehicle) {
        return packDao.getPackFromVehicle(idVehicle);
    }

}
