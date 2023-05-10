package com.exist.ecc.core.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_name_id")
    private Name name;
    //firstName, lastName, MiddleName, suffix, Title

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "fk_address_id")
    private List<Address> address;

    @Column
    private Date birthday;
    @Column
    private double GWA;

    @Column(name = "date_hired")
    private Date dateHired;
    @Column(name = "is_employed")
    private boolean isEmployed;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_contact_info")
    private ContactInformation contactInformation;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_role_list",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public Person(Name name, List<Address> address, Date birthday, double GWA, Date dateHired, boolean isEmployed, ContactInformation contactInformation, Set<Role> roles) {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.GWA = GWA;
        this.dateHired = dateHired;
        this.isEmployed = isEmployed;
        this.contactInformation = contactInformation;
        this.roles = roles;
    }

    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getGWA() {
        return GWA;
    }

    public void setGWA(double GWA) {
        this.GWA = GWA;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public boolean isEmployed() {
        return isEmployed;
    }

    public void setEmployed(boolean employed) {
        isEmployed = employed;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                ", birthday=" + birthday +
                ", GWA=" + GWA +
                ", dateHired=" + dateHired +
                ", isEmployed=" + isEmployed +
                ", contactInformation=" + contactInformation +
                ", roles=" + roles +
                '}';
    }
}
