package com.infymemobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infymemobile.entity.BankAccountEntity;
import com.infymemobile.entity.DigitalBankAccountEntity;
import com.infymemobile.exception.InfyMeMobileException;
import com.infymemobile.helper.Helper;
import com.infymemobile.repository.AccountRepository;
import com.infymemobile.repository.DigitalBankAccountRepository;
import com.infymemobile.utility.ExceptionConstants;
import com.infymemobile.utility.OTPUtility;


@Service
@Transactional
public class DigitalBankAccountServiceImpl implements DigitalBankAccountService {

    @Autowired
    private DigitalBankAccountRepository digitalBankAccountRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OTPUtility otpUtility;

    @Autowired
    private Helper helper;

    @Override
    public String linkAccount(Long mobileNo, Long accountNo) throws InfyMeMobileException {
        // TODO Auto-generated method stub
        BankAccountEntity entity = accountRepository.getBankAccountEntityByUserMobileNumberAndAccountNumber(mobileNo, accountNo);
        if(entity == null)
            throw new InfyMeMobileException(ExceptionConstants.NO_ACCOUNTS_FOUND.toString());

        DigitalBankAccountEntity digitalBankAccountEntityExisted = digitalBankAccountRepository.
                getDigitalBankAccountEntityByUserMobileNumberAndBankAccountEntityAccountNumber(mobileNo, accountNo);

        if(digitalBankAccountEntityExisted != null)
            throw new InfyMeMobileException(ExceptionConstants.ACCOUNT_ALREADY_LINKED.toString());

        DigitalBankAccountEntity digitalBankAccountEntity = new DigitalBankAccountEntity();
        digitalBankAccountEntity.setDigitalBankingId(helper.generateDigitalBankAccountId());
        digitalBankAccountEntity.setUser(entity.getUser());;
        digitalBankAccountEntity.setBankAccountEntity(entity);
        digitalBankAccountEntity.setAccountType(entity.getAccountType());

        DigitalBankAccountEntity newdigitalBankAccountEntity = digitalBankAccountRepository.save(
                digitalBankAccountEntity);
        String message = "Mobile Number " + newdigitalBankAccountEntity.getUser().getMobileNumber() +
                " has been registered with digital bank id : " +
                newdigitalBankAccountEntity.getDigitalBankingId();
        return message;
    }

    @Override
    public String linkAccount(Long mobileNo, Long accountNo, Integer OTP) throws InfyMeMobileException {
        // TODO Auto-generated method stub
        BankAccountEntity entity = accountRepository.getBankAccountEntityByUserMobileNumberAndAccountNumber(mobileNo, accountNo);
        if(entity == null)
            throw new InfyMeMobileException(ExceptionConstants.NO_ACCOUNTS_FOUND.toString());
        //System.out.println(otpUtility.sendOTP() + "   "  + OTP);
        if(otpUtility.sendOTP().equals(OTP)) {

            DigitalBankAccountEntity digitalBankAccountEntityExisted = digitalBankAccountRepository.
                    getDigitalBankAccountEntityByUserMobileNumberAndBankAccountEntityAccountNumber(mobileNo, accountNo);

            if(digitalBankAccountEntityExisted != null)
                throw new InfyMeMobileException(ExceptionConstants.ACCOUNT_ALREADY_LINKED.toString());

            DigitalBankAccountEntity digitalBankAccountEntity = new DigitalBankAccountEntity();
            digitalBankAccountEntity.setDigitalBankingId(helper.generateDigitalBankAccountId());
            digitalBankAccountEntity.setUser(entity.getUser());;
            digitalBankAccountEntity.setBankAccountEntity(entity);;
            digitalBankAccountEntity.setAccountType(entity.getAccountType());

            DigitalBankAccountEntity newdigitalBankAccountEntity = digitalBankAccountRepository.save(
                    digitalBankAccountEntity);
            String message = "Mobile Number " + newdigitalBankAccountEntity.getUser().getMobileNumber() +
                    " has been registered with digital bank id : " +
                    newdigitalBankAccountEntity.getDigitalBankingId() + " and otp " + OTP;
            return message;

        }
        else {
            throw new InfyMeMobileException(ExceptionConstants.OTP_DOESNOT_MATCH.toString());
        }
    }

    @Override
    public Double checkBalance(Long mobileNo, Long accountNo) throws InfyMeMobileException {
        // TODO Auto-generated method stub

        DigitalBankAccountEntity digitalBankAccountEntity = digitalBankAccountRepository.
                getDigitalBankAccountEntityByUserMobileNumberAndBankAccountEntityAccountNumber(mobileNo, accountNo);
        if(digitalBankAccountEntity == null)
            throw new InfyMeMobileException(ExceptionConstants.NO_ACCOUNT_IS_LINKED.toString());

        BankAccountEntity bankAccountEntity = accountRepository.getBankAccountEntityByUserMobileNumberAndAccountNumber(mobileNo, accountNo);
        return bankAccountEntity.getBalance();
    }

}
