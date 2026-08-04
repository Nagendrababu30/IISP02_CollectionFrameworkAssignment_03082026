package com.iispl.validations;

import com.iispl.enums.AccountStatus;
import com.iispl.model.Account;

public class AccountStatusValidation implements AccountValidator{

	@Override
	public boolean validate(Account account) {

		if(account.getAccountStatus().equals(AccountStatus.ACTIVE))
			return true;
		return false;
	}

}
