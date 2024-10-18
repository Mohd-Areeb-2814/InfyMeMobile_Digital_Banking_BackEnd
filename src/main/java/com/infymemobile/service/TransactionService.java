package com.infymemobile.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.infymemobile.dto.TransactionDTO;
import com.infymemobile.entity.TransactionEntity;
import com.infymemobile.exception.InfyMeMobileException;



public interface TransactionService {

    List<TransactionDTO> accountStatement(Long mobileNo) throws InfyMeMobileException;

    String fundTransfer(TransactionDTO transactionDTO) throws InfyMeMobileException;

    public Page<TransactionDTO> getTransactions(Long mobileNo ,int page, int size, String sortBy) throws InfyMeMobileException;
}
