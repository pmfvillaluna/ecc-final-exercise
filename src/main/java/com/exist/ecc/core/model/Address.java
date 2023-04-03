package com.exist.ecc.core.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long id;
    @Column(name = "street_number")
    private String streetNumber;
    @Column
    private String barangay;
    @Column
    private String city;
    @Column
    private String zipcode;
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    public Address(String streetNumber, String barangay, String city, String zipcode) {
        this.streetNumber = streetNumber;
        this.barangay = barangay;
        this.city = city;
        this.zipcode = zipcode;
    }

    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + id +
                ", street_no='" + streetNumber + '\'' +
                ", barangay='" + barangay + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
