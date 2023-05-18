package com.exist.ecc.app.resource;

import com.exist.ecc.core.exception.RoleNotFound;
import com.exist.ecc.core.model.RoleDTO;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RoleResource {
    private RoleService roleService;


    public RoleResource(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role){
        Role newRole = roleService.addRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }


    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> getAllEmployeesByRole(@PathVariable Long id){

        Optional<Role> optionalRole = roleService.getRoleById(id);
        if(optionalRole.isEmpty()){
            throw new RoleNotFound("No Role found");
        }
        Role role = optionalRole.get();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.roleToRoleDTO(role);
        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    @GetMapping("/list/")
    public ResponseEntity<Object> getRoles(){
        return new ResponseEntity<>(roleService.rolesToPersonRolesListDTO(), HttpStatus.OK);
    }
    @GetMapping("/names")
    public ResponseEntity<Set<String>> getRoleNames(){
        return new ResponseEntity<>(roleService.listRoles(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") Long id){
        roleService.deleteRoleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
