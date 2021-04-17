package Entity.Vehicle;

import Entity.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Ogni entita avra' il il suo dao se si vuole estendere le crud operation del dao in altre operation più specifiche
 */

public abstract class VehicleDao implements Dao<Vehicle> {
    @Override
    public Vehicle get(String id) {
        return null;
    }

    @Override
    public ArrayList<Vehicle> getAll() {
        return null;
    }

    @Override
    public void save(Vehicle vehicle) {

    }


    public void updateAvailableCapacity(Vehicle vehicle) {

    }

    public void updateStateVehicle(Vehicle vehicle){

    }

    @Override
    public void delete(Vehicle vehicle) {

    }


    public void savePackInVehicle(Vehicle vehicle){

    }

    public ArrayList<Vehicle> getAllByVatNumber(String vatNumber) {

        return null;
    }

    public Vehicle getVehicleByPackId(int id) throws SQLException {

        return null;
    }
}
