package com.exist.ecc.core.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pk_person_id")
    private Long personId;

    @Column(name = "full_name")
    private String fullName;
    @Column
    private int age;
    @Column
    private String address;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column
    private String role;

    public Person(String fullName, int age, String address, String mobileNumber, String role) {
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

    public Person() {
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
