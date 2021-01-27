package com.citibank.rewards.balance.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.citibank.rewards.balance.exception.BalanceRequestInvalidDataException;
import com.citibank.rewards.balance.exception.BusinessException;
import com.citibank.rewards.balance.exception.SystemException;
import com.citibank.rewards.balance.model.BalanceInfo;
import com.citibank.rewards.balance.model.BalanceRequest;
import com.citibank.rewards.balance.model.BalanceResponse;
import com.citibank.rewards.balance.model.StatusBlock;
import com.citibank.rewards.balance.service.BalanceService;
import com.citibank.rewards.balance.validator.BalanceValidator;

public class BalanceControllerTest {

	@InjectMocks
	BalanceController balanceController;

	@Mock
	BalanceValidator balanceValidator;

	@Mock
	BalanceService balanceService;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(balanceController).build();
		

	}

	@After
	public void tearDown() throws Exception {
	}

	 @Test
	public void testGetBalance_SuccessResp() throws Exception {

		this.mockMvc.perform(get("/balance/232323232323")).andExpect(status().isOk());

	}

	@Test
	public void testGetBalance_SuccessResp_availablePts()
			throws BalanceRequestInvalidDataException, BusinessException, SystemException {
		Mockito.doCallRealMethod().when(balanceValidator).validateRequest(Matchers.any(BalanceRequest.class));
		Mockito.when(balanceService.getBalance(Matchers.any(BalanceRequest.class))).thenReturn(buildMockBalanceResp());
		BalanceResponse balanceResp = balanceController.getBalance("13232323", "23232", "232323", "1212122");

		assertNotNull(balanceResp);
		assertEquals("0", balanceResp.getStatusBlock().getRespCode());
		assertEquals("10000", balanceResp.getBalanceInfo().getAvailablePts());

	}

	@Test(expected = BusinessException.class)
	public void testGetBalance_BusinessException()
			throws BalanceRequestInvalidDataException, BusinessException, SystemException {
		
		Mockito.doCallRealMethod().when(balanceValidator).validateRequest(Matchers.any(BalanceRequest.class));
		Mockito.when(balanceService.getBalance(Matchers.any(BalanceRequest.class)))
				.thenThrow(new BusinessException("bal001", "invalid cardnumber"));
		BalanceResponse balanceResp = balanceController.getBalance("13232323", "23232", "232323", "1212122");

		//assertNull(balanceResp);

	}

	@Test(expected = SystemException.class)
	public void testGetBalance_SystemException() throws BalanceRequestInvalidDataException, BusinessException, SystemException{

		Mockito.doCallRealMethod().when(balanceValidator).validateRequest(Matchers.any(BalanceRequest.class));
		Mockito.when(balanceService.getBalance(Matchers.any(BalanceRequest.class)))
				.thenThrow(new SystemException("100", "invalid cardnumber"));
		BalanceResponse balanceResp = balanceController.getBalance("13232323", "23232", "232323", "1212122");

		 //assertNull(balanceResp);

	}

	private BalanceResponse buildMockBalanceResp() {

		System.out.println("Entered into mock object");
		BalanceResponse balanceResp = new BalanceResponse();

		BalanceInfo balanceInfo = new BalanceInfo();
		balanceInfo.setAvailablePts("10000");
		balanceInfo.setEarnedPts("10000");
		balanceInfo.setPendingPts("10000");

		StatusBlock statusBlock = new StatusBlock();
		statusBlock.setRespCode("0");
		statusBlock.setRespMsg("success");

		balanceResp.setBalanceInfo(balanceInfo);

		balanceResp.setStatusBlock(statusBlock);

		return balanceResp;
	}



}
