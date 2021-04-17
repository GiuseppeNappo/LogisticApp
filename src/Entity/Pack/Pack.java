package Entity.Pack;


import Entity.Client.Client;


import java.util.ArrayList;
import java.util.List;


public class Pack {
    private int id;
    private int weight;
    private Client sender;
    private Client recipient;
    private PackState state;


    public Pack(int id , int weight , PackState state ,  Client sender , Client recipient){
        this.id = id;
        this.weight = weight;
        this.sender = sender;
        this.recipient = recipient;
        this.state = state;

    }

    public Pack( int weight ,PackState  state ,  Client sender , Client recipient){
        this.weight = weight;
        this.sender = sender;
        this.recipient = recipient;
        this.state = state;
    }

    public Pack() {

    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public PackState  getState() {
        return  state;
    }

    public void setState(PackState state) {
        this.state = state;
    }

    public Client getSender() {
        return sender;
    }

    public Client getRecipient() {
        return recipient;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void PackState(PackState state) {
        this.state = state;
    }

    public void setSender(Client sender) {
        this.sender = sender;
    }

    public void setRecipient(Client recipient) {
        this.recipient = recipient;
    }


}
