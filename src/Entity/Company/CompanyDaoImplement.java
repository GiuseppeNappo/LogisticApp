package Entity.Company;

import Database.DatabaseConnection;
import Entity.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompanyDaoImplement implements Dao<Company> {

    private ArrayList<Company> companies = new ArrayList<>();

    @Override
    public Company get(String id)  {

        String query = "SELECT * FROM  COMPANY WHERE VAT_NUMBER = ?";
        Company company = new Company();
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            prepareStatement.setString(1 , id);
            ResultSet result = prepareStatement.executeQuery();

            company.setName(result.getString(1));
            company.setVatNumber(result.getString(2));
        } catch (SQLException throwable ) {
            throwable.printStackTrace();
        }

        return company;
}

    @Override
    public ArrayList<Company> getAll() {
        String query = "SELECT * FROM COMPANY";
        try(Statement statement = DatabaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next())
                companies.add(new Company(resultSet.getString(1), resultSet.getString(2)));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return companies;
    }

    @Override
    public void save(Company agency)  {
        String update = "INSERT INTO COMPANY (NAME , VAT_NUMBER) VALUES (? , ?)";
         try(PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(update)){

             prepareStatement.setString(1 , agency.getName());
             prepareStatement.setString(2 , agency.getVatNumber());
             prepareStatement.executeUpdate();

         } catch (SQLException throwable) {
             throwable.printStackTrace();
         }
    }



    @Override
    public void delete(Company company) {
        String drop = "DELETE FROM   COMPANY  WHERE NAME = ? AND VAT_NUMBER = ? ;";
        try (PreparedStatement prepareStatement = DatabaseConnection.getConnection().prepareStatement(drop)) {

            prepareStatement.setString(1, company.getName());
            prepareStatement.setString(2, company.getVatNumber());
            prepareStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
