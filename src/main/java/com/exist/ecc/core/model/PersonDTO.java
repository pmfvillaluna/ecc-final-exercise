package com.exist.ecc.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long id;
    private Name name;
    private List<Address> address;
    private Date birthday;
    private Date dateHired;
    private boolean isEmployed;
    private Double gwa;
    private ContactInformation contactInformation;
    private Set<String> roleName;


    public PersonDTO personToPersonDTO(Person person){
        this.setId(person.getId());
        this.setName(person.getName());
        this.setAddress(person.getAddress());
        this.setBirthday(person.getBirthday());
        this.setDateHired(person.getDateHired());
        this.setEmployed(person.isEmployed());
        this.setContactInformation(person.getContactInformation());
        this.setGwa(person.getGWA());
        Set<String> roles = person.getRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
        this.setRoleName(roles);
        return this;
    }
}
