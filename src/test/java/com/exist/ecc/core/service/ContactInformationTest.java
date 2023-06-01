package com.exist.ecc.core.service;

import com.exist.ecc.exception.ContactInformationNotFound;
import com.exist.ecc.core.model.ContactInformation;
import com.exist.ecc.core.repository.ContactInformationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ContactInformationTest {
    @Mock
    private ContactInformationRepository contactInformationRepository;

    @InjectMocks
    private ContactInformationService contactInformationService;

    @Test
    @DisplayName("Create and save contact information")
    void testCreateContactInformation(){
        ContactInformation contactInformation = new ContactInformation();
        Mockito.when(contactInformationRepository.save(Mockito.any(ContactInformation.class))).thenReturn(contactInformation);

        ContactInformation resultContact = contactInformationService.createContactInformation(contactInformation);

        Assertions.assertNotNull(resultContact);
        Mockito.verify(contactInformationRepository, Mockito.times(1)).save(Mockito.any(ContactInformation.class));
    }

    @Test
    @DisplayName("Create and save null Contact Information and Throw exception")
    public void testCreateContactInformationWithNull_ThrowContactInformationNotFoundException() {
        Assertions.assertThrows(ContactInformationNotFound.class, () -> {
            contactInformationService.createContactInformation(null);
        });
    }

    @Test
    @DisplayName("Update contact information")
    public void testUpdateContactInformation() {
        Long contactInformationId = 1L;
        ContactInformation existing = new ContactInformation();
        existing.setId(contactInformationId);

        ContactInformation updated = new ContactInformation();
        updated.setEmail("newemail@example.com");

        Mockito.when(contactInformationRepository.findById(contactInformationId)).thenReturn(Optional.of(existing));
        Mockito.when(contactInformationRepository.save(Mockito.any(ContactInformation.class))).thenReturn(updated);

        ContactInformation result = contactInformationService.updateContactInformationById(contactInformationId, updated);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(updated.getEmail(), result.getEmail());
        Mockito.verify(contactInformationRepository, Mockito.times(1)).findById(contactInformationId);
        Mockito.verify(contactInformationRepository, Mockito.times(1)).save(Mockito.any(ContactInformation.class));
    }

    @Test
    @DisplayName("Update to fail due to no Id")
    public void testUpdateContactInformationWithNonExistingId() {

        Long contactInformationId = 1L;
        ContactInformation updatedContactInformation = new ContactInformation();
        updatedContactInformation.setEmail("newemail@example.com");
        Mockito.when(contactInformationRepository.findById(Mockito.eq(contactInformationId))).thenReturn(Optional.empty());

        ContactInformation result = contactInformationService.updateContactInformationById(contactInformationId, updatedContactInformation);

        Assertions.assertNull(result);
        Mockito.verify(contactInformationRepository, Mockito.times(1)).findById(contactInformationId);
        Mockito.verify(contactInformationRepository, Mockito.never()).save(Mockito.any(ContactInformation.class));
    }

    @Test
    @DisplayName("Delete by Contact information ID")
    public void testDeleteContactInformationById() {
        Long contactInformationId = 1L;

        contactInformationService.deleteContactInformation(contactInformationId);

        Mockito.verify(contactInformationRepository, Mockito.times(1)).deleteById(contactInformationId);
    }

    @Test
    @DisplayName("Find all Contact Information")
    public void testFindAllContactInformation() {
        List<ContactInformation> contactInformationList = new ArrayList<>();
        Mockito.when(contactInformationRepository.findAll()).thenReturn(contactInformationList);

        List<ContactInformation> result = contactInformationService.findAllContactInformation();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(contactInformationList.size(), result.size());
        Mockito.verify(contactInformationRepository, Mockito.times(1)).findAll();
    }

}

