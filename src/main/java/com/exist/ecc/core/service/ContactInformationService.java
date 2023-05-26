package com.exist.ecc.core.service;

import com.exist.ecc.core.exception.ContactInformationNotFound;
import com.exist.ecc.core.exception.PersonNotFoundException;
import com.exist.ecc.core.model.ContactInformation;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.PersonDTO;
import com.exist.ecc.core.repository.ContactInformationRepository;
import com.exist.ecc.core.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContactInformationService {
    @Autowired
    private ContactInformationRepository contactInformationRepository;
    @Autowired
    private PersonRepository personRepository;

    public ContactInformation createContactInformation(ContactInformation contactInformation){
        if(contactInformation != null){
            return contactInformationRepository.save(contactInformation);
        }else{
            throw new ContactInformationNotFound("Contact Information shouldn't be null");
        }
    }

    public ContactInformation updateContactInformationById(Long contactInformationId, ContactInformation contactInformation) {
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
    public PersonDTO addContactInformationByPersonId(Long personId, ContactInformation contactInformation) {
        Person personRecord = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("No person was found with ID: " + personId));
        PersonDTO personDTO = new PersonDTO();
        if (contactInformation != null) {
            List<ContactInformation> personContactInformation = personRecord.getContactInformation();
            if (personContactInformation == null) {
                personContactInformation = new ArrayList<>();
            }
            ContactInformation updateContacts = new ContactInformation();
            updateContacts.setEmail(contactInformation.getEmail());
            updateContacts.setLandline(contactInformation.getLandline());
            updateContacts.setMobileNumber(contactInformation.getMobileNumber());

            personContactInformation.add(updateContacts);
            personRecord.setContactInformation(personContactInformation);
            personRepository.save(personRecord);
            personDTO.personToPersonDTO(personRecord);
        }
        return personDTO;
    }


    public void deleteContactInformationByPersonIdAndContactId(Long personId, Long contactInformationId) {
        Optional<Person> optionalPerson = personRepository.findById(personId);

        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            List<ContactInformation> contactInformationList = person.getContactInformation();

            Optional<ContactInformation> optionalContactInformation = contactInformationList.stream()
                    .filter(contactInformation -> Objects.equals(contactInformation.getId(), contactInformationId))
                    .findFirst();

            if (optionalContactInformation.isPresent()) {
                ContactInformation contactInformation = optionalContactInformation.get();
                contactInformationList.remove(contactInformation);
                personRepository.save(person);
            }
        }
    }

    public void deleteContactInformation(Long contactInformationId) {
        contactInformationRepository.deleteById(contactInformationId);
    }



    public List<ContactInformation> findAllContactInformation(){
        return contactInformationRepository.findAll();
    }



}



