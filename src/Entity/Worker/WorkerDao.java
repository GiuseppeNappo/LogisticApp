package Entity.Worker;

import Entity.Dao;

import java.sql.SQLException;

/**
 * Ogni entita avra' il il suo dao se si vuole estendere le crud operation del dao in altre operation più specifiche
 */

public abstract class WorkerDao implements Dao<Worker> {

    public abstract boolean CheckExists(String id, String password ) throws SQLException;

}
