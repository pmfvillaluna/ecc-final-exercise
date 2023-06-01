package com.exist.ecc.app.resource;

import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.PersonDTO;
import com.exist.ecc.core.service.PersonRoleService;
import com.exist.ecc.core.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonResource {
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRoleService personRoleService;

    @PostMapping("/create")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        Person newPerson = personService.createPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> listPersonById(@PathVariable Long id){
        return new ResponseEntity<>(personService.findPersonDTOById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonDTO>> listAllPersons(){
        return new ResponseEntity<>(personService.listAllPersonToPersonDTO(), HttpStatus.OK);
    }

    @GetMapping("/GWA/")
    public ResponseEntity<List<PersonDTO>> listAllPersonByGWA(){
        return new ResponseEntity<>(personService.listPersonGWA(), HttpStatus.OK);
    }
    @GetMapping("/GWA/DESC")
    public ResponseEntity<List<PersonDTO>> listAllPersonByGWADESC(){
        return new ResponseEntity<>(personService.listPersonGWADESC(), HttpStatus.OK);
    }
    @GetMapping("/dateHired/")
    public ResponseEntity<List<PersonDTO>> listAllPersonByDateHired(){
        return new ResponseEntity<>(personService.listPersonDateHired(), HttpStatus.OK);
    }
    @GetMapping("/dateHired/DESC")
    public ResponseEntity<List<PersonDTO>> listAllPersonByDateHiredDESC(){
        return new ResponseEntity<>(personService.listPersonDateHired(), HttpStatus.OK);
    }
    @GetMapping("/lastName/")
    public ResponseEntity<List<PersonDTO>> listAllPersonByLastName(){
        return new ResponseEntity<>(personService.listPersonLastName(), HttpStatus.OK);
    }
    @GetMapping("/lastName/DESC")
    public ResponseEntity<List<PersonDTO>> listAllPersonByLastNameDESC(){
        return new ResponseEntity<>(personService.listPersonLastNameDESC(), HttpStatus.OK);
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


    @DeleteMapping("/{personId}/role/delete/{roleId}")
    public ResponseEntity<PersonDTO> deletePersonRoleByRoleId(@PathVariable() long personId, @PathVariable long roleId){
        return new ResponseEntity<>(
                personRoleService.convertPersonToPersonDTO(personRoleService.removeRoleFromPersonByRoleId(personId, roleId)), HttpStatus.OK);
    }

    @PutMapping("/{personId}/role/add/{roleId}")
    public ResponseEntity<PersonDTO> addPersonRoleByRoleId(@PathVariable() long personId, @PathVariable long roleId){
        return new ResponseEntity<>(
                personRoleService.convertPersonToPersonDTO(personRoleService.addRoleToPerson(personId, roleId)), HttpStatus.OK);
    }
}
