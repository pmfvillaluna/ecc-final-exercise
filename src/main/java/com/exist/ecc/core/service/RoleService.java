package com.exist.ecc.core.service;

import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role addRole(Role role){return roleRepository.save(role);}

    public List<Role> getRoles(){return roleRepository.findAll();}
}
