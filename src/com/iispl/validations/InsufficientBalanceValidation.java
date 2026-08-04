package com.iispl.validations;

import com.iispl.model.Account;
import com.iispl.model.Cheque;
import com.iispl.service.AccountService;
import com.iispl.service.AccountServiceImpl;

public class InsufficientBalanceValidation implements ChequeValidator {

	@Override
	public boolean validate(Cheque cheque) {
		AccountService accountService= new AccountServiceImpl();
		Account account=accountService.searchAccount(cheque.getAccountNumber());
		if(account.getAccountBalance().compareTo(cheque.getChequeAmount())>0)
			return true;
		return false;
	}
	

}
