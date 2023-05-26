package com.exist.ecc.app.resource;


import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.model.RoleDTO;
import com.exist.ecc.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleResource {
    @Autowired
    private RoleService roleService;

    @GetMapping("/list/")
    public ResponseEntity<List<RoleDTO>> getRoles(){
        return new ResponseEntity<>(roleService.findRolesThenConvertToRoleDTO(), HttpStatus.OK);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id){
        return new ResponseEntity<>(roleService.findRoleById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Role> createRole(@RequestBody Role createdRole){
        return new ResponseEntity<>(roleService.createRole(createdRole), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody Role role){
        return new ResponseEntity<>(roleService.updateRoleById(id, role), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable("id") long roleId){
        roleService.deleteRoleById(roleId);
        return new ResponseEntity<>(HttpStatus.GONE);
    }


}
