package com.globanttest.money.domain;

import java.math.BigDecimal;

import com.globanttest.domain.events.AccountEvent;
import com.globanttest.domain.events.AccountEventType;
import com.globanttest.interfaces.rest.OpenAccountCommand;

public class MoneyTransfer {

	private Long fromAccount;
	private Long toAccount;
	private BigDecimal amount;
	
	public void processMoneyTransfer(OpenAccountCommand accountOpenCommand) {
		AccountEvent accountOpenEvent = new AccountEvent(accountOpenCommand.getInitialBalance(),12L,AccountEventType.OPEN);
		
		accountEventRepository.persist(accountOpenEvent);
		
	}
}
