package com.JobApplication.IBANValidation.Controllers;

import com.JobApplication.IBANValidation.Models.IBANModel;
import com.JobApplication.IBANValidation.Services.IBANValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class IBANController {

    @Autowired
    IBANValidationService ibanValidationService;

    @PostMapping(value = "/ibanvalidation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IBANModel> createPost(@RequestBody IBANModel ibanModel) {

        var validatedIBANModel = ibanValidationService.check(ibanModel);

        return ResponseEntity.created(URI.create(String.format("/response"))).body(validatedIBANModel);
    }
}
