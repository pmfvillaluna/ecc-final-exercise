package com.exist.ecc.core.service;

import com.exist.ecc.core.exception.PersonNotFoundException;
import com.exist.ecc.core.exception.RoleNotFoundException;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.PersonDTO;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;


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
    public List<PersonDTO> listAllPersonToPersonDTO(){
        return personRepository.findAll().stream()
                .map(person->{
                    PersonDTO personDTO = new PersonDTO();
                    return personDTO.personToPersonDTO(person);
                }).collect(Collectors.toList());
    }

    public List<PersonDTO> convertPersonListToPersonDTO(List<Person> personList){
        return personList.stream()
                .map(person->{
                    PersonDTO personDTO = new PersonDTO();
                    return personDTO.personToPersonDTO(person);
                }).collect(Collectors.toList());
    }

    public List<PersonDTO> listPersonGWA(){
        List<Person> personList = personRepository.listPersonByGWA();
        return convertPersonListToPersonDTO(personList);
    }
    public List<PersonDTO> listPersonGWADESC(){
        List<Person> personList = personRepository.listPersonByGWADESC();
        return convertPersonListToPersonDTO(personList);
    }
    public List<PersonDTO> listPersonDateHired(){
        List<Person> personList = personRepository.listPersonByDateHired();
        return convertPersonListToPersonDTO(personList);
    }
    public List<PersonDTO> listPersonDateHiredDESC(){
        List<Person> personList = personRepository.listPersonByDateHiredDESC();
        return convertPersonListToPersonDTO(personList);
    }
    public List<PersonDTO> listPersonLastName(){
        List<Person> personList = personRepository.listPersonByLastName();
        return convertPersonListToPersonDTO(personList);
    }
    public List<PersonDTO> listPersonLastNameDESC(){
        List<Person> personList = personRepository.listPersonByLastNameDESC();
        return convertPersonListToPersonDTO(personList);
    }


    public Person updatePersonById(Long personId, Person updatedPerson) {
        updatedPerson.setId(personId);
        return personRepository.save(updatedPerson);
    }

    public void deletePersonById(Long personId){
        personRepository.deleteById(personId);
    }

    public Person findPersonById(Long personId){
        return personRepository.findById(personId)
                .orElseThrow(()-> new PersonNotFoundException(("Person with Id: " + personId + " was not found.")));
    }

    public PersonDTO convertPersonToPersonDTO(Person person){
        PersonDTO personDTO = new PersonDTO();
        return personDTO.personToPersonDTO(person);
    }


    public PersonDTO deletePersonRoleByRoleId(Long personId, Long roleId) {
        Person existingPerson = findPersonById(personId);
        Role roleToDelete = existingPerson.getRoles().stream()
                .filter(role -> role.getId().equals(roleId))
                .findFirst()
                .orElseThrow(() -> new RoleNotFoundException("No role with the id: " + roleId + " was found."));
        existingPerson.getRoles().remove(roleToDelete);
        personRepository.save(existingPerson);
        return convertPersonToPersonDTO(existingPerson);
    }




}
