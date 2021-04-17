package Entity.Worker;

import Entity.Company.Company;

public class Worker  {
    private String id;
    private String password;
    private String type;
    private Company company;

    public Worker(String id , String password , String type , Company company){
        this.id = id;
        this.password = password;
        this.type = type;
        this.company = company;
    }

    public Worker(){}

    public Worker(String id, String type, Company company) {
        this.id = id;
        this.type = type;
        this.company = company;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public String getType() {
        return type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setType(String type) {
        this.type = type;
    }
}
