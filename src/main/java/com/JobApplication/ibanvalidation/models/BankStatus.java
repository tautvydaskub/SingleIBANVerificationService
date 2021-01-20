package com.JobApplication.ibanvalidation.models;

import java.util.Objects;

public class BankStatus {
    private String status;
    private String message;
    private String recognizedBank;

    public BankStatus(String status, String recognizedBank) {
        this.status = status;
        this.recognizedBank = recognizedBank;
        setMessageByStatus();
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getRecognizedBank() {
        return recognizedBank;
    }

    private void setMessageByStatus()
    {
        switch(status) {
            case "bankRecognized":
                message = "IBAN belongs to a recognized bank. Recognized bank name: " + recognizedBank;
                break;
            case "bankNotRecognized":
                message = "IBAN belongs to an unrecognized bank.";
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
                Objects.equals(recognizedBank, that.recognizedBank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, recognizedBank);
    }
}
