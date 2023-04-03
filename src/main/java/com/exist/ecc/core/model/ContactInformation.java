package com.exist.ecc.core.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person_contact_info")
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private long id;
    @Column
    private String landline;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(unique = true)
    private String email;

    public ContactInformation(String landline, String mobileNumber, String email) {
        this.landline = landline;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public ContactInformation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactInformation{" +
                "contact_id=" + id +
                ", landline='" + landline + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
