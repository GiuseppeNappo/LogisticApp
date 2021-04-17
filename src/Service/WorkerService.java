package Service;

import Entity.Dao;
import Entity.Worker.Worker;

/**
 *  Questo semplice classe offre un servizio get che restituirà un worker in base al suo id andando a richiamre
 *  il metodo un metodo dell'istanza workerdao che sara' la classe che si occuperà di fare le query
 */
public class WorkerService {

    private Dao<Worker> workerDao;

    public WorkerService(Dao<Worker> workerDao){
        this.workerDao = workerDao;
    }

    public Worker get(String id){
        return  workerDao.get(id);
    }
}
