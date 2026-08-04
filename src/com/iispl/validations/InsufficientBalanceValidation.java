package com.iispl.validations;

import com.iispl.exceptions.AccountNotFoundException;
import com.iispl.exceptions.InsufficientFundsException;
import com.iispl.model.Account;
import com.iispl.model.Cheque;
import com.iispl.service.AccountService;

public class InsufficientBalanceValidation implements ChequeValidator {

	private AccountService accountService;

    public InsufficientBalanceValidation(AccountService accountService) {
        this.accountService = accountService;
    }
	
	@Override
	public boolean validate(Cheque cheque) throws AccountNotFoundException, InsufficientFundsException{
		Account account=accountService.searchAccount(cheque.getAccountNumber());
		if(account.getAccountBalance().compareTo(cheque.getChequeAmount())<0)
			throw new InsufficientFundsException();
		return true;
	}
	

}
