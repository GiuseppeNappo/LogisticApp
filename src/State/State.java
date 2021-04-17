package State;

import Entity.Vehicle.Vehicle;

/**
 * la classe state e' stata creata per utilizzare il pattern state
 */
public abstract class State {
    Vehicle vehicle;


    State(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract String onLockShipping(int i);

    public abstract String startShipping();

    public abstract String stopShipping();

    public abstract String getNameState();

}

