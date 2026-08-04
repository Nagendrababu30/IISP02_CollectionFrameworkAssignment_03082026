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
		
		
		do {
			printMenu();
			
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			
				case 1:
					getBranchReport();
					break;
					
				case 2:
					displayProcessedCheques();
					break;
					
				case 3:
					removeProcessedCheque(getChequeNumber());
					break;
					
				case 4:
					displayAllAccounts();
					break;
					
				case 5:
					searchAccount(getAccountNumber());
					break;
					
				case 6:
					deleteAccount(getAccountNumber());
					break;
					
				case 7:
					updateBalance(getAccountNumber(), getAmount());
					break;
					
				case 8:
					return;
				
				default :
					System.out.println("Invalid Choice.");
			
			}
			
		} while(true);
		
	}
	
	private static void printMenu() {
		System.out.println();
	}
	
	private static BigDecimal getAmount() {
		System.out.println("Enter amount : ");
		BigDecimal amount=scanner.nextBigDecimal();
		scanner.nextLine();
		return amount;
	}
	
	private static String getChequeNumber() {
		System.out.println("Enter Cheque Number");
		return scanner.nextLine();
	}
	
	private static String getAccountNumber() {
		System.out.println("Enter Account Number");
		return scanner.nextLine();
	}
	
	private static void displayProcessedCheques() {
		Set<Cheque> processedcheques=chequeService.displayProcessedCheques();
		for(Cheque cheque:processedcheques) {
			System.out.println(cheque);
		}
	}
	
	private static void getBranchReport() {
		Map<String,Integer> branchreports=chequeService.getBranchReport();
		
		System.out.println("Branch name\t\t Processed_Cheques");
		
		for(Map.Entry<String,Integer> branch:branchreports.entrySet()) {
			System.out.println(branch.getKey()+"\t\t"+branch.getValue());
		}
	}
	

	private static void removeProcessedCheque(String ChequeNumber) {
		boolean isdeleted=chequeService.removeProcessedCheque(ChequeNumber);
		if(isdeleted) {
			System.out.println("Removed cheque from processed cheque List");
		}else {
			System.err.println("Cheque Not Found");
		}
	}
	
	private static void searchAccount(String accountNnumber) {
		try {
			Account account=accountService.searchAccount(accountNnumber);
			System.out.println("AccountNumber\t\tAccountBlance\t\tAccountStatus");
			System.out.println(account.getAccountNumber()+"\t\t"+account.getAccountBalance()+"\t\t"+account.getAccountStatus());
		} catch (AccountNotFoundException e) {
			 
			e.printStackTrace();
		}
		
	}
	
	private static void updateBalance(String accountNnumber, BigDecimal amount) {
		
	}
	
	private static void displayAllAccounts() {
		Map<String,Account> accountMap =accountService.displayAllAccount();
		System.out.println("AccountNumber\t\tAccountBlance\t\tAccountStatus");
		accountMap.forEach((accountNumber,account)->{
			System.out.println(account.getAccountNumber() + "\t\t"+ account.getAccountBalance() + "\t\t"+account.getAccountStatus());
		});

	}
	
	private static void deleteAccount(String accountNumber) {
		boolean isDeleted = accountService.deleteAccount(accountNumber);
	    if (isDeleted) {
	        System.out.println("Account deleted successfully.");
	    } else {
	        System.out.println("Account not found.");
	    }
		
		
	}
	

}
