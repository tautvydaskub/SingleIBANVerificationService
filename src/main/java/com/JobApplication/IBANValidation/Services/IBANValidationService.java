package com.JobApplication.IBANValidation.Services;

import com.JobApplication.IBANValidation.Models.BankStatus;
import com.JobApplication.IBANValidation.Models.IBANModel;
import com.JobApplication.IBANValidation.Models.ValidationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class IBANValidationService {

    private String fullIBAN;
    private String countryCode;

    @Autowired
    private ResourceReader reader;

    @Autowired
    private BankCheckingService bankChecker;

    public IBANModel check(IBANModel iban) {
        splitIBAN(iban);
        String isIBANValid = getIBANValidity();
        iban.setIsValidIBAN(new ValidationStatus(isIBANValid));
        if (iban.getIsValidIBAN().getStatus() == "valid") {
            BankStatus belongsToSEB = bankChecker.checkBank(fullIBAN, "LT", "70440", 9);
            iban.setBelongsToSEB(belongsToSEB);
        }
        return iban;
    }

    private String getIBANValidity() {

        Map<String, Integer> codeLengths = reader.ibanCodeLengthByCountryCodeReader();
        String status = "";

        //Check if all characters in the code are valid
        if (checkForInvalidCharacters()) {

            //Check if such country code exists for valid IBANs
            if (codeLengths.containsKey(countryCode)) {

                //Check if the IBAN length matches the expected length
                if (codeLengths.get(countryCode) == fullIBAN.length()) {

                    //Rearrange IBAN by moving the first 4 characters to the end
                    String rearrangedIBAN = fullIBAN.substring(4) + fullIBAN.substring(0, 4);

                    //Convert IBAN characters (A-Z) to integers (A=10, B=11, ..., Z=35)
                    String convertedIBAN = "";
                    for (int i = 0; i < rearrangedIBAN.length(); i++) {
                        if (rearrangedIBAN.charAt(i) >= 'A' && rearrangedIBAN.charAt(i) <= 'Z') {
                            convertedIBAN += ((int) rearrangedIBAN.charAt(i)) - 55;
                        } else {
                            convertedIBAN += rearrangedIBAN.charAt(i);
                        }
                    }

                    //Calculate IBAN mod 97. Result = 1 means that the IBAN is valid
                    BigInteger convertedIBANAsBigInt = new BigInteger(convertedIBAN);
                    if (convertedIBANAsBigInt.mod(new BigInteger("97")).compareTo(BigInteger.ONE) == 0) {
                        status = "valid";
                    } else {
                        status = "invalid";
                    }
                } else {
                    status = "wrongLength";
                }
            } else {
                status = "wrongCountry";
            }
        } else {
            status = "invalidCharacters";
        }
        return status;
    }

    private void splitIBAN(IBANModel iban) {
        fullIBAN = iban.getFullIBANNumber().toUpperCase();
        countryCode = fullIBAN.substring(0, 2);
    }

    private boolean checkForInvalidCharacters() {
        Pattern pattern = Pattern.compile("[^A-Z0-9]");
        Matcher matcher = pattern.matcher(fullIBAN);
        return !matcher.find();
    }
}
