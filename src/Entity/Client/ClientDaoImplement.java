package Entity.Client;

import Database.DatabaseConnection;
import Entity.CenterSorting.CenterSorting;
import Entity.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDaoImplement implements Dao<Client> {

    ArrayList<Client> clients = new ArrayList<>();

    @Override
    public Client get(String id) {
        String query = "SELECT * FROM  CLIENT WHERE ID = ?";
        Client client = null;

        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {

            prepareStatement.setString(1, id);
            ResultSet result = prepareStatement.executeQuery();

            client = new Client(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return client;

    }

    @Override
    public ArrayList<Client> getAll() {
        String query = "SELECT * FROM CLIENT";
        try (Statement statement = DatabaseConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                clients.add(new Client(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return clients;
    }

    @Override
    public void save(Client client) {
        String update = "INSERT INTO CLIENT ( ADDRESS , TELEPHONE , CITY , NAME , SURNAME) VALUES ( ? , ? , ? , ? , ?)";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(update)) {

            prepareStatement.setString(1, client.getAddress());
            prepareStatement.setString(2, client.getTelephone());
            prepareStatement.setString(3, client.getCity());
            prepareStatement.setString(4, client.getName());
            prepareStatement.setString(5, client.getSurname());
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(Client client) {
        String drop = "DELETE FROM   CLIENT  WHERE ID = ?";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(drop)) {


            prepareStatement.setString(1, client.getId());
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public String getMaxId() {
        String query = "SELECT MAX(ID) FROM CLIENT";
        String tmp = null;
        try (Statement statement = DatabaseConnection.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            tmp = resultSet.getString(1);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        if(tmp != null)
            return tmp;
        return "1";
    }
}
