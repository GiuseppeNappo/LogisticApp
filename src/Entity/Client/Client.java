package Entity.Client;

public class Client {
    private String id;
    private String address;
    private String telephone;
    private String city;
    private String name;
    private String surname;

    public Client(String id , String address , String telephone , String city ,String name , String surname){
        this.id = id;
        this.address = address;
        this.telephone = telephone;
        this.city = city;
        this.name = name;
        this.surname = surname;
    }

    public Client( String address , String telephone , String city ,String name , String surname){
        this.address = address;
        this.telephone = telephone;
        this.city = city;
        this.name = name;
        this.surname = surname;
    }

    public Client(){}

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
