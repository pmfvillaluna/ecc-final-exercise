package com.exist.ecc.core.service;

import com.exist.ecc.exception.PersonNotFoundException;
import com.exist.ecc.exception.RoleNotFoundException;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.PersonDTO;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.repository.PersonRepository;
import com.exist.ecc.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonRoleService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;
    

    public PersonDTO convertPersonToPersonDTO(Person person){
        PersonDTO personDTO = new PersonDTO();
        return personDTO.personToPersonDTO(person);
    }


    public Person addRoleToPerson(Long personId, Long roleId) {
        Person existingPerson = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person with ID " + personId + " not found"));
        Role roleToAdd = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role with ID " + roleId + " not found"));
        existingPerson.getRoles().add(roleToAdd);
        return  personRepository.save(existingPerson);

    }


    public Person removeRoleFromPersonByRoleId(long personId, long roleId){
        Person existingPerson = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person with ID " + personId + " not found"));
        Role roleToRemove = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role with ID " + roleId + " not found"));
        existingPerson.getRoles().remove(roleToRemove);
        return personRepository.save(existingPerson);
    }
}
