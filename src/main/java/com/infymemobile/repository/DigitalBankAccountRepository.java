package com.infymemobile.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.infymemobile.entity.DigitalBankAccountEntity;


@Repository
public interface DigitalBankAccountRepository extends CrudRepository<DigitalBankAccountEntity, String> {

    @Query
    DigitalBankAccountEntity getDigitalBankAccountEntityByUserMobileNumberAndBankAccountEntityAccountNumber(Long mobileNumber, Long accountNumber);

    DigitalBankAccountEntity findFirstByOrderByDigitalBankingIdDesc();
}
