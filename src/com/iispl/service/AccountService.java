package com.iispl.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.iispl.model.Account;

public interface AccountService {
	
	public void validateAccount(Account account);
	
	public List<Account> getAllAccounts();
	
	public Account searchAccount(String accountNumber);
	
	public boolean updateBalance(String accountNumber, BigDecimal amount);
	
	public boolean deleteAccount(String accountNumber);
	
	public Map<String, Account> displayAllAccount();
	
	public void addAccount(Account account);
	
	public void loadAllAccounts(List<Account> accountList);
	
}
