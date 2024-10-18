package com.infymemobile.dto;

import java.time.LocalDate;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {


    String userId;

    @NotNull(message = "accountHolderName should not be null")
    @Size(min = 3, message = "accountHolderName should contain atleast 3 character")
    @Size(max = 50, message = "accountHolderName should contain atmost 50 character")
    String accountHolderName;

    @NotNull(message = "mobileNumber should not be null")
    @Min(value = 1000000000, message = "mobileNumber should be 10 digit number")
    @Max(value = 9999999999l, message = "mobileNumber should be 10 digit number")
    Long mobileNumber;

    @Pattern(regexp = "Male|Female", message = "gender should be male or female")
    String gender;

    @Past(message = "dateOfBirth should be past date")
    LocalDate dateOfBirth;

    @NotNull(message = "password should not be null")
    @Size(min = 5, message = "minimum length of password should be 5")
    @Size(max = 15, message = "maximum length of password should be 15")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{5,15}$", message = "password must be alpha-numeric")
    String password;

    //@Email(regexp = "^\\w*[A-Za-z]+(?:([._]?\\w+)*)\\@[A-Za-z]\\w*[-]?\\w+\\.[A-Za-z]{1,}?(\\.?[A-Za-z]+)$", message = "email not valid")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "email not valid")
    String email;

    @NotNull(message = "Communication Address should not be null")
    @Size(min = 3, message = "minimum length of Communication Address should be 3")
    @Size(max = 50, message = "maximum length of Communication Address should be 50")
    String communicationAddress;

    @NotNull(message = "PAN should not be null")
    @Size(min = 10, max = 10, message = "PAN should be 10 digit number")
    //@Size(max = 10, message = "PAN should be 10 digit number")
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "PAN number is invalid")
    String pan;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    public Long getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCommunicationAddress() {
        return communicationAddress;
    }
    public void setCommunicationAddress(String communicationAddress) {
        this.communicationAddress = communicationAddress;
    }
    public String getpan() {
        return pan;
    }
    public void setpan(String pAN) {
        pan = pAN;
    }
    @Override
    public String toString() {
        return "UserDTO [userId=" + userId + ", accountHolderName=" + accountHolderName + ", mobileNumber="
                + mobileNumber + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", password=" + password
                + ", email=" + email + ", communicationAddress=" + communicationAddress + ", PAN=" + pan + "]";
    }

}