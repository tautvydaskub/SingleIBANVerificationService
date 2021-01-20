package com.JobApplication.ibanvalidation.models;

import java.util.Objects;

public class ValidationStatus {
    private String status;
    private String message;

    public ValidationStatus(String status) {
        this.status = status;
        setMessageByStatus();
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    private void setMessageByStatus()
    {
        switch(status) {
            case "valid":
                message = "IBAN code is valid.";
                break;
            case "wrongCountry":
                message = "No such country code found.";
                break;
            case "wrongLength":
                message = "IBAN code is too short.";
                break;
            case "invalidCharacters":
                message = "IBAN code contains invalid characters.";
                break;
            case "invalid":
                message = "IBAN code is not valid.";
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
        ValidationStatus that = (ValidationStatus) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }
}
