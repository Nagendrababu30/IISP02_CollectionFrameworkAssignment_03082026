package com.iispl.service;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.iispl.exceptions.DuplicateChequeException;
import com.iispl.model.Cheque;

public interface ChequeService {

	public void validateCheques(List<Cheque> chequeList) throws DuplicateChequeException;
	
	public List<Cheque> getAllCheques();
	
	public boolean addChequeNumber(String chequeNumber);
	
	public TreeSet<Cheque> displayProcessedCheques();
	
	public boolean removeProcessedCheque(Cheque cheque);
	
	public void updateBranchChequeCount(String branchName);
	
	public Map<String, Integer> getBranchReport();
}
