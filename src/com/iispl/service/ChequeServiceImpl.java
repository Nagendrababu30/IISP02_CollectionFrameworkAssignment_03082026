package com.iispl.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import com.iispl.enums.ChequeStatus;
import com.iispl.exceptions.AccountNotFoundException;
import com.iispl.exceptions.DuplicateChequeException;
import com.iispl.model.Account;
import com.iispl.model.Cheque;
import com.iispl.repository.ChequeRepository;
import com.iispl.validations.ChequeNumberValidation;
import com.iispl.validations.ChequeValidator;

public class ChequeServiceImpl implements ChequeService {
	
	ChequeRepository chequeRepository = new ChequeRepository();
	Set<Cheque> chequeSet = new TreeSet<Cheque>(Comparator.comparing(Cheque::getChequeAmount).thenComparing(Cheque::getChequeNumber, Comparator.reverseOrder()));
    Set<String> chequeNumberSet = null;
    Map<String, Integer> branchCountMap = null;
    Queue<Cheque> chequeQueue = null;
    AccountService accountService = new AccountServiceImpl();
    List<ChequeValidator> validationRules = null;
    
    public ChequeServiceImpl() {
    	validationRules = new ArrayList<ChequeValidator>();
    	
    	validationRules.add(new ChequeNumberValidation());
    }

	@Override
	public void validateCheques(List<Cheque> chequeList) throws DuplicateChequeException {
		for(Cheque cheque : chequeList) {

			try {
				Account account = accountService.searchAccount(cheque.getAccountNumber());
				int errorCode = accountService.validateAccount(account);
				
				if(errorCode == 0) {
					if(addChequeNumber(cheque.getChequeNumber())) {
						
						for(ChequeValidator rule : validationRules) {
							if(!rule.validate(cheque)) {
								if(rule instanceof ChequeNumberValidation) {
									cheque.setChequeStatus(ChequeStatus.REJECTED_CHEQUE_AMOUNT_INVALID);
								}
							} else {
								cheque.setChequeStatus(ChequeStatus.ACCEPTED);
							}
						}
						
					} else {
						
						cheque.setChequeStatus(ChequeStatus.REJECTED_DUPLICATE_CHEQUE_NUMBER);
						throw new DuplicateChequeException();
						
					}
				} else if(errorCode == 1) {
					cheque.setChequeStatus(ChequeStatus.REJECTED_ACCOUNT_NOT_FOUND);
				} else {
					cheque.setChequeStatus(ChequeStatus.REJECTED_ACCOUNT_INACTIVE);
				}
				
			} catch(AccountNotFoundException exception) {
				cheque.setChequeStatus(ChequeStatus.REJECTED_ACCOUNT_NOT_FOUND);
				exception.getMessage();
			}
			
			chequeSet.add(cheque);
				
		}
		
	}

	@Override
	public boolean addChequeNumber(String chequeNumber) {
		chequeNumberSet = new HashSet<String>(); 
		return chequeNumberSet.add(chequeNumber);
	}

	@Override
	public Set<Cheque> displayProcessedCheques() {
		return chequeSet;
	}

	@Override
	public boolean removeProcessedCheque(String chequeNumber) {
		
		Iterator<Cheque> iterator=chequeSet.iterator();
		
		while(iterator.hasNext()) {
			Cheque cheque=iterator.next();
			if(cheque.getAccountNumber().equals(chequeNumber)) {
				chequeSet.remove(cheque);
				return true;
			}
			
		}
		return false;
	}

	@Override
	public void updateBranchChequeCount(String branchName) {
		branchCountMap = new HashMap<String, Integer>();
		
		if(branchCountMap.containsKey(branchName)) {
			branchCountMap.put(branchName, branchCountMap.get(branchName)+1);
			return;
		}
		branchCountMap.put(branchName,1);
	}

	@Override
	public Map<String, Integer> getBranchReport() {
		return branchCountMap;
	}

	@Override
	public List<Cheque> getAllCheques() {
		return ChequeRepository.getChequeList();
	}

}
