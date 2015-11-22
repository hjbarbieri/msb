package com.globanttest.requests;

import java.math.BigDecimal;

public class AccountOpenRequest {
	
	private BigDecimal balance;
		
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
