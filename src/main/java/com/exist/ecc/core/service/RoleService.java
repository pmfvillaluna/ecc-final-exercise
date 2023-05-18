package com.exist.ecc.core.service;

import com.exist.ecc.core.model.RoleDTO;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role addRole(Role role){return roleRepository.save(role);}

    public Optional<Role> getRoleById(Long id){
        return roleRepository.findById(id);
    }

    public Set<RoleDTO> rolesToPersonRolesListDTO(){
        Set<RoleDTO> properRoles = roleRepository
                .findAll()
                .stream()
                .map((roles)->{
                    RoleDTO roleDTO = new RoleDTO();
                    return roleDTO.roleToRoleDTO(roles);
                }).collect(Collectors.toSet());
        return properRoles;
    }

    public Set<String> listRoles(){
        Set<String> roleNames = roleRepository
                .findAll()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());
        return roleNames;
    }

    public void deleteRoleById(Long id){
        roleRepository.deleteById(id);
    }

    public void update(Long id){

    }
}
