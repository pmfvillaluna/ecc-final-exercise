package com.exist.ecc.app.resource;

import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> listOfRoles = roleService.getRoles();
        return new ResponseEntity<>(listOfRoles, HttpStatus.OK);
    }
}
