package Entity.CenterSorting;

import Database.DatabaseConnection;
import Entity.Company.CompanyDaoImplement;
import Entity.Pack.Pack;
import Entity.Pack.PackDaoImplement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Ogni entit' avra anche il proprio DaoImplement che implementa l'interfaccia del dao oppure estende la classe astratta che
 * ha esteso la possibilità di crud operation specifiche per quella classe
 */
public class CenterSortingDaoImplement extends CenterSortingDao {

    private ArrayList<CenterSorting> centers = new ArrayList<>();

    @Override
    public CenterSorting get(String id) {
        String query = "SELECT * FROM  CENTER_SORTING WHERE ID = ?";
        CenterSorting centerSorting = null;
        ArrayList<Pack> packs = new ArrayList<>();

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {

            prepareStatement.setInt(1, Integer.parseInt(id));
            ResultSet result = prepareStatement.executeQuery();

            centerSorting = new CenterSorting(((Integer) result.getInt(1)).toString(), result.getString(2), result.getString(3), new CompanyDaoImplement().get(result.getString(4)));
            centerSorting.setPacks(getPacks(id));

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }


        return centerSorting;
    }

    @Override
    public ArrayList<CenterSorting> getAll() {
        String query = "SELECT * FROM CENTER_SORTING";

        try (Statement statement = DatabaseConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            int i = 0;
            while (resultSet.next()) {
                centers.add(new CenterSorting(((Integer) resultSet.getInt(1)).toString(), resultSet.getString(2), resultSet.getString(3), new CompanyDaoImplement().get(resultSet.getString(4))));
                centers.get(i++).setPacks(getPacks(resultSet.getString(1)));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return centers;
    }

    @Override
    public void save(CenterSorting centerSorting) {
        String update = "INSERT INTO CENTER_SORTING (ID , CITY , CENTER_ADDRESS , VAT_NUMBER_COMPANY) VALUES (? , ? , ? , ? )";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(update)) {

            prepareStatement.setInt(1, Integer.parseInt(centerSorting.getId()));
            prepareStatement.setString(2, centerSorting.getCity());
            prepareStatement.setString(3, centerSorting.getAddress());
            prepareStatement.setString(4, centerSorting.getCompany().getVatNumber());
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        String updatePackInCenter = "INSERT INTO PACK_IN_CENTER (ID_PACK , ID_CENTER ) VALUES (? , ? )";

        for (int i = 0; i < centerSorting.getPacks().size(); i++) {
            try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(updatePackInCenter)) {

                prepareStatement.setInt(1, centerSorting.getPacks().get(i).getId());
                prepareStatement.setString(2, centerSorting.getId());
                prepareStatement.executeUpdate();

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public void insertPackInCenter(CenterSorting centerSorting) {
        String updatePackInCenter = "INSERT INTO PACK_IN_CENTER (ID_PACK , ID_CENTER ) VALUES (? , ? )";

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(updatePackInCenter)) {

            prepareStatement.setString(1, new PackDaoImplement().maxId());
            prepareStatement.setString(2, centerSorting.getId());
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(CenterSorting centerSorting) {
        String drop = "DELETE FROM   CENTER_SORTING  WHERE ID = ?";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(drop)) {

            prepareStatement.setString(1, centerSorting.getId());
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    private ArrayList<Pack> getPacks(String id) {
        String query = "SELECT ID_PACK FROM  PACK_IN_CENTER WHERE ID_CENTER = ?";

        ArrayList<Pack> packs = new ArrayList<>();
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {

            prepareStatement.setString(1, id);
            ResultSet result = prepareStatement.executeQuery();
            while (result.next())
                packs.add(new PackDaoImplement().get(result.getString(1)));

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return packs;
    }

    public CenterSorting getCenterByPackId(int id) {
        String query = "SELECT ID_CENTER FROM  PACK_IN_CENTER WHERE ID_PACK = ?";
        CenterSorting centerSorting = null;
        ArrayList<Pack> packs = new ArrayList<>();
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {

            prepareStatement.setInt(1 , id);

            ResultSet result = prepareStatement.executeQuery();
            System.out.println(result.getString(1));
            centerSorting = new CenterSortingDaoImplement().get(result.getString(1));


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return centerSorting;
    }
}
