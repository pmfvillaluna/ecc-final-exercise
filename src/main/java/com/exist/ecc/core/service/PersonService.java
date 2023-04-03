package com.exist.ecc.core.service;

import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    public Person addPerson(Person person){
        return personRepository.save(person);
    }
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }
    public void deletePersonById(Long id){
        personRepository.deleteById(id);
    }

}
