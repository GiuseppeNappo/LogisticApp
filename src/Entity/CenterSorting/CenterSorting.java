package Entity.CenterSorting;

import Entity.Company.Company;
import Entity.Pack.Pack;

import java.util.ArrayList;

public class CenterSorting {
    private String id;
    private String city;
    private String address;
    private Company company;
    private ArrayList<Pack> packs;


    public CenterSorting(String id, String city, String address, Company company, ArrayList<Pack> packs) {

        this.id = id;
        this.city = city;
        this.address = address;
        this.company = company;
        this.packs = packs;
    }

    public CenterSorting(String id, String city, String address, Company company) {

        this.id = id;
        this.city = city;
        this.address = address;
        this.company = company;
        this.packs = new ArrayList<>();

    }

    public CenterSorting() {
        this.packs = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Company getCompany() {
        return company;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ArrayList<Pack> getPacks() {
        return packs;
    }

    public void setPacks(ArrayList<Pack> packs) {
        this.packs = packs;
    }
}
