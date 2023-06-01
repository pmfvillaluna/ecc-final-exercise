package com.exist.ecc.core.controller;

import com.exist.ecc.app.resource.PersonResource;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.PersonDTO;
import com.exist.ecc.core.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PersonResourceTest {
        @InjectMocks
        private PersonResource personResource;

        @Mock
        private PersonService personService;

        @Test
        public void testCreatePerson() {
            Person person = new Person();
            Mockito.when(personService.createPerson(Mockito.any(Person.class))).thenReturn(person);

            ResponseEntity<Person> response = personResource.createPerson(person);

            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
            Assertions.assertEquals(person, response.getBody());
            Mockito.verify(personService, Mockito.times(1)).createPerson(Mockito.any(Person.class));
        }

        @Test
        public void testCreatePersonWithNull() {
            ResponseEntity<Person> response = personResource.createPerson(null);

            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            Assertions.assertNull(response.getBody());
            Mockito.verify(personService, Mockito.never()).createPerson(Mockito.any(Person.class));
        }

        @Test
        public void testUpdatePerson() {
            Long personId = 1L;
            Person person = new Person();
            Mockito.when(personService.updatePersonById(personId, Mockito.any(Person.class))).thenReturn(person);

            ResponseEntity<Person> response = personResource.updatePerson(personId, person);

            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(person, response.getBody());
            Mockito.verify(personService, Mockito.times(1)).updatePersonById(personId, Mockito.any(Person.class));
        }

        @Test
        public void testUpdatePersonWithNull() {
            Long personId = 1L;

            ResponseEntity<Person> response = personResource.updatePerson(personId, null);

            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            Assertions.assertNull(response.getBody());
            Mockito.verify(personService, Mockito.never()).updatePersonById(personId, Mockito.any(Person.class));
        }

        @Test
        public void testDeletePersonById() {
            
            Long personId = 1L;
            
            ResponseEntity<?> response = personResource.deletePerson(personId);

            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            Assertions.assertNull(response.getBody());
            Mockito.verify(personService, Mockito.times(1)).deletePersonById(personId);
        }




}
