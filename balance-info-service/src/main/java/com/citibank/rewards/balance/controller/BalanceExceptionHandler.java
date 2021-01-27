package com.citibank.rewards.balance.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.citibank.rewards.balance.exception.BalanceRequestInvalidDataException;
import com.citibank.rewards.balance.exception.BusinessException;
import com.citibank.rewards.balance.exception.SystemException;
import com.citibank.rewards.balance.model.BalanceResponse;
import com.citibank.rewards.balance.model.StatusBlock;
@RestControllerAdvice
public class BalanceExceptionHandler {
	@ExceptionHandler(value=BalanceRequestInvalidDataException.class)
	public BalanceResponse handleRequestInvalidException(BalanceRequestInvalidDataException exception) {
		BalanceResponse response=buildErrorResp(exception.getRespCode(),exception.getRespMsg());
		return response;
		
	}
	
	@ExceptionHandler(value=BusinessException.class)
	public BalanceResponse handleDataErrors(BusinessException exception) {
		BalanceResponse response=buildErrorResp(exception.getRespCode(),exception.getRespMsg());
		return response;
		
	}
	@ExceptionHandler(value=SystemException.class)
	public BalanceResponse handleSystemErrors(SystemException exception) {
		BalanceResponse response=buildErrorResp(exception.getRespCode(),exception.getRespMsg());
		return response;
		
	}
	@ExceptionHandler(value=Exception.class)
	public BalanceResponse handleGenericErrors(Exception exception) {
		BalanceResponse response=buildErrorResp("22222","UnKnown Error From Database:" +exception.getMessage());
		return response;
		
	}
	
	
	public BalanceResponse buildErrorResp(String respCode,String respMsg) {
		BalanceResponse response=new BalanceResponse();
		StatusBlock statusBlock=new StatusBlock();
		statusBlock.setRespCode(respCode);
		statusBlock.setRespMsg(respMsg);
		return response;
	}
}
