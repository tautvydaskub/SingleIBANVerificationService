package com.JobApplication.IBANValidation.Services;

import com.JobApplication.IBANValidation.Models.BankStatus;
import org.springframework.stereotype.Service;

@Service
public class BankCheckingService {

    public BankStatus checkBank(String iban, String countryCodeToCheck, String bankNumberToCheck, int bankNumberEndPos) {

        if (iban.substring(0, 2).equals(countryCodeToCheck)) {
            String bankCode = iban.substring(4, bankNumberEndPos);
            if (bankCode.equals(bankNumberToCheck)) {
                return new BankStatus("sameBank", countryCodeToCheck, bankNumberToCheck);
            }
            else {
                return new BankStatus("wrongBank", countryCodeToCheck, bankNumberToCheck);
            }
        }
        return new BankStatus("wrongCountry", countryCodeToCheck, bankNumberToCheck);
    }
}
