package com.globanttest.money.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globanttest.money.domain.MoneyTransfer;
import com.globanttest.money.interfaces.rest.MoneyTransferCommand;

@Service
//Adapter converts external request and events into commands
public class MoneyTransferServiceImpl implements MoneyTransferService{

	@Autowired
	//Aggregate Route
	private MoneyTransfer moneyTransfer;
	
	@Override
	public void transfer(BigDecimal balance,Long fromAccount,Long toAccount) {
		MoneyTransferCommand moneyTransferCommand = new MoneyTransferCommand(fromAccount,toAccount,balance);
		moneyTransfer.processMoneyTransfer(moneyTransferCommand);
	}
}
