package com.iispl.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.iispl.enums.ChequeStatus;
import com.iispl.exceptions.AccountNotFoundException;
import com.iispl.exceptions.DuplicateChequeException;
import com.iispl.exceptions.InsufficientFundsException;
import com.iispl.model.Account;
import com.iispl.model.Cheque;
import com.iispl.repository.ChequeRepository;
import com.iispl.validations.ChequeInvalidAmountValidation;
import com.iispl.validations.ChequeNumberValidation;
import com.iispl.validations.ChequeValidator;
import com.iispl.validations.InsufficientBalanceValidation;

public class ChequeServiceImpl implements ChequeService {

	ChequeRepository chequeRepository = new ChequeRepository();
	Set<Cheque> chequeSet = new TreeSet<Cheque>(Comparator.comparing(Cheque::getChequeAmount, Comparator.reverseOrder())
			.thenComparing(Cheque::getChequeNumber));
	Set<String> chequeNumberSet = new HashSet<String>();
	Map<String, Integer> branchCountMap = new HashMap<String, Integer>();
	AccountService accountService = null;
	List<ChequeValidator> validationRules = null;

	public ChequeServiceImpl(AccountService accountService) {
		this.accountService = accountService;

		validationRules = new ArrayList<ChequeValidator>();

		validationRules.add(new ChequeNumberValidation());
		validationRules.add(new ChequeInvalidAmountValidation());
		validationRules.add(new InsufficientBalanceValidation(accountService));
	}

	@Override
	public void validateCheques(List<Cheque> chequeList){
		for(Cheque cheque : chequeList) {

			boolean isValid = true;
			try {
				Account account = accountService.searchAccount(cheque.getAccountNumber());
				int errorCode = accountService.validateAccount(account);
				
				if(errorCode == 0) {
					if(addChequeNumber(cheque.getChequeNumber())) {
						
						for(ChequeValidator rule : validationRules) {
							if(!rule.validate(cheque)) {
								isValid = false;
								if(rule instanceof ChequeNumberValidation) {
									cheque.setChequeStatus(ChequeStatus.REJECTED_INVALID_CHEQUE_NUMBER);
								} else if(rule instanceof ChequeInvalidAmountValidation) {
									cheque.setChequeStatus(ChequeStatus.REJECTED_CHEQUE_AMOUNT_INVALID);
								}
							}
						}
					}
						
				} else if(errorCode == 1) {
					isValid = false;
					cheque.setChequeStatus(ChequeStatus.REJECTED_INVALID_ACCOUNT_NUMBER);
				} else {
					isValid = false;
					cheque.setChequeStatus(ChequeStatus.REJECTED_ACCOUNT_INACTIVE);
				}
				
			} catch(AccountNotFoundException exception) {
				isValid = false;
				cheque.setChequeStatus(ChequeStatus.REJECTED_ACCOUNT_NOT_FOUND);
				exception.getMessage();
			} catch(InsufficientFundsException exception) {
				isValid = false;
				cheque.setChequeStatus(ChequeStatus.REJECTED_INSUFFICIENT_FUNDS);
			} catch(DuplicateChequeException exception) {
				isValid = false;
				cheque.setChequeStatus(ChequeStatus.REJECTED_DUPLICATE_CHEQUE_NUMBER);
			}
			
			if(isValid) {
				cheque.setChequeStatus(ChequeStatus.ACCEPTED);
			}
			
			chequeSet.add(cheque);
			updateBranchChequeCount(cheque.getBranchName());
				
		}
		
	}

	@Override
	public boolean addChequeNumber(String chequeNumber) throws DuplicateChequeException {
		if (!chequeNumberSet.add(chequeNumber)) {
			throw new DuplicateChequeException();
		}

		return true;
	}

	@Override
	public Set<Cheque> displayProcessedCheques() {
		return chequeSet;
	}

	@Override
	public boolean removeProcessedCheque(String chequeNumber) {

		Iterator<Cheque> iterator = chequeSet.iterator();

		while (iterator.hasNext()) {
			Cheque cheque = iterator.next();
			if (cheque.getChequeNumber().equals(chequeNumber)) {
				chequeSet.remove(cheque);
				return true;
			}

		}
		return false;
	}

	@Override
	public void updateBranchChequeCount(String branchName) {

		if (branchCountMap.containsKey(branchName)) {
			branchCountMap.put(branchName, branchCountMap.get(branchName) + 1);
			return;
		}
		branchCountMap.put(branchName, 1);
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
