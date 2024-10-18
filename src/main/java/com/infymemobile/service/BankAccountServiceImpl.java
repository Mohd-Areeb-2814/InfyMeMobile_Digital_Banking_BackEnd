package com.infymemobile.service;

import java.util.ArrayList;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infymemobile.dto.BankAccountDTO;
import com.infymemobile.entity.BankAccountEntity;
import com.infymemobile.exception.InfyMeMobileException;
import com.infymemobile.helper.Helper;
import com.infymemobile.repository.AccountRepository;
import com.infymemobile.utility.ExceptionConstants;
import com.infymemobile.validator.AccountValidator;



@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private Helper helper;

    @Override
    @Transactional(rollbackFor = { InfyMeMobileException.class }) //To avoid the scenario where an "Application exception overridden by commit exception" occurs in a Spring application,
    public String createAccount(BankAccountDTO accountDTO) throws InfyMeMobileException {

        accountDTO.setAccountNumber(helper.generateAccountNumber());
        AccountValidator.validateBankAccount(accountDTO);
        BankAccountEntity entity = accountDTO.prepareEntity(accountDTO);
        try {
            BankAccountEntity newEntity = accountRepository.save(entity);
            return String.valueOf(newEntity.getAccountNumber());
        } catch (DataAccessException e) {

            throw new InfyMeMobileException(ExceptionConstants.USER_NOT_FOUND.toString());

        }

    }

    @Override
    public List<BankAccountDTO> listAccounts(Long mobileNo) throws InfyMeMobileException {

        List<BankAccountEntity> list = accountRepository.getBankAccountEntityByUserMobileNumber(mobileNo);
        List<BankAccountDTO> listBankAccountDTO = new ArrayList<BankAccountDTO>();

        if(list.isEmpty()) {
            throw new InfyMeMobileException(ExceptionConstants.NO_ACCOUNTS_FOUND.toString());
        }
        list.forEach(entity -> {
            BankAccountDTO bankAccountDTO = new BankAccountDTO();
            listBankAccountDTO.add(bankAccountDTO.prepareDTO(entity));
        });
        return listBankAccountDTO;
    }

}
