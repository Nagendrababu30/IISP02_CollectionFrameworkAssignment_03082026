package com.iispl.validations;

import java.math.BigDecimal;

import com.iispl.model.Cheque;

public class ChequeInvalidAmountValidation implements ChequeValidator {

	@Override
	public boolean validate(Cheque cheque) {
		
		if(cheque.getChequeAmount().compareTo(BigDecimal.ZERO)>0) {
			return true;
		}
		return false;
	}

}
//AccountService accountService= new AccountServiceImpl();
//Account account=accountService.searchAccount(cheque.getAccountNumber());