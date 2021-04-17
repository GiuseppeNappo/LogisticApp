package Service;

import Entity.CenterSorting.CenterSorting;
import Entity.Dao;

import java.util.ArrayList;

public class CenterSortingService {

    private Dao<CenterSorting> centerSortingDao;

    public CenterSortingService(Dao<CenterSorting> daoCenterSorting){
        this.centerSortingDao = daoCenterSorting;
    }

    public ArrayList<CenterSorting> getAll(){
        return this.centerSortingDao.getAll();
    }


}
