package Entity.Worker;

import Database.DatabaseConnection;
import Entity.Company.CompanyDaoImplement;
import java.sql.*;
import java.util.ArrayList;

/**
 * Ogni entit' avra anche il proprio DaoImplement che implementa l'interfaccia del dao oppure estende la classe astratta che
 * ha esteso la possibilità di crud operation specifiche per quella classe
 */

public class WorkerDaoImplement extends WorkerDao {

    ArrayList<Worker> workers =  new ArrayList<>();

    @Override
    public boolean CheckExists(String id, String password ) throws SQLException {
        String query = "SELECT ID , PASSWORD FROM  WORKER WHERE ID = ? AND PASSWORD = ?";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            prepareStatement.setString(1 , id);
            prepareStatement.setString(2 ,password);
            ResultSet result = prepareStatement.executeQuery();
            if(result.next()) {
                return true;
            }
        } catch (SQLClientInfoException ignored) {

        }
        return false;
    }


    @Override
    public Worker get(String id) {
        String query = "SELECT ID , TYPE , ID_COMPANY FROM  WORKER WHERE ID = ?";
        Worker worker = null;
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            prepareStatement.setString(1, id);
            ResultSet result = prepareStatement.executeQuery();
            worker = new Worker(result.getString("ID"), result.getString("TYPE"), new CompanyDaoImplement().get(result.getString("ID_COMPANY")));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return worker;
    }

    @Override
    public ArrayList<Worker> getAll() {

        String query = "SELECT ID , TYPE , ID_COMPANY FROM  WORKER WHERE ID = ?";
        try (Statement statement = DatabaseConnection.getConnection().createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next())
                workers.add(new Worker(result.getString("ID"), result.getString("TYPE"), new CompanyDaoImplement().get(result.getString("ID_COMPANY"))));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return workers;
    }

    @Override
    public void save(Worker worker) {

        String update = "INSERT INTO WORKER (ID , PASSWORD , TYPE , ID_COMPANY ) VALUES (? , ? , ? , ?)";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(update)) {

            prepareStatement.setString(1, worker.getId());
            prepareStatement.setString(2, worker.getPassword());
            prepareStatement.setString(3, worker.getType());
            prepareStatement.setString(4, worker.getCompany().getVatNumber());
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    @Override
    public void delete(Worker worker) {
        String drop = "DELETE FROM PACK WHERE ID = ?;";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(drop)) {
            prepareStatement.setString(1, worker.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


}
