package com.JobApplication.ibanvalidation.controllers;

import com.JobApplication.ibanvalidation.models.IBANModel;
import com.JobApplication.ibanvalidation.services.IBANValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IBANValidationController {

    @Autowired
    IBANValidationService ibanValidationService;

    @PostMapping(value = "/ibanvalidation",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IBANModel> createPost(@RequestBody IBANModel ibanModel) {

        IBANModel validatedIBANModel = ibanValidationService.check(ibanModel);

        return ResponseEntity.ok().body(validatedIBANModel);
    }
}
