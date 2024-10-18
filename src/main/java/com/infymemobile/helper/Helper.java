package com.infymemobile.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.infymemobile.entity.BankAccountEntity;
import com.infymemobile.entity.DigitalBankAccountEntity;
import com.infymemobile.entity.User;
import com.infymemobile.repository.AccountRepository;
import com.infymemobile.repository.DigitalBankAccountRepository;
import com.infymemobile.repository.UserRepository;
@Component
public class Helper {

    @Autowired
    private  AccountRepository accountRepository;

    @Autowired
    private  DigitalBankAccountRepository digitalBankAccountRepository;

    @Autowired
    private UserRepository userRepository;

    public  Long generateAccountNumber() {

        BankAccountEntity accountEntity = accountRepository.findFirstByOrderByAccountNumberDesc();
        if(accountEntity == null) {
            return 55810643789L;
        }
        return accountEntity.getAccountNumber() + 1;
    }

    public  String generateDigitalBankAccountId() {
        DigitalBankAccountEntity accountEntity = digitalBankAccountRepository.findFirstByOrderByDigitalBankingIdDesc();

        if(accountEntity == null) {
            return "w_1001";
        }
        String[] arr = accountEntity.getDigitalBankingId().split("_");
        String digitalId = arr[0] + "_" + (Integer.valueOf(arr[1]) + 1);
        return digitalId;
    }


    public String generateUserID() {
        User user = userRepository.findFirstByOrderByUserIdDesc();

        if(user == null) {
            return "U1001";
        }

        String[] arr = user.getUserId().split("U");
        String digitalId = "U" + (Integer.valueOf(arr[1]) + 1);
        return digitalId;
    }
}
