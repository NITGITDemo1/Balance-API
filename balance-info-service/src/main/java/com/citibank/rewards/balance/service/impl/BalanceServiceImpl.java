package com.citibank.rewards.balance.service.impl;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citibank.rewards.balance.dao.BalanceDAO;
import com.citibank.rewards.balance.exception.BusinessException;
import com.citibank.rewards.balance.exception.SystemException;
import com.citibank.rewards.balance.model.BalanceDAORequest;
import com.citibank.rewards.balance.model.BalanceDAOResponse;
import com.citibank.rewards.balance.model.BalanceInfo;
import com.citibank.rewards.balance.model.BalanceRequest;
import com.citibank.rewards.balance.model.BalanceResponse;
import com.citibank.rewards.balance.model.StatusBlock;
import com.citibank.rewards.balance.service.BalanceService;
@Service
public class BalanceServiceImpl implements BalanceService {
	
	private Logger logger=Logger.getLogger(BalanceServiceImpl.class);
	
	@Autowired
	private BalanceDAO balanceDAO;
	
	@Override
	public BalanceResponse getBalance(BalanceRequest request) throws BusinessException, SystemException {
		logger.info("In Service Layer Request From Controller:"+request);
		BalanceDAORequest daoReq=new BalanceDAORequest();
		daoReq.setCardNum(request.getCardNum());
		daoReq.setClientId(request.getClientId());
		//call the DAO Layer and get the DAOResponse
		//BalanceDAO dao=new BalanceDAOImpl();
		BalanceDAOResponse daoResp=balanceDAO.getBalance(daoReq);
		
		//prepare the service response
		BalanceResponse response=new BalanceResponse();
		StatusBlock statusBlock=new StatusBlock();
		statusBlock.setRespCode(daoResp.getRespCode());
		statusBlock.setRespMsg(daoResp.getRespMsg());
		 BalanceInfo balanceInfo=new BalanceInfo();
		 balanceInfo.setAvailablePts(daoResp.getAvailablePts());
		 balanceInfo.setEarnedPts(daoResp.getEarnedPts());
		 balanceInfo.setPendingPts(daoResp.getPendingPts());
		 
		 response.setStatusBlock(statusBlock);
		 response.setBalanceInfo(balanceInfo);
		 logger.info("In Service Layer Response From Controller:"+response);
		return response;
	}

}
