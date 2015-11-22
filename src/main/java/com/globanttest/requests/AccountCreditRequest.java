package com.globanttest.requests;

import java.math.BigDecimal;

public class AccountCreditRequest {

	private BigDecimal balance;
	private Long transactionID;
	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}
	
}
