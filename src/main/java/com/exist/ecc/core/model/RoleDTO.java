package com.exist.ecc.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    long id;
    Set<Name> personName;
    String roleName;
    private static final Logger log = LoggerFactory.getLogger(RoleDTO.class);
    public RoleDTO roleToRoleDTO(Role role) {
        if (role == null) {
            return new RoleDTO();
        }
        List<Person> personNames = role.getPerson();
        Set<Name> setOfNamesInRole = (personNames != null && !personNames.isEmpty())
                ? personNames.stream().map(Person::getName).collect(Collectors.toSet())
                : Collections.emptySet();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setPersonName(setOfNamesInRole);
        roleDTO.setRoleName(role.getRoleName());
        return roleDTO;
    }

}
