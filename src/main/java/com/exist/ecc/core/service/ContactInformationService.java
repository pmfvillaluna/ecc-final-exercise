package com.exist.ecc.core.service;

import com.exist.ecc.core.exception.ContactInformationNotFound;
import com.exist.ecc.core.model.ContactInformation;
import com.exist.ecc.core.repository.ContactInformationRepository;
import com.exist.ecc.core.repository.PersonRepository;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactInformationService {
    @Autowired
    private ContactInformationRepository contactInformationRepository;
    public ContactInformation createContactInformation(ContactInformation contactInformation){
        if(contactInformation != null){
            return contactInformationRepository.save(contactInformation);
        }else{
            throw new ContactInformationNotFound("Contact Information shouldn't be null");
        }
    }

    public ContactInformation updateContactInformation(Long contactInformationId, ContactInformation contactInformation) {
            ContactInformation existingContactInfo = contactInformationRepository.findById(contactInformationId)
                    .orElse(null);
            if (existingContactInfo != null) {
                existingContactInfo.setEmail(contactInformation.getEmail());
                existingContactInfo.setMobileNumber(contactInformation.getMobileNumber());
                existingContactInfo.setLandline(contactInformation.getLandline());
                return contactInformationRepository.save(existingContactInfo);
            }
        return null;
    }

    public void deleteContactInformationById(Long id){
        contactInformationRepository.deleteById(id);
    }

    public List<ContactInformation> findAllContactInformation(){
        return contactInformationRepository.findAll();
    }
}


