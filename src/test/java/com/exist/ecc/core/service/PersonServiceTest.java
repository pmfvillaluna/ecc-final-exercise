package com.exist.ecc.core.service;

import com.exist.ecc.core.exception.PersonNotFoundException;
import com.exist.ecc.core.model.Name;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.PersonDTO;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void testCreatePerson() {
        
        Person person = new Person();
        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

        
        Person result = personService.createPerson(person);

        
        Assertions.assertNotNull(result);
        Mockito.verify(personRepository, Mockito.times(1)).save(Mockito.any(Person.class));
    }

    @Test
    public void testUpdatePersonById() {
        
        Long personId = 1L;
        Person existingPerson = new Person();
        existingPerson.setId(personId);
        existingPerson.setName(new Name()); // Initialize the Name object
        existingPerson.getName().setFirstName("Joseph");

        Person updatedPerson = new Person();
        updatedPerson.setName(new Name()); // Initialize the Name object
        updatedPerson.getName().setFirstName("John");

        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(updatedPerson);

        
        Person result = personService.updatePersonById(personId, updatedPerson);

        
        Assertions.assertNotNull(result);
        Assertions.assertEquals(updatedPerson.getName().getFirstName(), result.getName().getFirstName());
    }



    @Test
    public void testDeletePersonById() {
        
        Long personId = 1L;

        
        personService.deletePersonById(personId);

        
        Mockito.verify(personRepository, Mockito.times(1)).deleteById(personId);
    }

    @Test
    public void testFindPersonById() {
        
        Long personId = 1L;
        Person person = new Person();
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        
        Person result = personService.findPersonById(personId);

        
        Assertions.assertNotNull(result);
        Mockito.verify(personRepository, Mockito.times(1)).findById(personId);
    }

    @Test
    @DisplayName("Return exception when finding by null Id")
    public void testFindPersonByIdWithNonExistingId() {
        Long personId = 1L;
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.empty());

        
        Throwable exception = Assertions.assertThrows(PersonNotFoundException.class, () -> {
            personService.findPersonById(personId);
        });

        
        Assertions.assertEquals("Person with Id: " + personId + " was not found.", exception.getMessage());
        Mockito.verify(personRepository, Mockito.times(1)).findById(personId);
    }



    @Test
    public void testGetAllPersons() {
        
        List<Person> personList = new ArrayList<>();
        Mockito.when(personRepository.findAll()).thenReturn(personList);

        
        List<PersonDTO> result = personService.listAllPersonToPersonDTO();

        
        Assertions.assertNotNull(result);
        Assertions.assertEquals(personList.size(), result.size());
        Mockito.verify(personRepository, Mockito.times(1)).findAll();
    }
}

