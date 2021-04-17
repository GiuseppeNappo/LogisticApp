package Entity.Company;

public class Company {
    private String Name;
    private String vatNumber;

    public Company(String Name , String vatNumber){
        this.Name = Name;
        this.vatNumber = vatNumber;
    }

    public Company(){}

    public String getName() {
        return Name;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
}
