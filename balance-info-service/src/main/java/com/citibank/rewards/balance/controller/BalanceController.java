package com.citibank.rewards.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.citibank.rewards.balance.builder.BalanceRequestBuilder;
import com.citibank.rewards.balance.exception.BalanceRequestInvalidDataException;
import com.citibank.rewards.balance.exception.BusinessException;
import com.citibank.rewards.balance.exception.SystemException;
import com.citibank.rewards.balance.model.BalanceRequest;
import com.citibank.rewards.balance.model.BalanceResponse;
import com.citibank.rewards.balance.service.BalanceService;
import com.citibank.rewards.balance.service.impl.BalanceServiceImpl;
import com.citibank.rewards.balance.validator.BalanceValidator;

@RestController
//@Component
public class BalanceController {
	@Autowired
	private BalanceService balanceService;
	@Autowired
	private BalanceValidator balanceValidator;
	
	@RequestMapping(method=RequestMethod.GET ,value="/balance/{cardNum}",produces="application/json")
	public BalanceResponse getBalance(@PathVariable("cardNum") String cardNum,
			@RequestHeader(value="client-Id",required=true)String clientId,
			@RequestHeader(value="request-Id",required=true)String requestId,
			@RequestHeader(value="msg-ts",required=true)String msgts) throws BusinessException, SystemException, BalanceRequestInvalidDataException
	{
		
		
	//prepare The balance Request
	BalanceRequestBuilder requestbuilder=new BalanceRequestBuilder();
	BalanceRequest request=requestbuilder.buildRequest(cardNum, clientId, requestId, msgts);
		//validate therequest
	//BalanceValidator validator=new BalanceValidator();
	balanceValidator.validateRequest(request);

	//prepare the request for service layer
	//BalanceService service=new BalanceServiceImpl();
	//call the service layer
	BalanceResponse response=balanceService.getBalance(request);
	return response;
	}

	
}
