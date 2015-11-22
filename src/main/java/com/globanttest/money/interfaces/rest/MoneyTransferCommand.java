package com.globanttest.money.interfaces.rest;

import java.math.BigDecimal;

public class MoneyTransferCommand {

	private Long fromAccount;
	private Long toAccount;
	private BigDecimal amount;
	
	public MoneyTransferCommand(Long fromAccount, Long toAccount,BigDecimal amount) {
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}
	
	public Long getToAccount() {
		return toAccount;
	}
	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}
	public Long getFromAccount() {
		return fromAccount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
}
