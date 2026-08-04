package com.iispl.main;

import java.util.List;

import com.iispl.model.Account;
import com.iispl.model.Cheque;
import com.iispl.service.AccountService;
import com.iispl.service.AccountServiceImpl;
import com.iispl.service.ChequeService;
import com.iispl.service.ChequeServiceImpl;

public class ChequeProcessingApplication {
	
	static AccountService accountService = new AccountServiceImpl();
	static ChequeService chequeService = new ChequeServiceImpl();

	public static void main(String[] args) {
		
		List<Account> accountList = accountService.getAllAccounts();
		accountService.loadAllAccounts(accountList);
		List<Cheque> chequeList = chequeService.getAllCheques();
		chequeService.validateCheques(chequeList);
		
	}
	
}
