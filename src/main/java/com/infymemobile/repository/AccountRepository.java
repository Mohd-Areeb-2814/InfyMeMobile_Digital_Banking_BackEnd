package com.infymemobile.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infymemobile.entity.BankAccountEntity;


@Repository
public interface AccountRepository extends JpaRepository<BankAccountEntity, Long> {

    @Query
    List<BankAccountEntity> getBankAccountEntityByUserMobileNumber(Long mobileNumber);

    @Query
    BankAccountEntity getBankAccountEntityByUserMobileNumberAndAccountNumber(Long mobileNumber, Long accountNumber);

    //@Query("select b  from BankAccountEntity b order by accountNumber desc limit 1")

    BankAccountEntity  findFirstByOrderByAccountNumberDesc();
}
