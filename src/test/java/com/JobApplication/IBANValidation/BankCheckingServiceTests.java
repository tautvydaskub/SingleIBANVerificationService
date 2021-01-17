package com.JobApplication.IBANValidation;

import com.JobApplication.IBANValidation.Models.BankStatus;
import com.JobApplication.IBANValidation.Models.IBANModel;
import com.JobApplication.IBANValidation.Models.ValidationStatus;
import com.JobApplication.IBANValidation.Services.BankCheckingService;
import com.JobApplication.IBANValidation.Services.IBANValidationService;
import org.junit.Assert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankCheckingServiceTests {

    @InjectMocks
    BankCheckingService bankCheckingService;

    @ParameterizedTest
    @CsvFileSource(resources = "/BankCheckingServiceTestData.csv")
    public void when_validating_iban_should_return_appropriate_validation_data(String expectedStatus, String testIBANNumber) {
        var countryCodeToCheck = "LT";
        var bankCodeToCheck = "70440";
        var testBankStatus = new BankStatus(expectedStatus, countryCodeToCheck, bankCodeToCheck);

        BankStatus bankStatus = bankCheckingService.checkBank(testIBANNumber, countryCodeToCheck, bankCodeToCheck, 9);

        Assert.assertEquals(bankStatus, testBankStatus);
    }
}
