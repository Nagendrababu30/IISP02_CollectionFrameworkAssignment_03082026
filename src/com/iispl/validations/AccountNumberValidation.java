package com.iispl.validations;

import com.iispl.model.Account;

public class AccountNumberValidation implements AccountValidator{

	@Override
	public boolean validate(Account account) {
		if(account.getAccountNumber().startsWith("ACC")) {
			return true;
		}
		return false;
	}

}
