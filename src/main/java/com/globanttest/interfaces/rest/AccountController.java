package com.globanttest.interfaces.rest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.globanttest.requests.AccountCreditRequest;
import com.globanttest.requests.AccountDebitRequest;
import com.globanttest.requests.AccountOpenRequest;
import com.globanttest.services.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value="/openAccountEvent",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> openAccountEvent(@RequestBody AccountOpenRequest accountOpenRequest){
		if((accountOpenRequest.getBalance().compareTo(BigDecimal.ZERO) < 0))
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		
		accountService.openAccount(accountOpenRequest.getBalance());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/debitAccountEvent",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> debitAccountEvent(@RequestBody AccountDebitRequest accountDebitRequest){
		if((accountDebitRequest.getBalance().compareTo(BigDecimal.ZERO) < 0))
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		
		accountService.debitAccount(accountDebitRequest.getBalance(),accountDebitRequest.getTransactionID());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/creditAccountEvent",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> creditAccountEvent(@RequestBody AccountCreditRequest accountCreditRequest){
		if((accountCreditRequest.getBalance().compareTo(BigDecimal.ZERO) > 0))
			return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
		
		accountService.creditAccount(accountCreditRequest.getBalance(),accountCreditRequest.getTransactionID());
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
