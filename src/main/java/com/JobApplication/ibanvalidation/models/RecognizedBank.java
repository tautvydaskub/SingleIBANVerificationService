package com.JobApplication.ibanvalidation.models;

public class RecognizedBank {

    private String countryCode;
    private String bankCode;
    private String bankName;

    public RecognizedBank(String countryCode, String bankCode, String bankName) {
        this.countryCode = countryCode;
        this.bankCode = bankCode;
        this.bankName = bankName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBankName() {
        return bankName;
    }
}
