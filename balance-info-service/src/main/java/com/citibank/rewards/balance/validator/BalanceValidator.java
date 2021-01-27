package com.citibank.rewards.balance.validator;

import org.springframework.stereotype.Component;

import com.citibank.rewards.balance.exception.BalanceRequestInvalidDataException;
import com.citibank.rewards.balance.model.BalanceRequest;
@Component
public class BalanceValidator {
	public void validateRequest(BalanceRequest request)throws BalanceRequestInvalidDataException {
		
		if(request.getCardNum()==null ||"".equals(request.getCardNum())) {
			throw new BalanceRequestInvalidDataException("bal001","Invalid CardNum");
		}
		if(request.getClientId()==null ||"".equals(request.getClientId())) {
			throw new BalanceRequestInvalidDataException("bal002","Invalid ClientId");
		}
	
		if(request.getRequestId()==null ||"".equals(request.getRequestId())) {
			throw new BalanceRequestInvalidDataException("bal003","Invalid RequestId");
		}
		if(request.getMsgts()==null ||"".equals(request.getMsgts())) {
			throw new BalanceRequestInvalidDataException("bal004","Invalid Message TimeStamp");
		}
	
	
	}
}
