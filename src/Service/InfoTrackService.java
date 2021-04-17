package Service;

import Entity.CenterSorting.CenterSorting;
import Entity.CenterSorting.CenterSortingDao;
import Entity.Pack.Pack;
import Entity.Pack.PackDao;
import Entity.Track.Track;
import Entity.Track.TrackDao;
import Entity.Vehicle.Vehicle;
import Entity.Vehicle.VehicleDao;
import java.sql.SQLException;

/**
 *  Questa classe servizio restituira' tutta una serie di servizi legati alle info che possono riguardare le track quindi diciamo le tracce effettuate da un pacco
 *  accetta come parametri nel costruttore un TrackDao , vehicleDao  , packDao , centerSortingDao;
 */
public class InfoTrackService {

    private TrackDao trackDao;
    private VehicleDao vehicleDao;
    private PackDao packDao;
    private CenterSortingDao centerSortingDao;

    public InfoTrackService(TrackDao trackDao ,VehicleDao vehicleDao  , PackDao packDao , CenterSortingDao centerSortingDao){
        this.trackDao = trackDao;
        this.vehicleDao = vehicleDao;
        this.packDao = packDao;
        this.centerSortingDao = centerSortingDao;
    }

    public Pack getPack(String idPack){
        return packDao.get(idPack);
    }

    public Vehicle getVehicle(int  id)  {
        try {
            return vehicleDao.getVehicleByPackId(id);
        } catch (SQLException throwables) {
            System.out.println("THIS PACK NOT IN VEHICLE");
        }
        return  null;
    }


    public CenterSorting getCenterSorting(int id){
        return centerSortingDao.getCenterByPackId(id);
    }

    /**
     *  restutuisce una track in base al pack id
     */
    public Track  getTrackByPackId(int packId){
        return trackDao.getAllTrackByPackID(packId).get(0);
    }

    /**
     *  restutuisce una track in base all'ultimo centro smistamento in cui è stato il corriere che possiede il nostro collo
     */

    public Track getLastStop(int idVehicle){
        return trackDao.getLastStops(idVehicle).get(0);
    }


}
