package com.JobApplication.ibanvalidation.services;

import com.JobApplication.ibanvalidation.models.BankStatus;
import com.JobApplication.ibanvalidation.models.RecognizedBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankCheckingService {

    @Autowired
    private ResourceReader reader;

    public BankStatus checkBank(String iban) {

        List<RecognizedBank> recognizedBanks = reader.recognizedBankReader();
        for (RecognizedBank bank : recognizedBanks) {

            if (iban.substring(0, 2).equals(bank.getCountryCode())) {

                int bankCodeEndPos = bank.getBankCode().length() + 4;
                String bankCode = iban.substring(4, bankCodeEndPos);
                if (bankCode.equals(bank.getBankCode())) {

                    return new BankStatus("bankRecognized", bank.getBankName());
                }
            }
        }
        return new BankStatus("bankNotRecognized", null);
    }
}
