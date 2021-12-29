package Model;

import Model.Office;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity Employee class
 */

@Entity
@Table (name = "Employees")
public class Employees implements Serializable {
    @Id
    @Column(name = "PersonalID")
    private int personalId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Surname")
    private String surname;
    //@Column(name = "tel_number")
    @Column(name = "Country")
    private String country;
    @Column(name = "City")
    private String city;
    @Column(name = "Street")
    private String street;
    @ManyToMany(mappedBy = "employees")
    //@JoinTable(name = "Office", joinColumns = @JoinColumn(name = "department"))
    private List<Office> offices;


    public Employees() {
    }

    public Employees(int id, String name, String surname, String country, String city, String street) {
        this.personalId = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public long getId() {
        return personalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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


    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public void setId(String id) {
        this.personalId = Integer.parseInt(id);
    }
    public void setId(int id) {
        this.personalId = id;
    }
}
