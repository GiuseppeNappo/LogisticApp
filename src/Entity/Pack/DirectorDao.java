package Entity.Pack;

import Entity.Client.ClientDaoImplement;

import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public class DirectorDao {

    public Pack packBuild(ResultSet result) {
        try {
            return   PackBuilder.newBuilder().id(result.getInt(1))
                    .weight(result.getInt(2))
                    .PackStateWithInt(result.getInt(3))
                    .idSender(new ClientDaoImplement().get(result.getString(4)).getId())
                    .idRecipient(new ClientDaoImplement().get(result.getString(5)).getId())
                    .addressSender(new ClientDaoImplement().get(result.getString(4)).getAddress())
                    .telephoneSender(new ClientDaoImplement().get(result.getString(4)).getTelephone())
                    .citySender(new ClientDaoImplement().get(result.getString(4)).getCity())
                    .nameSender(new ClientDaoImplement().get(result.getString(4)).getName())
                    .surnameSender(new ClientDaoImplement().get(result.getString(4)).getSurname())
                    .addressRecipient(new ClientDaoImplement().get(result.getString(5)).getAddress())
                    .telephoneRecipient(new ClientDaoImplement().get(result.getString(5)).getTelephone())
                    .cityRecipient(new ClientDaoImplement().get(result.getString(5)).getCity())
                    .nameRecipient(new ClientDaoImplement().get(result.getString(5)).getName())
                    .surnameRecipient(new ClientDaoImplement().get(result.getString(5)).getSurname())
                    .build();
        } catch (SQLException ignore) {
            return null;
        }
    }
}
