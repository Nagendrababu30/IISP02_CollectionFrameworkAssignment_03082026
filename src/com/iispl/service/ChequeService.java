package com.iispl.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.iispl.model.Cheque;

public interface ChequeService {
	
	public boolean addCheque(Cheque cheque);

	public void validateCheques(List<Cheque> chequeList);
	
	public List<Cheque> getAllCheques();
	
	public boolean addChequeNumber(String chequeNumber);
	
//	--------------------------------------------
	
	public Set<Cheque> displayProcessedCheques();
	
	public boolean removeProcessedCheque(String chequeNumber);
	
	public void updateBranchChequeCount(String branchName);
	
	public Map<String, Integer> getBranchReport();
}
