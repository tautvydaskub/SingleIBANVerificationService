package com.JobApplication.IBANValidation.Models;

public class IBANModel {

    private String fullIBANNumber;
    private ValidationStatus isValidIBAN;
    private BankStatus belongsToSEB;

    public IBANModel(String fullIBANNumber) {
        this.fullIBANNumber = fullIBANNumber;
    }
    public IBANModel() {
    }

    public String getFullIBANNumber() {
        return fullIBANNumber;
    }

    public ValidationStatus getIsValidIBAN() {
        return isValidIBAN;
    }

    public void setIsValidIBAN(ValidationStatus isValidIBAN) {
        this.isValidIBAN = isValidIBAN;
    }

    public BankStatus getBelongsToSEB() {
        return belongsToSEB;
    }

    public void setBelongsToSEB(BankStatus belongsToSEB) {
        this.belongsToSEB = belongsToSEB;
    }
}