package com.globanttest.money.interfaces.rest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.globanttest.money.requests.MoneyTransferRequest;
import com.globanttest.money.services.MoneyTransferService;

@Controller
public class MoneyTransferController {
	
	@Autowired
	private MoneyTransferService moneyTransferService;

	@RequestMapping(value="/transfer",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> transfer(@RequestBody MoneyTransferRequest moneyTransferRequest){
		if((moneyTransferRequest.getAmount().compareTo(BigDecimal.ZERO) < 0))
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		
		moneyTransferService.transfer(moneyTransferRequest.getAmount(), moneyTransferRequest.getFromAccount(), moneyTransferRequest.getToAccount());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
