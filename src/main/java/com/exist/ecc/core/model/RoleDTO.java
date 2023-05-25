package com.exist.ecc.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    long id;
    Set<String> fullName;
    String roleName;


    public RoleDTO roleToRoleDTO(Role role){
        if(role != null){
            List<Person> personNames = role.getPerson();
            Set<String> setOfNamesInRole = personNames
                    .stream()
                    .map(Person::getName)
                    .map(Name::toFullName)
                    .collect(Collectors.toSet());
            this.setId(role.getId());
            this.setFullName(setOfNamesInRole);
            this.setRoleName(role.getRoleName());
            return this;
        }
        return null;
    }




}
