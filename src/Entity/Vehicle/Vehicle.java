package Entity.Vehicle;

import Entity.Company.Company;
import Entity.Pack.Pack;
import State.RunningState;
import State.State;
import State.LockedState;
import State.ReadyState;


import java.util.ArrayList;


public class Vehicle {
    private String id;
    private String type;
    private int maxCapacity;
    private int availableCapacity;
    boolean vehicleIsTraveling;
    private Company company;
    private ArrayList<Pack> packs;
    private State state;

    public Vehicle(String id, String type, int capacity, Company company , boolean vehicleIsTraveling) {
        this.id = id;
        this.type = type;
        this.maxCapacity = capacity;
        this.company = company;
        this.availableCapacity = capacity;
        packs = new ArrayList<>();
        this.vehicleIsTraveling = vehicleIsTraveling;
        if(vehicleIsTraveling)
            state = new RunningState(this);
        else
            state = new ReadyState(this);
    }

    public Vehicle() {
    }

    public Company getCompany() {
        return company;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Pack> getPacks() {
        return packs;
    }

    public void setPacks(ArrayList<Pack> packs) {
        this.packs = packs;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public void addPackToVehicle(Pack pack) {
        this.packs.add(pack);
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }


    public void decreaseAvailableCapacity(int decrease) {
        if (decrease <= availableCapacity)
            availableCapacity -= decrease;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void resetAvailableCapacity() {
        availableCapacity = maxCapacity;
    }

   public void setVehicleIsTraveling(boolean vehicleIsTraveling) {
        this.vehicleIsTraveling = vehicleIsTraveling;
    }

   public boolean getVehicleIsTraveling(){
        return vehicleIsTraveling;
    }

}
