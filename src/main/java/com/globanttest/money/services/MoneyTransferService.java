package com.globanttest.money.services;

import java.math.BigDecimal;

public interface MoneyTransferService {

	void transfer(BigDecimal balance, Long fromAccount, Long toAccount);

}
