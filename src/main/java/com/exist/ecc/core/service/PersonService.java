package com.exist.ecc.core.service;

import com.exist.ecc.core.exception.ContactInformationNotFound;
import com.exist.ecc.core.exception.PersonNotFoundException;
import com.exist.ecc.core.exception.RoleNotFoundException;
import com.exist.ecc.core.model.*;
import com.exist.ecc.core.repository.ContactInformationRepository;
import com.exist.ecc.core.repository.PersonRepository;
import com.exist.ecc.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ContactInformationRepository contactInformationRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Person createPerson(Person personEntry) {
        Person person = new Person();
        person.setName(personEntry.getName());
        person.setAddress(personEntry.getAddress());
        person.setBirthday(personEntry.getBirthday());
        person.setGWA(personEntry.getGWA());
        person.setDateHired(personEntry.getDateHired());
        person.setEmployed(personEntry.isEmployed());
        person.setContactInformation(personEntry.getContactInformation());
        person.setRoles(personEntry.getRoles());
        return personRepository.save(person);
    }


    public Person updatePersonById(Long personId, Person updatedPerson) {
        updatedPerson.setId(personId);
        return personRepository.save(updatedPerson);
    }

    public void deletePersonById(Long personId){
        personRepository.deleteById(personId);
    }

    public Person findPersonById(Long personId){
        Person person = personRepository.findById(personId)
                .orElseThrow(()-> new PersonNotFoundException(("Person with Id: " + personId + " was not found.")));
        return person;
    }

    public List<PersonDTO> personToPersonDTO(){
        List<PersonDTO> persons = personRepository.findAll().stream()
                .map(person->{
                    PersonDTO personDTO = new PersonDTO();
                    return personDTO.personToPersonDTO(person);
                }).collect(Collectors.toList());
        return persons;
    }


    public Person addPersonRoleById(Long personId, Role role) {
        Person existingPerson = findPersonById(personId);
        Set<Role> existingPersonRoles = existingPerson.getRoles();
        Optional<Role> roleFinder = roleRepository.findByRoleName(role.getRoleName());
        Role existingRole = roleFinder.get();
        if (roleFinder != null) {
            existingPersonRoles.add(existingRole);
        } else {
            existingPersonRoles.add(roleRepository.save(role));
        }

        return personRepository.save(existingPerson);
    }
    public Person deletePersonRoleById(Long personId, Long roleId) {
        Person existingPerson = findPersonById(personId);
        Role roleToDelete = existingPerson.getRoles().stream()
                .filter(role -> role.getId().equals(roleId))
                .findFirst()
                .orElseThrow(() -> new RoleNotFoundException("No role with the id: " + roleId + " was found."));
        existingPerson.getRoles().remove(roleToDelete);
        return personRepository.save(existingPerson);
    }
}
