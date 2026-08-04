package com.iispl.main;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
	static Scanner scanner=new Scanner(System.in);

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
	
	private void printMenu() {
		System.out.println();
	}
	
	private String getchequNumber() {
		System.out.println("Enter Cheque Number");
		return scanner.nextLine();
	}
	private String getAccountNumber() {
		System.out.println("Enter Account Number");
		return scanner.nextLine();
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
	

	private void removeProcessedCheque(String ChequeNumber) {
		boolean isdeleted=chequeService.removeProcessedCheque(ChequeNumber);
		if(isdeleted) {
			System.out.println("Removed cheque from processed cheque List");
		}else {
			System.err.println("Cheque Not Found");
		}
	}
	
	private void searchAccount(String accountNnumber) {
		try {
			Account account=accountService.searchAccount(accountNnumber);
			System.out.println("AccountNumber\t\tAccountBlance\t\tAccountStatus");
			System.out.println(account.getAccountNumber()+"\t\t"+account.getAccountBalance()+"\t\t"+account.getAccountStatus());
		} catch (AccountNotFoundException e) {
			 
			e.printStackTrace();
		}
		
	}
	
	private void updateBalance(String accountNnumber, BigDecimal amount) {
		
		if(accountService.updateBalance(accountNnumber, amount)) {
			System.out.println("balance updated successfully");
		}
		else {
			System.out.println("balance not updated");
		}
		
	}
	
	private void displayAllAccounts() {
		
	}
	
	private void deleteAccount(String accountNumber) {
		
	}
	

}
