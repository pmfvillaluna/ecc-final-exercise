package com.exist.ecc.core.model;

import java.util.Set;
import java.util.stream.Collectors;

public class RoleDTO {
    long id;
    Set<String> fullName;
    String roleName;

    public RoleDTO(long id, Set<String> fullName, String roleName) {
        this.id = id;
        this.fullName = fullName;
        this.roleName = roleName;
    }

    public RoleDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<String> getFullName() {
        return fullName;
    }

    public void setFullName(Set<String> fullName) {
        this.fullName = fullName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    @Override
    public String toString() {
        return "RoleDTO{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", fullName=" + fullName +
                '}';
    }

    public RoleDTO roleToRoleDTO(Role role){
        if(role != null){
            this.setId(role.getId());
            Set<Person> personNames = role.getPerson();
            Set<String> setOfNamesInRole = personNames
                    .stream()
                    .map(Person::getName)
                    .map(Name::toFullName)
                    .collect(Collectors.toSet());
            for (String S : setOfNamesInRole) {
                System.out.println(S);
            }
            this.setFullName(setOfNamesInRole);
            this.setRoleName(role.getRoleName());
            return this;
        }
        return null;
    }


}
