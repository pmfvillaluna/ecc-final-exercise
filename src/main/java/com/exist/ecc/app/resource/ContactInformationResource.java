package com.exist.ecc.app.resource;

import com.exist.ecc.core.model.ContactInformation;
import com.exist.ecc.core.service.ContactInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactInformationResource {

    @Autowired
    private ContactInformationService contactInformationService;

    @PostMapping("/add")
    public ResponseEntity<ContactInformation> createContactInformation(@RequestBody ContactInformation contactInformation){
        return new ResponseEntity<>(contactInformationService.createContactInformation(contactInformation), HttpStatus.CREATED);
    }


    @PutMapping("/{id}/update")
    public ResponseEntity<ContactInformation> updateContactInformationById(
            @PathVariable("id") Long contactInfoId,
            @RequestBody ContactInformation requestedContactInformation){
        return new ResponseEntity<>(contactInformationService.updateContactInformation(
                contactInfoId, requestedContactInformation), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactInformation>> findAllContactInformation(){
        return new ResponseEntity<>(contactInformationService.findAllContactInformation(), HttpStatus.OK);
    }


}
