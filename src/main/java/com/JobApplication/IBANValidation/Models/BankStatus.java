package com.JobApplication.IBANValidation.Models;

import java.util.Objects;

public class BankStatus {
    private String status;
    private String message;
    private String checkedCountryCode;
    private String checkedBankCode;

    public BankStatus(String status, String checkedCountryCode, String checkedBankCode) {
        this.status = status;
        setMessageByStatus();
        this.checkedCountryCode = checkedCountryCode;
        this.checkedBankCode = checkedBankCode;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCheckedCountryCode() {
        return checkedCountryCode;
    }

    public String getCheckedBankCode() {
        return checkedBankCode;
    }

    private void setMessageByStatus()
    {
        switch(status) {
            case "sameBank":
                message = "IBAN belongs to the provided bank.";
                break;
            case "wrongCountry":
                message = "IBAN belongs to a different country than the provided one";
                break;
            case "wrongBank":
                message = "IBAN code is for a different bank";
                break;
            default:
                message = "";
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankStatus that = (BankStatus) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(message, that.message) &&
                Objects.equals(checkedCountryCode, that.checkedCountryCode) &&
                Objects.equals(checkedBankCode, that.checkedBankCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, checkedCountryCode, checkedBankCode);
    }
}
