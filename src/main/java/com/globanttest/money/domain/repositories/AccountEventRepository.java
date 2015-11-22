package com.globanttest.money.domain.repositories;

import com.globanttest.money.domain.events.AccountEvent;

public interface AccountEventRepository {

	void persist(AccountEvent event);
	
	
}
