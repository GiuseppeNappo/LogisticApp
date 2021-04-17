package State;

import Entity.Pack.Pack;
import Entity.Pack.PackState;
import Entity.Vehicle.Vehicle;
/**
 * Quando siamo in LockedState nel costruttore andiamo a settare la variabile vehicleIsTraveling a false
 * per indicare che il veicolo è in spedizione
 */
public class RunningState extends State {
    private final static  String nameState = "RUNNING";
    public RunningState(Vehicle vehicle) {
        super(vehicle);
    }


    @Override
    public String onLockShipping(int i) {
        vehicle.setVehicleIsTraveling(false);
        vehicle.getPacks().get(i).setState(PackState.PACK_DELIVERED);
        vehicle.setAvailableCapacity(vehicle.getAvailableCapacity() + vehicle.getPacks().get(i).getWeight());
        vehicle.setState(new LockedState(vehicle));

        return "LOCKED";
    }

    @Override
    public String startShipping() {
        return "RUNNING";
    }

    @Override
    public String stopShipping() {
        vehicle.setVehicleIsTraveling(false);
        for (Pack pack :  vehicle.getPacks()) {
            pack.setState(PackState.PACK_INSERTED);
        }
        vehicle.setState(new ReadyState(vehicle));
        return "READY";
    }

    @Override
    public String getNameState() {
        return nameState;
    }
}
