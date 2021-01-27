package com.citibank.rewards.balance.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.citibank.rewards.balance.dao.BalanceDAO;
import com.citibank.rewards.balance.exception.BusinessException;
import com.citibank.rewards.balance.exception.SystemException;
import com.citibank.rewards.balance.model.BalanceDAORequest;
import com.citibank.rewards.balance.model.BalanceDAOResponse;
import com.citibank.rewards.balance.model.BalanceRequest;
import com.citibank.rewards.balance.model.BalanceResponse;
import com.citibank.rewards.balance.service.BalanceService;

public class BalanceServiceImplTest {

	@InjectMocks
	BalanceServiceImpl balanceServiceImpl;

	@Mock
	BalanceDAO balanceDAO;
	
	

	@Before
	public void setUp() throws Exception {
		
		
		MockitoAnnotations.initMocks(this);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetBalance() throws BusinessException, SystemException {

		BalanceRequest request = new BalanceRequest();
		request.setCardNum("12121212212");
		request.setClientId("web");
		request.setMsgts("23232323");
		request.setRequestId("122323");
		
		//expectation
		Mockito.when(balanceDAO.getBalance(Matchers.any(BalanceDAORequest.class))).thenReturn(buildBalanceDAOResp());

		BalanceResponse response = balanceServiceImpl.getBalance(request);

		assertNotNull(response);
		assertEquals("success", response.getStatusBlock().getRespMsg());

	}

	private BalanceDAOResponse buildBalanceDAOResp() {
		
		BalanceDAOResponse daoResp = new BalanceDAOResponse();
		daoResp.setAvailablePts("10000");
		daoResp.setEarnedPts("10000");
		daoResp.setPendingPts("10000");
		daoResp.setRespCode("0");
		daoResp.setRespMsg("success");
		
		
		return daoResp;
	}

}
