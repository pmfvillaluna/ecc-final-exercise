package com.exist.ecc.app.resource;


import com.exist.ecc.core.model.RoleDTO;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @GetMapping("/list/")
    public ResponseEntity<Object> getRoles(){
        return new ResponseEntity<>(roleService.rolesToPersonRolesListDTO(), HttpStatus.OK);
    }

}


/*
    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> getAllEmployeesByRoleId(@PathVariable Long id){

        return new ResponseEntity<>(roleService.getAllPersonByRoleId(id), HttpStatus.OK);
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


 */