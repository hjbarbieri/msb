package com.globanttest.money.domain;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.globanttest.domain.events.AccountEvent;
import com.globanttest.domain.events.AccountEventType;
import com.globanttest.money.domain.repositories.AccountEventRepository;
import com.globanttest.money.interfaces.rest.MoneyTransferCommand;

@Component
public class MoneyTransfer {

	@Autowired
	private AccountEventRepository accountEventRepository;
	
	private Long fromAccount;
	private Long toAccount;
	private BigDecimal amount;
	
	public void processMoneyTransfer(MoneyTransferCommand moneyTransferCommand) {
		
		AccountEvent moneyFromAccountTransferEvent = new AccountEvent(moneyTransferCommand.getAmount().multiply(BigDecimal.valueOf(-1)),moneyTransferCommand.getFromAccount(),AccountEventType.DEBIT);
		AccountEvent moneyToAccountTransferEvent = new AccountEvent(moneyTransferCommand.getAmount(),moneyTransferCommand.getToAccount(),AccountEventType.CREDIT);
		
		try {
			accountEventRepository.persist(moneyFromAccountTransferEvent);
			accountEventRepository.persist(moneyToAccountTransferEvent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public Long getFromAccount() {
		return fromAccount;
	}

	public Long getToAccount() {
		return toAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	
}
