package com.JobApplication.IBANValidation;

import com.JobApplication.IBANValidation.Models.IBANModel;
import com.JobApplication.IBANValidation.Models.ValidationStatus;
import com.JobApplication.IBANValidation.Services.BankCheckingService;
import com.JobApplication.IBANValidation.Services.IBANValidationService;
import com.JobApplication.IBANValidation.Services.ResourceReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IBANValidationServiceTests {

    @Mock
    ResourceReader reader;

    @Mock
    BankCheckingService bankChecker;

    @InjectMocks
    IBANValidationService ibanValidationService;

    @ParameterizedTest
    @CsvFileSource(resources = "/IBANValidationServiceTestData.csv")
    public void when_validating_iban_should_return_appropriate_validation_data(String expectedStatus, String testIBANNumber) {

        var validationStatus = new ValidationStatus(expectedStatus);
        var codeLengths = new HashMap<String, Integer>();
        codeLengths.put("LT", 20);

        IBANModel ibanModel = new IBANModel(testIBANNumber);
        when(reader.ibanCodeLengthByCountryCodeReader()).thenReturn(codeLengths);

        ibanModel = ibanValidationService.check(ibanModel);

        Assert.assertEquals(validationStatus, ibanModel.getIsValidIBAN());
    }
}
