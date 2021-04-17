package Entity.Vehicle;

import Database.DatabaseConnection;
import Entity.Company.CompanyDaoImplement;
import java.sql.*;
import java.util.ArrayList;

/**
 * Ogni entit' avra anche il proprio DaoImplement che implementa l'interfaccia del dao oppure estende la classe astratta che
 * ha esteso la possibilità di crud operation specifiche per quella classe
 */

public class VehicleDaoImplement extends VehicleDao {

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    @Override
    public Vehicle get(String id) {
        String query = "SELECT * FROM  VEHICLE WHERE ID = ?";
        Vehicle vehicle = null;
        ResultSet result = null;
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {

            prepareStatement.setString(1, id);
            result = prepareStatement.executeQuery();

            vehicle = new Vehicle(result.getString(1), result.getString(2), result.getInt(3), new CompanyDaoImplement().get(result.getString(4) ) , result.getBoolean(6));
            vehicle.setAvailableCapacity(result.getInt(5));

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return vehicle;
    }

    @Override
    public ArrayList<Vehicle> getAll() {
        String query = "SELECT * FROM VEHICLE";

        try (Statement statement = DatabaseConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            int i = 0;
            while (resultSet.next()) {
                vehicles.add(new Vehicle(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), new CompanyDaoImplement().get(resultSet.getString(4)), resultSet.getBoolean(6)));
                vehicles.get(i).setAvailableCapacity(resultSet.getInt(5));
                i++;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public void save(Vehicle vehicle) {
        String update = "INSERT INTO VEHICLE (ID , TYPE , CAPACITY , VAT_NUMBER , AVAILABLE_CAPACITY , VEHICLE_IS_TRAVELING) VALUES (? , ? , ? , ? , ? , ?)";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(update)) {

            //Preparo la query
            prepareStatement.setString(1, vehicle.getId());
            prepareStatement.setString(2, vehicle.getType());
            prepareStatement.setInt(3, vehicle.getMaxCapacity());
            prepareStatement.setString(4, vehicle.getCompany().getVatNumber());
            prepareStatement.setInt(5, vehicle.getAvailableCapacity());
            prepareStatement.setBoolean(6 , vehicle.getVehicleIsTraveling());
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void updateAvailableCapacity(Vehicle vehicle) {
       String update =" UPDATE VEHICLE SET AVAILABLE_CAPACITY = ?  WHERE ID = ? ";

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(update)) {

            //Preparo la query
            prepareStatement.setInt(1, vehicle.getAvailableCapacity());
            prepareStatement.setString(2, vehicle.getId());
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    @Override
    public void updateStateVehicle(Vehicle vehicle){
        String update =" UPDATE VEHICLE SET VEHICLE_IS_TRAVELING = ?  WHERE ID = ? ";

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(update)) {

            //Preparo la query
            prepareStatement.setBoolean(1, vehicle.getVehicleIsTraveling());
            prepareStatement.setString(2, vehicle.getId());
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(Vehicle vehicle) {
        String drop = "DELETE FROM   VEHICLE  WHERE ID = ?";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(drop)) {

            //Preparo la query
            prepareStatement.setString(1, vehicle.getId());
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void savePackInVehicle(Vehicle vehicle) {
        String insertQuery = "INSERT INTO PACK_IN_VEHICLE ( ID_VEHICLE , ID_PACK ) VALUES (? , ? )";
        for (int i = 0; i < vehicle.getPacks().size(); i++) {
            try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(insertQuery)) {

                //Preparo la query
                prepareStatement.setString(1, vehicle.getId());
                prepareStatement.setInt(2, vehicle.getPacks().get(i).getId());
                prepareStatement.executeUpdate();

            } catch (Exception throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public ArrayList<Vehicle> getAllByVatNumber(String vatNumber) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM VEHICLE  WHERE VAT_NUMBER = ?";
        ResultSet result = null;
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            prepareStatement.setString(1, vatNumber);
            result = prepareStatement.executeQuery();
            int i = 0;
            while (result.next()) {
                vehicles.add(new Vehicle(result.getString(1), result.getString(2), result.getInt(3), new CompanyDaoImplement().get(result.getString(4) ) , result.getBoolean(6)));
                vehicles.get(i).setAvailableCapacity(result.getInt(5));
                i++;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return vehicles;
    }

    public Vehicle getVehicleByPackId(int idPack) throws SQLException {
        String query = "SELECT ID_VEHICLE FROM  PACK_IN_VEHICLE WHERE ID_PACK = ?";
        Vehicle vehicle = null;
        ResultSet result = null;
        //Tento la connessione
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {

            //Preparo la query
            prepareStatement.setInt(1, idPack);
            result = prepareStatement.executeQuery();
            //Setto i valori trovati nell'istanza agency
            vehicle = new VehicleDaoImplement().get(result.getString(1));

        } catch (SQLClientInfoException ignored) {

        }

        return vehicle;
    }
}
