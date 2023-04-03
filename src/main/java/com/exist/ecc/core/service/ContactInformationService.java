package com.exist.ecc.core.service;

import com.exist.ecc.core.repository.ContactInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactInformationService {
    @Autowired
    private ContactInformationRepository contactInformationRepository;


}
