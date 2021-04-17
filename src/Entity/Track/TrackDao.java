package Entity.Track;


import Entity.Dao;
import java.util.ArrayList;

/**
 * Ogni entita avra' il il suo dao se si vuole estendere le crud operation del dao in altre operation più specifiche
 */

public abstract class TrackDao implements Dao<Track> {
    @Override
    public Track get(String id) {
        return null;
    }

    @Override
    public ArrayList<Track> getAll() {
        return null;
    }

    @Override
    public void save(Track track) {

    }

    @Override
    public void delete(Track track) {

    }

    public ArrayList<Track> getAllTrackByVehicleID(int idVehicle){

        return  null;
    }

    public ArrayList<Track> getAllTrackByPackID(int idPack){

        return  null;
    }

    public ArrayList<Track> getAllTrackByCenterID(int idCenter){

        return  null;
    }

    public ArrayList<Track> getLastStops( int idVehicle) {
        return null;
    }
}
