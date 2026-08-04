package com.iispl.validations;

import com.iispl.exceptions.AccountNotFoundException;
import com.iispl.exceptions.InsufficientFundsException;
import com.iispl.model.Cheque;

public interface ChequeValidator {

	public boolean validate(Cheque cheque) throws AccountNotFoundException, InsufficientFundsException;
	
}
