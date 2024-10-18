package com.infymemobile.service;

import com.infymemobile.exception.InfyMeMobileException;

public interface DigitalBankAccountService {

    String linkAccount(Long mobileNo, Long accountNo) throws InfyMeMobileException;

    String linkAccount(Long mobileNo, Long accountNo, Integer OTP) throws InfyMeMobileException;

    Double checkBalance(Long mobileNo, Long accountNo) throws InfyMeMobileException;
}
