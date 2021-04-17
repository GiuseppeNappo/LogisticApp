package Entity.Pack;

import Database.DatabaseConnection;
import Entity.Client.ClientDaoImplement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Ogni entit' avra anche il proprio DaoImplement che implementa l'interfaccia del dao oppure estende la classe astratta che
 * ha esteso la possibilità di crud operation specifiche per quella classe
 */
public class PackDaoImplement extends PackDao {



    @Override
    public Pack get(String id) {

        String query = "SELECT * FROM  PACK WHERE ID = ?";
        Pack pack = null;
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            prepareStatement.setString(1, id);
            ResultSet result = prepareStatement.executeQuery();
            pack = new DirectorDao().packBuild(result);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return pack;
    }


    @Override
    public ArrayList<Pack> getAll() {
        ArrayList<Pack> packs = new ArrayList<>();

        String query = "SELECT * FROM PACK";
        try (Statement statement = DatabaseConnection.getConnection().createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()){
                packs.add(new DirectorDao().packBuild(result));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return packs;
    }

    @Override
    public void save(Pack pack) {
        String update = "INSERT INTO PACK (ID , WEIGHT , STATE , ID_SENDER , ID_RECIPIENT) VALUES (? , ? , ? , ? , ?)";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(update)) {
            prepareStatement.setInt(2, pack.getWeight());
            prepareStatement.setInt(3, pack.getState().getNumber());
            prepareStatement.setInt(4, Integer.parseInt(new ClientDaoImplement().getMaxId()) -1);
            prepareStatement.setString(5, new ClientDaoImplement().getMaxId());
            prepareStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void updateState(Pack pack) {
        String update = " UPDATE PACK SET STATE = ?  WHERE ID = ? ";

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(update)) {
            prepareStatement.setInt(1, pack.getState().getNumber());
            prepareStatement.setInt(2, pack.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(Pack pack) {
        String drop = "DELETE FROM PACK WHERE ID = ?;";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(drop)) {
            prepareStatement.setInt(1, pack.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void deletePackFromCenter(Pack pack) {
        String drop = "DELETE FROM PACK_IN_CENTER WHERE ID_PACK = ?;";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(drop)) {
            prepareStatement.setInt(1, pack.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void deletePackFromVehicle(Pack pack) {
        String drop = "DELETE FROM PACK_IN_VEHICLE WHERE ID_PACK = ?;";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(drop)) {
            prepareStatement.setInt(1, pack.getId());
            prepareStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public String maxId() {

        String query = "SELECT MAX(ID) FROM PACK";
        String tmp = null;
        try (Statement statement = DatabaseConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            //Setto i parametri ritornati dalla query
            tmp = resultSet.getString(1);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        if (tmp != null)
            return tmp;
        return "1";
    }

    @Override
    public ArrayList<Pack> getPackFromVehicle(String idVehicle) {
         ArrayList<Pack> packs = new ArrayList<>();
        String query = "SELECT ID_PACK FROM  PACK_IN_VEHICLE WHERE ID_VEHICLE = ?";

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            prepareStatement.setString(1, idVehicle);
            ResultSet result = prepareStatement.executeQuery();
            while (result.next())
                packs.add(new PackDaoImplement().get(result.getString(1)));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return packs;
    }

    @Override
    public ArrayList<Pack> getPackFromCenter(String idCenter) {

        ArrayList<Pack> packs = new ArrayList<>();
        String query = "SELECT ID_PACK FROM  PACK_IN_CENTER WHERE ID_CENTER = ?";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            prepareStatement.setString(1, idCenter);
            ResultSet result = prepareStatement.executeQuery();
            while (result.next())
                packs.add(new PackDaoImplement().get(result.getString(1)));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return packs;
    }

}
