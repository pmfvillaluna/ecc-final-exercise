package com.exist.ecc.app.resource;

import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonResource {

    private PersonService personService;

    public PersonResource(PersonService personService){
        this.personService = personService;
    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPeople(@RequestBody Person person){
        Person newPerson = personService.addPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getAll(){
        List<Person> people = personService.getAllPersons();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        personService.deletePersonById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
