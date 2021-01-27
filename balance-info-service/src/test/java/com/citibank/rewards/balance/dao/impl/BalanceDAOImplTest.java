package com.citibank.rewards.balance.dao.impl;

import static org.junit.Assert.assertNotNull;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.citibank.rewards.balance.exception.BusinessException;
import com.citibank.rewards.balance.exception.SystemException;
import com.citibank.rewards.balance.model.BalanceDAORequest;
import com.citibank.rewards.balance.model.BalanceDAOResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ BalanceDAOImpl.class })
public class BalanceDAOImplTest {

	Connection mockConnection;
	CallableStatement mockCsmt;
	ResultSet mockRs;

	@Test
	public void testGetBalance() throws BusinessException, SystemException, SQLException {

		System.setProperty("env", "dev");

		mockConnection = PowerMockito.mock(Connection.class);
		mockCsmt = PowerMockito.mock(CallableStatement.class);
		mockRs = PowerMockito.mock(ResultSet.class);

		PowerMockito.mockStatic(DriverManager.class);
		PowerMockito.when(DriverManager.getConnection(Matchers.anyString(), Matchers.anyString(), Matchers.anyString()))
				.thenReturn(mockConnection);

		PowerMockito.when(mockConnection.prepareCall(Matchers.anyString())).thenReturn(mockCsmt);
		
		PowerMockito.when(mockCsmt.executeQuery()).thenReturn(mockRs);
		PowerMockito.when(mockCsmt.getString(3)).thenReturn("000");

		PowerMockito.when(mockRs.next()).thenReturn(true).thenReturn(false);

		PowerMockito.when(mockCsmt.getString("avail_pts")).thenReturn("100000");
		PowerMockito.when(mockCsmt.getString("earned_pts")).thenReturn("100000");
		PowerMockito.when(mockCsmt.getString("adjusted_pts")).thenReturn("100000");

		BalanceDAOImpl dao = new BalanceDAOImpl();

		BalanceDAORequest daoReq = new BalanceDAORequest();
		daoReq.setCardNum("12323323");
		daoReq.setClientId("web");

		BalanceDAOResponse daoResp = dao.getBalance(daoReq);

		assertNotNull(daoResp);

	}

}
