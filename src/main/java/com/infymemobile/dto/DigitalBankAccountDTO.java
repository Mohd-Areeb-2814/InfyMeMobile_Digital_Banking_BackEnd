package com.infymemobile.dto;

public class DigitalBankAccountDTO {

    private String digitalBankingId;
    private UserDTO userDTO;
    private BankAccountDTO bankAccountDTO;
    private String accountType;

    public UserDTO getUserDTO() {
        return userDTO;
    }
    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    public BankAccountDTO getBankAccountDTO() {
        return bankAccountDTO;
    }
    public void setBankAccountDTO(BankAccountDTO bankAccountDTO) {
        this.bankAccountDTO = bankAccountDTO;
    }

    public String getDigitalBankingId() {
        return digitalBankingId;
    }
    public void setDigitalBankingId(String digitalBankingId) {
        this.digitalBankingId = digitalBankingId;
    }


    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


}
