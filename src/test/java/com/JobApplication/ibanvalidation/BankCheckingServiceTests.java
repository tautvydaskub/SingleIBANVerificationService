package com.JobApplication.ibanvalidation;

import com.JobApplication.ibanvalidation.models.BankStatus;
import com.JobApplication.ibanvalidation.models.RecognizedBank;
import com.JobApplication.ibanvalidation.services.BankCheckingService;
import com.JobApplication.ibanvalidation.services.ResourceReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankCheckingServiceTests {

    @InjectMocks
    BankCheckingService bankCheckingService;

    @Mock
    ResourceReader reader;

    @ParameterizedTest
    @CsvFileSource(resources = "/BankCheckingServiceTestData.csv")
    public void when_validating_iban_should_return_appropriate_validation_data(String expectedStatus, String expectedRecognizedBank, String testIBANNumber) {

        List<RecognizedBank> recognizedBanks = new LinkedList<RecognizedBank>();
        recognizedBanks.add(new RecognizedBank("LT", "70440", "SEB"));

        var testBankStatus = new BankStatus(expectedStatus, expectedRecognizedBank);

        var codeLengths = new HashMap<String, Integer>();
        codeLengths.put("LT", 20);

        when(reader.recognizedBankReader()).thenReturn(recognizedBanks);

        BankStatus bankStatus = bankCheckingService.checkBank(testIBANNumber);

        Assertions.assertEquals(bankStatus, testBankStatus);
    }
}
