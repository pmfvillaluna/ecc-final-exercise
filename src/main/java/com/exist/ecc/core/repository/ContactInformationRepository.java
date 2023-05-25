package com.exist.ecc.core.repository;

import com.exist.ecc.core.model.ContactInformation;
import com.exist.ecc.core.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {


}
