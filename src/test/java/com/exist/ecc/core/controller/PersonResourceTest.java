package com.exist.ecc.core.controller;

import com.exist.ecc.app.resource.PersonResource;
import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.service.PersonService;
import org.junit.jupiter.api.Assertions;
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

@ExtendWith(MockitoExtension.class)
public class PersonResourceTest {
        @InjectMocks
        private PersonResource personResource;

        @Mock
        private PersonService personService;

        @Test
        public void testCreatePerson() {
            // Arrange
            Person person = new Person();
            Mockito.when(personService.createPerson(Mockito.any(Person.class))).thenReturn(person);

            // Act
            ResponseEntity<Person> response = personResource.createPerson(person);

            // Assertions
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
            Assertions.assertEquals(person, response.getBody());
            Mockito.verify(personService, Mockito.times(1)).createPerson(Mockito.any(Person.class));
        }

        @Test
        public void testCreatePersonWithNull() {
            // Arrange

            // Act
            ResponseEntity<Person> response = personResource.createPerson(null);

            // Assertions
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            Assertions.assertNull(response.getBody());
            Mockito.verify(personService, Mockito.never()).createPerson(Mockito.any(Person.class));
        }

        @Test
        public void testUpdatePerson() {
            // Arrange
            Long personId = 1L;
            Person person = new Person();
            Mockito.when(personService.updatePersonById(personId, Mockito.any(Person.class))).thenReturn(person);

            // Act
            ResponseEntity<Person> response = personResource.updatePerson(personId, person);

            // Assertions
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(person, response.getBody());
            Mockito.verify(personService, Mockito.times(1)).updatePersonById(personId, Mockito.any(Person.class));
        }

        @Test
        public void testUpdatePersonWithNull() {
            // Arrange
            Long personId = 1L;

            // Act
            ResponseEntity<Person> response = personResource.updatePerson(personId, null);

            // Assertions
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            Assertions.assertNull(response.getBody());
            Mockito.verify(personService, Mockito.never()).updatePersonById(personId, Mockito.any(Person.class));
        }

        @Test
        public void testDeletePersonById() {
            // Arrange
            Long personId = 1L;

            // Act
            ResponseEntity<?> response = personResource.deletePerson(personId);

            // Assertions
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            Assertions.assertNull(response.getBody());
            Mockito.verify(personService, Mockito.times(1)).deletePersonById(personId);
        }

        @Test
        public void testAddPersonRoleById() {
            // Arrange
            Long personId = 1L;
            Long roleId = 1L;
            Person person = new Person();
            Mockito.when(personService.(personId, Mockito.any(Role.class))).thenReturn(person);

            // Act
            ResponseEntity<Person> response = personResource.addPersonRoleById(personId, new Role());

            // Assertions
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(person, response.getBody());
            Mockito.verify(personService, Mockito.times(1)).addPersonRoleById(personId, Mockito.any(Role.class));
        }

        @Test
        public void testAddPersonRoleByIdWithNonExistingPersonId() {
            // Arrange
            Long personId = 1L;
            Long roleId = 1L;
            Mockito.when(personService.addPersonRoleById(personId, Mockito.any(Role.class))).thenReturn(null);

            // Act
            ResponseEntity<Person> response = personResource.addPersonRoleById(personId, new Role());

            // Assertions
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            Assertions.assertNull(response.getBody());
            Mockito.verify(personService, Mockito.times(1)).addPersonRoleById(personId, Mockito.any(Role.class));
        }

        @Test
        public void testRemovePersonRoleById() {
            // Arrange
            Long personId = 1L;
            Long roleId = 1L;
            Person person = new Person();
            Mockito.when(personService.removePersonRoleById(personId, roleId)).thenReturn(person);

            // Act
            ResponseEntity<Person> response = personResource.removePersonRoleById(personId, roleId);

            // Assertions
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(person, response.getBody());
            Mockito.verify(personService, Mockito.times(1)).removePersonRoleById(personId, roleId);
        }

        @Test
        public void testRemovePersonRoleByIdWithNonExistingPersonId() {
            // Arrange
            Long personId = 1L;
            Long roleId = 1L;
            Mockito.when(personService.removePersonRoleById(personId, roleId)).thenReturn(null);

            // Act
            ResponseEntity<Person> response = personResource.removePersonRoleById(personId, roleId);

            // Assertions
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            Assertions.assertNull(response.getBody());
            Mockito.verify(personService, Mockito.times(1)).removePersonRoleById(personId, roleId);
        }

        @Test
        public void testGetAllPersons() {
            // Arrange
            List<Person> personList = new ArrayList<>();
            Mockito.when(personService.getAllPersons()).thenReturn(personList);

            // Act
            ResponseEntity<List<Person>> response = personResource.getAllPersons();

            // Assertions
            Assertions.assertNotNull(response);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(personList, response.getBody());
            Mockito.verify(personService, Mockito.times(1)).getAllPersons();
        }
    }
}
