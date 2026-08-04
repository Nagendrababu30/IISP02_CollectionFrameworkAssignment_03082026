package com.iispl.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.iispl.enums.AccountStatus;
import com.iispl.model.Account;

public class AccountRepository {

	static List<Account> accountList = null;
	
	static {
		
		accountList = new ArrayList<Account>();
		
		accountList.add(new Account("ACC1001", new BigDecimal("50000.00"), AccountStatus.ACTIVE));
		accountList.add(new Account("ACC1002", new BigDecimal("120000.00"), AccountStatus.ACTIVE));
		accountList.add(new Account("ACC1003", new BigDecimal("2500.00"), AccountStatus.ACTIVE));
		accountList.add(new Account("ACC1004", new BigDecimal("75000.00"), AccountStatus.INACTIVE));
		accountList.add(new Account("ACC1005", new BigDecimal("950000.00"), AccountStatus.ACTIVE));

		accountList.add(new Account("", new BigDecimal("10000.00"), AccountStatus.ACTIVE));              
		accountList.add(new Account("ACC1006", new BigDecimal("5000.00"), AccountStatus.ACTIVE));       
		accountList.add(new Account("ACC1007", new BigDecimal("5000.00"), AccountStatus.ACTIVE));                  
		accountList.add(new Account("ACC1008", new BigDecimal("25000.00"), AccountStatus.ACTIVE));                       
		
	} 
	
	public static List<Account> getAccountList() {
		return accountList;
	}
	
}
