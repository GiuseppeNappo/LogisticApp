package Entity.Track;

import Database.DatabaseConnection;
import Entity.CenterSorting.CenterSorting;
import Entity.CenterSorting.CenterSortingDaoImplement;
import Entity.Pack.Pack;
import Entity.Pack.PackDaoImplement;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleDaoImplement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Ogni entit' avra anche il proprio DaoImplement che implementa l'interfaccia del dao oppure estende la classe astratta che
 * ha esteso la possibilità di crud operation specifiche per quella classe
 */

public class TrackDaoImplement extends TrackDao {

    private ArrayList<Track> tracks = new ArrayList<>();

    @Override
    public ArrayList<Track> getAll() {

        return null;
    }

    @Override
    public void save(Track track) {
        String update = "INSERT INTO TRACK (PACK_ID , VEHICLE_ID , CENTER_ID , DATA ) VALUES (? , ? , ?, ? )";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(update)) {

            //Preparo la query
            // prepareStatement.setString(1, pack.getId());
            prepareStatement.setInt(1, track.getPack().getId());
            prepareStatement.setInt(2, Integer.parseInt(track.getVehicle().getId()));
            prepareStatement.setInt(3, Integer.parseInt(track.getCenterSorting().getId()));
            prepareStatement.setDate(4, Date.valueOf(track.getData()));
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(Track track) {

    }

    public ArrayList<Track> getAllTrackByVehicleID(int idVehicle) {
        String query = "SELECT * FROM  TRACK WHERE VEHICLE_ID = ?";
        return this.getAllTrackByGenericId(query , idVehicle);
    }

    public ArrayList<Track> getAllTrackByPackID(int idPack) {
        String query = "SELECT * FROM  TRACK WHERE PACK_ID = ?";
        return this.getAllTrackByGenericId(query , idPack);
    }

    public ArrayList<Track> getAllTrackByCenterID(int idCenter) {
        String query = "SELECT * FROM  TRACK WHERE CENTER_ID = ?";
        return this.getAllTrackByGenericId(query , idCenter);
    }

    public ArrayList<Track> getLastStops( int idVehicle) {
        String query = "SELECT * FROM TRACK WHERE VEHICLE_ID = ?  AND DATA = (SELECT MAX(DATA) FROM TRACK)";
        return this.getAllTrackByGenericId(query, idVehicle);
    }

    private ArrayList<Track> getAllTrackByGenericId(String query , int id) {

        ArrayList<Track> tracks = new ArrayList<>();
        ResultSet result = null;

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {

            prepareStatement.setInt(1, id);
            result = prepareStatement.executeQuery();

            Pack pack = new PackDaoImplement().get(result.getString(1));
            Vehicle vehicle = new VehicleDaoImplement().get(result.getString(2));
            CenterSorting centerSorting = new CenterSortingDaoImplement().get(result.getString(3));

            while (result.next())
                tracks.add(new Track(pack, vehicle, centerSorting, LocalDate.now()));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return tracks;
    }

}
