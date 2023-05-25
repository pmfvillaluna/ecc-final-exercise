package com.exist.ecc.app.resource;

import com.exist.ecc.core.model.*;
import com.exist.ecc.core.service.ContactInformationService;
import com.exist.ecc.core.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonResource {
    Logger logger = LoggerFactory.getLogger(PersonResource.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private ContactInformationService contactInformationService;


    @PostMapping("/create")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        Person newPerson = personService.createPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonDTO>> getAllPersons(){
        return new ResponseEntity<>(personService.personToPersonDTO(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id) {
        personService.deletePersonById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, @RequestBody Person updatedPerson){
        return new ResponseEntity<>(personService.updatePersonById(id, updatedPerson), HttpStatus.OK);
    }





}
