package com.exist.ecc.core.service;

import com.exist.ecc.core.exception.RoleNotFoundException;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.model.RoleDTO;
import com.exist.ecc.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public RoleDTO findRoleById(Long roleId){
        Role foundRole = roleRepository.findById(roleId)
                .orElseThrow(()-> new RoleNotFoundException("No role found"));

        RoleDTO roleDTO = new RoleDTO();
        return roleDTO.roleToRoleDTO(foundRole);
    }
    public List<RoleDTO> findRolesThenConvertToRoleDTO(){
        return roleRepository
                .findAll()
                .stream()
                .map((roles)->{
                    RoleDTO roleDTO = new RoleDTO();
                    return roleDTO.roleToRoleDTO(roles);
                }).collect(Collectors.toList());
    }
    public RoleDTO updateRoleById(Long id, Role updatedRole) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role existingRole = optionalRole.get();
            existingRole.setRoleName(updatedRole.getRoleName());
            existingRole.setPerson(updatedRole.getPerson());
            roleRepository.save(existingRole);
            RoleDTO displayRoleAsDTO = new RoleDTO();
            return displayRoleAsDTO.roleToRoleDTO(existingRole);
        } else {
            throw new RoleNotFoundException("Role not found with id: " + id);
        }
    }
    public void deleteRoleById(Long id){
        roleRepository.deleteById(id);
    }
}