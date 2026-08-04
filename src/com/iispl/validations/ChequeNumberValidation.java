package com.iispl.validations;

import com.iispl.model.Cheque;

public class ChequeNumberValidation implements ChequeValidator {

	@Override
	public boolean validate(Cheque cheque) {
		
		if(cheque.getChequeNumber().startsWith("CHQ"))
			return true;
		return false;
	}

}
