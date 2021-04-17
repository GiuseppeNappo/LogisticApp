package Entity.Track;
import Entity.CenterSorting.CenterSorting;
import Entity.Pack.Pack;

import Entity.Vehicle.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;

public class Track {
    private Vehicle vehicle;
    private CenterSorting centerSorting;
    private Pack pack;
    private LocalDate data;

    public Track(Pack pack ,  Vehicle vehicle , CenterSorting centerSorting  , LocalDate data){

        this.vehicle = vehicle;
        this.centerSorting = centerSorting;
        this.pack  = pack;
        this.data = data;
    }

    public Track(){}

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public CenterSorting getCenterSorting() {
        return centerSorting;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setCenterSorting(CenterSorting centerSorting) {
        this.centerSorting = centerSorting;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    public LocalDate getData() {

       return data;
    }
}
