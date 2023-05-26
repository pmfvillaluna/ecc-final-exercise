package com.exist.ecc.app.resource;

import com.exist.ecc.core.service.PersonRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person/role")
public class PersonRoleResource {
    @Autowired
    private PersonRoleService personRoleService;



}
