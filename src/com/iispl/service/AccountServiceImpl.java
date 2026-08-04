package com.iispl.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iispl.model.Account;
import com.iispl.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	Map<String, Account> accountMap = new HashMap<String, Account>();
	AccountRepository accountRepository = new AccountRepository();

	@Override
	public Account searchAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBalance(String accountNumber, BigDecimal amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount(String accountNumber) {
		// TODO Auto-generated method stub
		if(accountMap.containsKey(accountNumber)) {
			accountMap.remove(accountNumber);
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Account> displayAllAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		
	}
	
	public void loadAllAccounts(List<Account> accountList) {
		accountList.forEach(account -> {
			accountMap.put(account.getAccountNumber(), account);
		});
	}

	@Override
	public List<Account> getAllAccounts() {
		return AccountRepository.getAccountList();
	}


}
