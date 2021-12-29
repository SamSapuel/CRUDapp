package Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entity Office class
 */

@Entity
@Table(name = "Office")
public class Office implements Serializable {
    @Id
    @Column(name = "Department")
    private int department;

    @Column(name = "Chair_number")
    private int chairNumber;
    @Column(name = "Cabinet_number")
    private int cabinetNumber;
    @Column(name = "Country")
    private String country;
    @Column(name = "City")
    private String city;
    @Column(name = "Street")
    private String street;
    @Column(name = "Employee")
    private int employee;
    @ManyToMany
    //@JoinTable(name = "Work", joinColumns = @JoinColumn(name = "department"), inverseJoinColumns = @JoinColumn(name = "employee"))
    private List<Employees> employees;



    public Office() {

    }
    public Office(int department, int chairNumber, int cabinetNumber, String country, String city, String street){
        this.department = department;
        this.chairNumber = chairNumber;
        this.cabinetNumber = cabinetNumber;
        this.country = country;
        this.city = city;
        this.street = street;
    }
    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

    public int getChairNumber() {
        return chairNumber;
    }

    public void setChairNumber(int chairNumber) {
        this.chairNumber = chairNumber;
    }

    public int getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(int cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

}
