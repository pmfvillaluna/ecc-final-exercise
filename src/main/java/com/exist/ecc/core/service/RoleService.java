package com.exist.ecc.core.service;

import com.exist.ecc.core.exception.RoleNotFoundException;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.RoleDTO;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.repository.PersonRepository;
import com.exist.ecc.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    public Optional<Role> getRoleById(Long id){
        return roleRepository.findById(id);
    }

    public Role createRole(Role role){
        if (role==null){
            throw new RoleNotFoundException("No role found in given entry");
        }else{
            return roleRepository.save(role);
        }
    }

    public Role findRoleById(Long roleId){
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()-> new RoleNotFoundException("No role found"));
        return role;
    }

    public Role updateRoleById(Long roleId, Role updatedRole){
        Role existingRole = findRoleById(roleId);
        existingRole.setRoleName(updatedRole.getRoleName());
        existingRole.setPerson(updatedRole.getPerson());
        return roleRepository.save(existingRole);
    }

    public void deleteRoleById(Long id){
        roleRepository.deleteById(id);
    }

    public List<RoleDTO> rolesToPersonRolesListDTO(){
        List<RoleDTO> properRoles = roleRepository
                .findAll()
                .stream()
                .map((roles)->{
                    RoleDTO roleDTO = new RoleDTO();
                    return roleDTO.roleToRoleDTO(roles);
                }).collect(Collectors.toList());
        return properRoles;
    }
}

/*

    public RoleDTO getAllEmployeesByRoleId(Long id){
        Role roleById = roleRepository.findById(id)
                .orElseThrow(()->  new RoleNotFoundException("No roles found"));
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.roleToRoleDTO(roleById);
        return roleDTO;
    }

    public RoleDTO getAllRoles(){
        List<Role> allRoles = roleRepository.findAll();
        List<RoleDTO> roleDTO = allRoles.stream()
                .map(RoleService::rolesToPersonRolesListDTO)
                .collect(Collectors.toSet());

    }

    public RoleDTO roleToRoleDTO(){

    }

    public List<RoleDTO> rolesToPersonRolesListDTO(){
        List<RoleDTO> properRoles = roleRepository
                .findAll()
                .stream()
                .map((roles)->{
                    RoleDTO roleDTO = new RoleDTO();
                    return roleDTO.roleToRoleDTO(roles);
                }).collect(Collectors.toList());
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
 */