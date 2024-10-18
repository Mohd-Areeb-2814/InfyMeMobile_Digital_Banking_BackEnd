package com.infymemobile.validator;

import com.infymemobile.dto.TransactionDTO;
import com.infymemobile.exception.InfyMeMobileException;


public class TransactionValidator {

    private TransactionValidator() {}

    public static void validateTransaction(TransactionDTO transaction) throws InfyMeMobileException {

        isValidModeOfTransaction(transaction.getModeOfTransaction());
        //isValidTransactionDate(transaction.getTransactionDateTime());

    }


    public static boolean isValidModeOfTransaction(String modeOfTransaction) throws InfyMeMobileException {

        if(modeOfTransaction.matches("Fund Transfer|Electricity Bill Pay|DTH Recharge|QR Scan payment")) {
            return true;
        }
        else {
            throw new InfyMeMobileException("Mode Of Transaction should be either Fund Transfer/Electricity Bill Pay/DTH Recharge/QR Scan payment");
        }

    }
	/*
	public static boolean isValidTransactionDate(LocalDateTime transactionDate) throws InfyMeMobileException {

		if(transactionDate.isAfter(LocalDateTime.now())) {
			throw new InfyMeMobileException("Transaction Date should be a past or present date");
		}
		else {
			return true;
		}

	}
	*/
}
