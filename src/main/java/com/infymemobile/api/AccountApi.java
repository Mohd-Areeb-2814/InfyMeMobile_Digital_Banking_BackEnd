package com.infymemobile.api;


import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infymemobile.dto.AccNoOtpRequest;
import com.infymemobile.dto.BankAccountDTO;
import com.infymemobile.dto.TransactionDTO;
import com.infymemobile.entity.TransactionEntity;
import com.infymemobile.exception.InfyMeMobileException;
import com.infymemobile.service.BankAccountService;
import com.infymemobile.service.DigitalBankAccountService;
import com.infymemobile.service.TransactionService;



@RestController
@RequestMapping(value = "accountService")
@CrossOrigin
@Validated
public class AccountApi {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private DigitalBankAccountService digitalBankAccountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping(value = "/accounts")
    public ResponseEntity<String> createAccount (@RequestBody BankAccountDTO accountDTO) throws InfyMeMobileException{
        //System.out.println(accountDTO.toString());
        String res = bankAccountService.createAccount(accountDTO);
        return new ResponseEntity<String>(res, HttpStatus.CREATED);
    }

    @GetMapping(value = "/accounts/{mobileNo}")
    public ResponseEntity<List<BankAccountDTO>> listAccounts(@PathVariable Long mobileNo) throws InfyMeMobileException{
        List<BankAccountDTO> list = bankAccountService.listAccounts(mobileNo);
        return new ResponseEntity<List<BankAccountDTO>>(list, HttpStatus.OK);
    }

    @PostMapping(value = "/account/{mobileNo}")
    public ResponseEntity<String> linkAccount(@PathVariable Long mobileNo,@RequestBody Long accountNo) throws InfyMeMobileException{
        System.out.println(mobileNo + " " + accountNo);
        String res = digitalBankAccountService.linkAccount(mobileNo, accountNo);
        return new ResponseEntity<String>(res, HttpStatus.CREATED);
    }

    @PostMapping(value = "/accounts/{mobileNo}")
    public ResponseEntity<String> linkAccounts(@PathVariable Long mobileNo, @RequestBody AccNoOtpRequest request) throws InfyMeMobileException{
        String res = digitalBankAccountService.linkAccount(mobileNo, request.getAccountNumber(), request.getOtp());
        return new ResponseEntity<String>(res, HttpStatus.CREATED);
    }

    @GetMapping(value = "/accounts/balance/{mobileNo}/account")
    public ResponseEntity<Double> checkBalance(@PathVariable Long mobileNo, @RequestParam("accountNo") Long accountNo) throws InfyMeMobileException{
        System.out.println("Inside Api");
        Double res = digitalBankAccountService.checkBalance(mobileNo, accountNo);
        return new ResponseEntity<Double>(res, HttpStatus.OK);
    }

    @PatchMapping(value = "/accounts/fundtransfer")
    public ResponseEntity<String> fundTransfer(@RequestBody TransactionDTO transactionDTO) throws InfyMeMobileException{
        System.out.println(transactionDTO.toString());
        String res = transactionService.fundTransfer(transactionDTO);
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }

    @GetMapping(value = "/accounts/statement/{mobileNo}")
    public ResponseEntity<List<TransactionDTO>> accountStatement(@PathVariable Long mobileNo) throws InfyMeMobileException{
        List<TransactionDTO> lisDTO = transactionService.accountStatement(mobileNo);
        return new ResponseEntity<List<TransactionDTO>>(lisDTO, HttpStatus.OK);
    }

    @GetMapping("/accounts/transactions/{mobileNo}")
    public Page<TransactionDTO> getTransactions(
            @PathVariable Long mobileNo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "transactionDateTime") String sortBy) throws InfyMeMobileException {

        Iterable<TransactionDTO> transactions = transactionService.getTransactions(mobileNo, page, size, sortBy);
        for (TransactionDTO transc : transactions) {
            System.out.println(transc);
        }
//		return transactionService.getTransactions(mobileNo, page, size, sortBy);
        return (Page<TransactionDTO>) transactions;
    }
}
