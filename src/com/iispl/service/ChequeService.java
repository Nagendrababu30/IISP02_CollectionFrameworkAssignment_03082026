package com.iispl.service;

import java.util.List;
import java.util.TreeSet;

import com.iispl.model.Cheque;

public interface ChequeService {

	public void validateCheques(List<Cheque> chequeList);
	
	public List<Cheque> getAllCheques();
	
	public boolean addCheque(Cheque cheque);
	
	public boolean addChequeNumber(String chequeNumber);
	
	public TreeSet<Cheque> displayProcessedCheques();
	
	public boolean removeProcessedCheque(Cheque cheque);
	
	public void addChequeToQueue(Cheque cheque);
	
	public void updateBranchChequeCount(String branchName);
}
