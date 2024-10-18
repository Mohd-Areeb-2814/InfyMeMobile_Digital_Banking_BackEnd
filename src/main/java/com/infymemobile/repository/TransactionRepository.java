package com.infymemobile.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infymemobile.entity.TransactionEntity;



@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer>{

    List<TransactionEntity> getByPaidFrom(Long mobileNo);

}
