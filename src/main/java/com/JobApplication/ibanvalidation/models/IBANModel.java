package com.JobApplication.ibanvalidation.models;

public class IBANModel {

    private String fullIBANNumber;
    private ValidationStatus validationStatus;
    private BankStatus recognizableBank;

    public IBANModel(String fullIBANNumber) {
        this.fullIBANNumber = fullIBANNumber;
    }
    public IBANModel() {
    }

    public String getFullIBANNumber() {
        return fullIBANNumber;
    }

    public void setFullIBANNumber(String fullIBANNumber) {
        this.fullIBANNumber = fullIBANNumber;
    }

    public ValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(ValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

    public BankStatus getRecognizableBank() {
        return recognizableBank;
    }

    public void setRecognizableBank(BankStatus recognizableBank) {
        this.recognizableBank = recognizableBank;
    }
}