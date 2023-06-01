package com.exist.ecc.core.repository;

import com.exist.ecc.core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Optional<Role> findByRoleName(String roleName);

}
