package com.exist.ecc.core.repository;

import com.exist.ecc.core.model.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {

    public ContactInformation findByEmail(String email);
    public ContactInformation findByLandline(String landLine);
    public ContactInformation findByMobileNumber(String mobileNumber);

}
