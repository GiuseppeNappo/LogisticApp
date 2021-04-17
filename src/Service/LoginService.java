package Service;

import Entity.Worker.WorkerDao;

import java.sql.SQLException;

/**
 * La classe del Login Service prende come parametri di input nel costruttore un worker dao il quale nella sua unica funzione ossia quella di authentcate andandogli a passare id e password
 * restituirà un tipo della classe AuthType in base al tipo di autenticazione effettuata dall'utente e se questa autenticazione è stata realmente portata a termine
 */
public class LoginService {

    WorkerDao workerDao;

    public LoginService(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }

    public AuthType authenticate(String id, String password) {

        try {
            if (workerDao.CheckExists(id, password) && workerDao.get(id).getType().equals("O"))
                return AuthType.OPERATOR_AUTHENTICATED;
            else if(workerDao.CheckExists(id, password) && workerDao.get(id).getType().equals("C"))
                return AuthType.COURIER_AUTHENTICATED;
        } catch (SQLException throwable) {
            return AuthType.NOT_AUTHENTICATED;
        }

        return AuthType.NOT_AUTHENTICATED;
    }
}
