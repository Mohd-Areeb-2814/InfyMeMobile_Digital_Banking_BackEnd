package com.infymemobile.service;

import java.time.LocalDateTime;


import java.util.ArrayList;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infymemobile.dto.TransactionDTO;
import com.infymemobile.entity.BankAccountEntity;
import com.infymemobile.entity.TransactionEntity;
import com.infymemobile.exception.InfyMeMobileException;
import com.infymemobile.repository.AccountRepository;
import com.infymemobile.repository.TransactionRepository;
import com.infymemobile.utility.ExceptionConstants;
import com.infymemobile.validator.TransactionValidator;

import org.springframework.data.domain.PageImpl;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String fundTransfer(TransactionDTO transactionDTO) throws InfyMeMobileException {
        // TODO Auto-generated method stub
        TransactionValidator.validateTransaction(transactionDTO);
        Optional<BankAccountEntity> optional = accountRepository.findById(transactionDTO.
                getSenderAccountNumber());

        BankAccountEntity senderbankAccountEntity = optional.orElseThrow(() -> new
                InfyMeMobileException(ExceptionConstants.NO_ACCOUNTS_FOUND.toString()));

        if(transactionDTO.getAmount() <= senderbankAccountEntity.getBalance()) {
            Optional<BankAccountEntity> optionals = accountRepository.findById(transactionDTO.
                    getReceiverAccountNumber());

            BankAccountEntity recieverbankAccountEntity = optionals.orElseThrow(() -> new
                    InfyMeMobileException(ExceptionConstants.NO_ACCOUNTS_FOUND.toString()));

            senderbankAccountEntity.setBalance(senderbankAccountEntity.getBalance() - transactionDTO.getAmount());
            accountRepository.save(senderbankAccountEntity);

            recieverbankAccountEntity.setBalance(recieverbankAccountEntity.getBalance() + transactionDTO.getAmount());
            accountRepository.save(recieverbankAccountEntity);

            transactionDTO.setTransactionDateTime(LocalDateTime.now());;

            TransactionEntity transactionEntity = transactionDTO.prepareEntity(transactionDTO);
            transactionRepository.save(transactionEntity);

            return "The amount is debited from " + transactionDTO.getSenderAccountNumber() +
                    " and The amount is credited to " + transactionDTO.getReceiverAccountNumber();

        }
        else {
            throw new InfyMeMobileException(ExceptionConstants.INSUFFICIENT_FUNDS.toString());
        }
    }

    @Override
    public List<TransactionDTO> accountStatement(Long mobileNo) throws InfyMeMobileException {
        // TODO Auto-generated method stub

        List<TransactionEntity> entityList =  transactionRepository.getByPaidFrom(mobileNo);
        if(entityList.isEmpty())
            throw new InfyMeMobileException(ExceptionConstants.NO_ACTIVE_TRANSACTIONS.toString());

        List<TransactionDTO> dtoList = new ArrayList<TransactionDTO>();

        entityList.forEach(transactionEntity -> {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setTransactionId(transactionEntity.getTransactionId());
            transactionDTO.setModeOfTransaction(transactionEntity.getModeOfTransaction());
            transactionDTO.setPaidTo(transactionEntity.getPaidTo());
            transactionDTO.setReceiverAccountNumber(transactionEntity.getReceiverAccountNumber());
            transactionDTO.setAmount(transactionEntity.getAmount());
            transactionDTO.setTransactionDateTime(transactionEntity.getTransactionDateTime());
            transactionDTO.setRemarks(transactionEntity.getRemarks());
            transactionDTO.setPaidFrom(transactionEntity.getPaidFrom());
            transactionDTO.setSenderAccountNumber(transactionEntity.getSenderAccountNumber());

            dtoList.add(transactionDTO);

        });
        return dtoList;
    }

    @Override
    public Page<TransactionDTO> getTransactions(Long mobileNo ,int page, int size, String sortBy) throws InfyMeMobileException {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        List<TransactionEntity> entityList =  transactionRepository.getByPaidFrom(mobileNo);
        if(entityList.isEmpty())
            throw new InfyMeMobileException(ExceptionConstants.NO_ACTIVE_TRANSACTIONS.toString());

        List<TransactionDTO> dtoList = new ArrayList<TransactionDTO>();

        entityList.forEach(transactionEntity -> {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setTransactionId(transactionEntity.getTransactionId());
            transactionDTO.setModeOfTransaction(transactionEntity.getModeOfTransaction());
            transactionDTO.setPaidTo(transactionEntity.getPaidTo());
            transactionDTO.setReceiverAccountNumber(transactionEntity.getReceiverAccountNumber());
            transactionDTO.setAmount(transactionEntity.getAmount());
            transactionDTO.setTransactionDateTime(transactionEntity.getTransactionDateTime());
            transactionDTO.setRemarks(transactionEntity.getRemarks());
            transactionDTO.setPaidFrom(transactionEntity.getPaidFrom());
            transactionDTO.setSenderAccountNumber(transactionEntity.getSenderAccountNumber());

            dtoList.add(transactionDTO);

        });

        // Convert the list to a Page
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dtoList.size());
        return new PageImpl<>(dtoList.subList(start, end), pageable, dtoList.size());

    }

}
