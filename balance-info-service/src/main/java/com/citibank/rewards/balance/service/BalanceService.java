package com.citibank.rewards.balance.service;

import com.citibank.rewards.balance.exception.BusinessException;
import com.citibank.rewards.balance.exception.SystemException;
import com.citibank.rewards.balance.model.BalanceRequest;
import com.citibank.rewards.balance.model.BalanceResponse;

public interface BalanceService {
	public BalanceResponse getBalance(BalanceRequest request)throws BusinessException,SystemException;
		
}
