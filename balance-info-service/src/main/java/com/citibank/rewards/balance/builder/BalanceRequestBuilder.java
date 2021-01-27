package com.citibank.rewards.balance.builder;

import org.springframework.stereotype.Component;

import com.citibank.rewards.balance.model.BalanceRequest;
@Component
public class BalanceRequestBuilder {
	
	public BalanceRequest buildRequest(String cardnum,String clientId,String requestId,String msgTs) {
		BalanceRequest request=new BalanceRequest();
		request.setCardNum(cardnum);
		request.setClientId(clientId);
		request.setRequestId(requestId);
		request.setMsgts(msgTs);
		return request;
	}
}
