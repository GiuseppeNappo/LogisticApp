package Entity.CenterSorting;

import Entity.Dao;

import java.util.ArrayList;

/**
 * Ogni entita avra' il il suo dao se si vuole estendere le crud operation del dao in altre operation più specifiche
 */
public abstract class CenterSortingDao implements Dao<CenterSorting> {
    @Override
    public CenterSorting get(String id) {
        return null;
    }

    @Override
    public ArrayList<CenterSorting> getAll() {
        return null;
    }

    @Override
    public void save(CenterSorting centerSorting) {

    }

    @Override
    public void delete(CenterSorting centerSorting) {

    }
    public void insertPackInCenter(CenterSorting centerSorting){}

    public CenterSorting getCenterByPackId(int id){
        return null;
    }
}
