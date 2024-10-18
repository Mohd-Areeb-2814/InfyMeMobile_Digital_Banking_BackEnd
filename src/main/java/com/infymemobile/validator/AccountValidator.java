package com.infymemobile.validator;

import com.infymemobile.dto.BankAccountDTO;
import com.infymemobile.exception.InfyMeMobileException;

public class AccountValidator {

    private AccountValidator() {}

    public static void validateBankAccount(BankAccountDTO account) throws InfyMeMobileException {

        isValidAccountNumber(account.getAccountNumber());
        isValidBankName(account.getBankName());
        isValidBalance(account.getBalance());
        isValidAccounttype(account.getAccountType());

    }


    public static boolean isValidAccountNumber(Long accountNumber) throws InfyMeMobileException {

        String accnumber = String.valueOf(accountNumber);

        if(accnumber.matches("^\\d{11,16}$")) {
            return true;
        }
        else {
            throw new InfyMeMobileException("Account Number is Invalid");
        }

    }

    public static boolean isValidBankName(String bankName) throws InfyMeMobileException {
        if(bankName.matches("^[a-zA-Z]+$")) {
            return true;
        }
        else {
            throw new InfyMeMobileException("BankName is Invalid");
        }
    }

    public static boolean isValidBalance(Double balance) throws InfyMeMobileException {

        if(balance >= 1000) {
            return true;
        }
        else {
            throw new InfyMeMobileException("Minimum balance should be 1000");
        }
    }

    public static boolean isValidAccounttype(String accountType) throws InfyMeMobileException {

        if(accountType.matches("Savings|Salary|Current")) {
            return true;
        }
        else {
            throw new InfyMeMobileException("Account type should be either Savings/Salary/Current");
        }

    }
}
