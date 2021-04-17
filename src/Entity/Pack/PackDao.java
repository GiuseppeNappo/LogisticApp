package Entity.Pack;


import Entity.Dao;
import java.util.ArrayList;
/**
 * Ogni entita avra' il il suo dao se si vuole estendere le crud operation del dao in altre operation più specifiche
 */
public abstract  class PackDao implements Dao<Pack> {
    @Override
    public Pack get(String id) {
        return null;
    }

    @Override
    public ArrayList<Pack> getAll() {
        return null;
    }

    @Override
    public void save(Pack pack) {

    }

    public void updateState(Pack pack){

    }

    @Override
    public void delete(Pack pack) {

    }

    public void deletePackFromCenter(Pack pack){

    };

    public void deletePackFromVehicle(Pack pack){
    }

    public ArrayList<Pack> getPackFromCenter(String idPack){
        return null;
    };
    public ArrayList<Pack> getPackFromVehicle(String idVehicle) {
        return null;
    }
}
