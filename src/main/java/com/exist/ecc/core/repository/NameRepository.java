package com.exist.ecc.core.repository;

import com.exist.ecc.core.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NameRepository extends JpaRepository<Name, Long> {
}