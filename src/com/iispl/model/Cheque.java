package com.iispl.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.iispl.enums.ChequeStatus;

public class Cheque {

	private String chequeNumber;
	private String accountNumber;
	private BigDecimal chequeAmount;
	private LocalDate chequeDate;
	private String branchName;
	private ChequeStatus chequeStatus;

	public Cheque(String chequeNumber, String accountNumber, BigDecimal chequeAmount, LocalDate chequeDate,
			String branchName, ChequeStatus chequeStatus) {
		super();
		this.chequeNumber = chequeNumber;
		this.accountNumber = accountNumber;
		this.chequeAmount = chequeAmount;
		this.chequeDate = chequeDate;
		this.branchName = branchName;
		this.chequeStatus = chequeStatus;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public BigDecimal getChequeAmount() {
		return chequeAmount;
	}

	public LocalDate getChequeDate() {
		return chequeDate;
	}

	public String getBranchName() {
		return branchName;
	}

	public ChequeStatus getChequeStatus() {
		return chequeStatus;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setChequeAmount(BigDecimal chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public void setChequeDate(LocalDate chequeDate) {
		this.chequeDate = chequeDate;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public void setChequeStatus(ChequeStatus chequeStatus) {
		this.chequeStatus = chequeStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, branchName, chequeAmount, chequeDate, chequeNumber, chequeStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cheque other = (Cheque) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(branchName, other.branchName)
				&& Objects.equals(chequeAmount, other.chequeAmount) && Objects.equals(chequeDate, other.chequeDate)
				&& Objects.equals(chequeNumber, other.chequeNumber) && chequeStatus == other.chequeStatus;
	}
	
	

}
