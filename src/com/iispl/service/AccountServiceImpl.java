package com.iispl.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iispl.exceptions.AccountNotFoundException;
import com.iispl.model.Account;
import com.iispl.repository.AccountRepository;
import com.iispl.validations.AccountNumberValidation;
import com.iispl.validations.AccountStatusValidation;
import com.iispl.validations.AccountValidator;

public class AccountServiceImpl implements AccountService {
	
	Map<String, Account> accountMap = new HashMap<String, Account>();
	AccountRepository accountRepository = new AccountRepository();
	List<AccountValidator> validationRules = null;
	
	public AccountServiceImpl() {
		validationRules = new ArrayList<AccountValidator>();
		
		validationRules.add(new AccountNumberValidation());
		validationRules.add(new AccountStatusValidation());
		
	}

	@Override
	public Account searchAccount(String accountNumber) throws AccountNotFoundException {
	    if( accountMap.containsKey(accountNumber)) {
	    	 
	    	return  accountMap.get(accountNumber);
	    }
	    else {
	    	 throw new AccountNotFoundException();
	    }
	}

	@Override
	public boolean updateBalance(String accountNumber, BigDecimal amount) {
		if( accountMap.containsKey(accountNumber)) {
			accountMap.get(accountNumber).setAccountBalance(amount);
			return true;
		}
		else {
			
			return false;
		}
	}

	@Override
	public boolean deleteAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Account> displayAllAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int validateAccount(Account account) {
		
		for(AccountValidator rule : validationRules) {
			if(!rule.validate(account)) {
				if(rule instanceof AccountNumberValidation) {
					return 1;
				} else if(rule instanceof AccountStatusValidation) {
					return 2;
				}
			}
		}
		
		return 0;
		
	} 

	@Override
	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		accountMap.put(account.getAccountNumber(), account);
		
	}
	
	public void loadAllAccounts(List<Account> accountList) {
		accountList.forEach(account -> {
			addAccount(account);
		});
	}

	@Override
	public List<Account> getAllAccounts() {
		return AccountRepository.getAccountList();
	}


}
