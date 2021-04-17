package State;

import Entity.Pack.Pack;
import Entity.Pack.PackState;
import Entity.Vehicle.Vehicle;

/**
 * Quando siamo in LockedState nel costruttore andiamo a settare la variabile vehicleIsTraveling a false
 * per indicare che il veicolo è pronto per inziare la spedizione
 */

public class ReadyState extends State{
    private final static  String nameState = "READY";
    public ReadyState(Vehicle vehicle) {
        super(vehicle);
    }



    @Override
    public String onLockShipping(int i) {
        vehicle.getPacks().get(i).setState(PackState.PACK_DELIVERED);
        vehicle.setAvailableCapacity(vehicle.getAvailableCapacity() + vehicle.getPacks().get(i).getWeight());
        vehicle.setState(new ReadyState(vehicle));
        vehicle.setState(new LockedState(vehicle));
        return "LOCKED";
    }

    @Override
    public String startShipping() {
        vehicle.setVehicleIsTraveling(true);
        vehicle.setState(new RunningState(vehicle));
        for (Pack pack :  vehicle.getPacks()) {
            pack.setState(PackState.PACK_DELIVERING);
        }
        return "RUNNING";
    }

    @Override
    public String stopShipping() {
        for (Pack pack :  vehicle.getPacks()) {
            pack.setState(PackState.PACK_INSERTED);
        }
        return "READY";
    }

    @Override
    public String getNameState() {
        return nameState;
    }
}
