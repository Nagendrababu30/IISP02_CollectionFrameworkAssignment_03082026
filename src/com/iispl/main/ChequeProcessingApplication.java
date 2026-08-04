package com.iispl.main;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.iispl.exceptions.AccountNotFoundException;
import com.iispl.exceptions.DuplicateChequeException;
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
		try {
			chequeService.validateCheques(chequeList);
		}catch(DuplicateChequeException exception) {
			exception.getMessage();
		}
		
	}
	
	private void displayProcessedCheques() {
		Set<Cheque> processedcheques=chequeService.displayProcessedCheques();
		for(Cheque cheque:processedcheques) {
			System.out.println(cheque);
		}
	}
	
	private void getBranchReport() {
		Map<String,Integer> branchreports=chequeService.getBranchReport();
		
		System.out.println("Branch name\t\t Processed_Cheques");
		
		for(Map.Entry<String,Integer> branch:branchreports.entrySet()) {
			System.out.println(branch.getKey()+"\t\t"+branch.getValue());
		}
	}
	
}
