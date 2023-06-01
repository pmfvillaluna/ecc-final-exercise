package com.exist.ecc.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @JsonFormat(pattern = "MM-dd-yyyy")
    @Column
    private Date birthday;
    @Column
    private double GWA;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_hired")
    private Date dateHired;
    @Column(name = "is_employed")
    private boolean isEmployed;

    @OneToMany( cascade = { CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ContactInformation> contactInformation;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "person_role",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


}
