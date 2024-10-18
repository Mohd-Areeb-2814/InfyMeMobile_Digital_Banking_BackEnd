package com.infymemobile.dto;


import java.time.LocalDate;


import com.infymemobile.entity.BankAccountEntity;
import com.infymemobile.entity.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class BankAccountDTO {

    @NotNull()
    @Min(value = 1000000)
    private Long accountNumber;

    @NotNull()
    @Size(min = 3, message = "the minimum length should be 3")
    @Size(max = 15, message = "the maximum length should be 15,")
    private String bankName;

    @Min(value = 1000, message = "The minimum value must be 1000. Can't be a negative value.")
    private Double balance;


    @NotNull()
    @Size(min = 1, message = "the minimum length should be 1")
    @Size(max = 10, message = "the maximum length should be 15")
    private String accountType;


    @NotNull()
    @Size(min = 1, message = "the minimum length should be 1")
    @Size(max = 15, message = "the maximum length should be 15")
    private String ifscCode;

    @Past(message = "Opening Date Must be a past Date")
    private LocalDate openingDate;

    @NotNull(message = "mobileNumber should not be null")
    @Valid
    private UserDTO userDTO;


    public UserDTO getUserDTO() {
        return userDTO;
    }
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    public Long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getIfscCode() {
        return ifscCode;
    }
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
    public LocalDate getOpeningDate() {
        return openingDate;
    }
    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }


    public BankAccountEntity prepareEntity(BankAccountDTO bankAccountDTO) {
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        bankAccountEntity.setAccountNumber(bankAccountDTO.getAccountNumber());
        bankAccountEntity.setBankName(bankAccountDTO.getBankName());
        bankAccountEntity.setBalance(bankAccountDTO.getBalance());
        bankAccountEntity.setAccountType(bankAccountDTO.getAccountType());
        bankAccountEntity.setIfscCode(bankAccountDTO.getIfscCode());
        bankAccountEntity.setOpeningDate(bankAccountDTO.getOpeningDate());
        User user = new User();
        user.setMobileNumber(bankAccountDTO.getUserDTO().getMobileNumber());
        bankAccountEntity.setUser(user);;
        return bankAccountEntity;

    }

    public BankAccountDTO prepareDTO(BankAccountEntity bankAccountEntity) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setAccountNumber(bankAccountEntity.getAccountNumber());
        bankAccountDTO.setBankName(bankAccountEntity.getBankName());
        bankAccountDTO.setBalance(bankAccountEntity.getBalance());
        bankAccountDTO.setAccountType(bankAccountEntity.getAccountType());
        bankAccountDTO.setIfscCode(bankAccountEntity.getIfscCode());
        bankAccountDTO.setOpeningDate(bankAccountEntity.getOpeningDate());
        UserDTO dto = new UserDTO();
        dto.setMobileNumber(bankAccountEntity.getUser().getMobileNumber());
        bankAccountDTO.setUserDTO(dto);;
        return bankAccountDTO;
    }
    @Override
    public String toString() {
        return "BankAccountDTO [accountNumber=" + accountNumber + ", bankName=" + bankName + ", balance=" + balance
                + ", accountType=" + accountType + ", ifscCode=" + ifscCode + ", openingDate=" + openingDate
                + ", userDTO=" + userDTO + "]";
    }


}
