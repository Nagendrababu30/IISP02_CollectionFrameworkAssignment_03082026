package com.iispl.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import com.iispl.model.Account;
import com.iispl.model.Cheque;
import com.iispl.repository.ChequeRepository;

public class ChequeServiceImpl implements ChequeService {
	
	ChequeRepository chequeRepository = new ChequeRepository();
	Set<Cheque> chequeSet = null;
    Set<String> chequeNumberSet = null;
    Map<String, Integer> branchCountMap = null;
    Queue<Cheque> chequeQueue = null;
    AccountService accountService = new AccountServiceImpl();
//    List<E>

	@Override
	public void validateCheques(List<Cheque> chequeList) {
		chequeList.forEach(cheque -> {
			Account account = accountService.searchAccount(cheque.getAccountNumber());
			accountService.validateAccount(account);
			
			
			
		});
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addCheque(Cheque cheque) {
		chequeSet = new TreeSet<Cheque>();
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addChequeNumber(String chequeNumber) {
		chequeNumberSet = new HashSet<String>(); 
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Cheque> displayProcessedCheques() {
		return chequeSet;
	}

	@Override
	public boolean removeProcessedCheque(Cheque cheque) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateBranchChequeCount(String branchName) {
		branchCountMap = new HashMap<String, Integer>();
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Integer> getBranchReport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cheque> getAllCheques() {
		return ChequeRepository.getChequeList();
	}

}
