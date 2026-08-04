package com.iispl.exceptions;

public class AccountNotFoundException extends Exception {

	public AccountNotFoundException() {
		super("AccountNotFoundException account not found.");
	}
	
}
