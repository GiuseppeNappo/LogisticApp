package Service;

import Entity.CenterSorting.CenterSorting;
import Entity.CenterSorting.CenterSortingDao;
import Entity.Client.Client;
import Entity.Dao;
import Entity.Pack.Pack;
import Entity.Pack.PackDao;

import java.util.ArrayList;
/**
 * Si occpua della gestione dei colli all'interno del centro di smistamento il costruttore accetta come parametri un PackDao ed un centerCortingDao e un clientDao
 */
public class ManagePackInCenterService {

    private PackDao packDao;
    private CenterSortingDao centerSortingDao;
    private  Dao<Client> clientDao;


    public ManagePackInCenterService(PackDao packDao , Dao<Client> clientDao , CenterSortingDao centerSortingDaoImplement){
        this.packDao = packDao;
        this.clientDao = clientDao;
        this.centerSortingDao = centerSortingDaoImplement;
    }

    public ManagePackInCenterService(PackDao packDao ){
        this.packDao = packDao;
    }

    /**
     * Questa funzione accetta  come parametri un collo e il centro di smistamento andando a salvare i dati del collo nel centro di smistamento
     * andando a richiamare apposite crud operation dei propri dao
     */
    public void insertPackInCenterService(Pack pack , CenterSorting centerSorting){
        clientDao.save(pack.getSender());
        clientDao.save(pack.getRecipient());
        packDao.save(pack);
        centerSortingDao.insertPackInCenter(centerSorting);

    }

    /**
     *  Restituira' la lista dei colli di quel centro di smistamento
     */
    public ArrayList<Pack> getPacksByCenterId(String idCenter) {
        return packDao.getPackFromCenter(idCenter);
    }

    /**
     * Eliminera' il collo dal centro
     */
    public void delete(Pack pack){
        packDao.deletePackFromCenter(pack);
    }
}
