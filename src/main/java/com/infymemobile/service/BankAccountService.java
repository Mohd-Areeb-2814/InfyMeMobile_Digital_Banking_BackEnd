package com.infymemobile.service;

import java.util.List;


import com.infymemobile.dto.BankAccountDTO;
import com.infymemobile.exception.InfyMeMobileException;




public interface BankAccountService {

    String createAccount(BankAccountDTO accountDTO) throws InfyMeMobileException;
    List<BankAccountDTO> listAccounts(Long mobileNo) throws InfyMeMobileException;
}
