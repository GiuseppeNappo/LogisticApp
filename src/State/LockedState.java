package State;


import Entity.Pack.PackState;
import Entity.Vehicle.Vehicle;

/**
 * Quando siamo in LockedState nel costruttore andiamo a settare la variabile vehicleIsTraveling a false
 * per indicare che il veicolo non e' in movimento e non puo essere richiamato
 */
public class LockedState extends  State{
    private final static  String nameState = "LOCKED";
    public LockedState(Vehicle vehicle) {
        super(vehicle);
        vehicle.setVehicleIsTraveling(false);
    }

    /**
     * Questa funzione setta lo stato del pack setta la cpacita del veicolo e anche il suo stato
     */
    @Override
    public String onLockShipping(int i) {
        vehicle.getPacks().get(i).setState(PackState.PACK_DELIVERED);
        vehicle.setAvailableCapacity(vehicle.getAvailableCapacity() + vehicle.getPacks().get(i).getWeight());
        vehicle.setState(new ReadyState(vehicle));
        if(vehicle.getVehicleIsTraveling()) {
            return "STOP RUNNING";
        }else
            vehicle.getPacks().get(i).setState(PackState.PACK_DELIVERED);
            return "LOCKED";
    }

    @Override
    public String startShipping() {
        vehicle.setState(new ReadyState(vehicle));
        return "READY";
    }

    @Override
    public String stopShipping() {
        return "LOCKED";
    }

    @Override
    public String getNameState() {
        return nameState;
    }
}
