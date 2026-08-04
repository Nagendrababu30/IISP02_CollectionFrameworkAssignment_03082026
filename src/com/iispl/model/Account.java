package com.iispl.model;

import java.math.BigDecimal;
import java.util.Objects;

import com.iispl.enums.AccountStatus;

public class Account {

	private String accountNumber;
	private BigDecimal accountBalance;
	private AccountStatus accountStatus;

	public Account(String accountNumber, BigDecimal accountBalance, AccountStatus accountStatus) {
		super();
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.accountStatus = accountStatus;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountBalance, accountNumber, accountStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountBalance, other.accountBalance)
				&& Objects.equals(accountNumber, other.accountNumber) && accountStatus == other.accountStatus;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountBalance=" + accountBalance + ", accountStatus="
				+ accountStatus + "]";
	}
	
	

}
