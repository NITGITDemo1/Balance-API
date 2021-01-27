package com.citibank.rewards.balance.validator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.citibank.rewards.balance.exception.BalanceRequestInvalidDataException;
import com.citibank.rewards.balance.model.BalanceRequest;

public class BalanceValidatorTest {
	BalanceRequest request=null;
	
	@Before
	public void setup() {
		buildRequest();
	}

	public void buildRequest() {
		request = new BalanceRequest();
		request.setCardNum("23232323233232");
		request.setClientId("web");
		request.setRequestId("232323");
		request.setMsgts("21-01-2021");
	
	}
	
	@Test
	public void testCardNumber_Null_Scenarios() {
		request.setCardNum(null);
		BalanceValidator validator=new BalanceValidator();
		try {
			validator.validateRequest(request);
		}
		catch(BalanceRequestInvalidDataException e) {
			assertEquals("bal001",e.getRespCode());
		}
	}
	@Test
	public void testCardNumber_Empty_Scenarios() {
		request.setCardNum(" ");
		BalanceValidator validator=new BalanceValidator();
		
		try {
			validator.validateRequest(request);
		}
		catch(BalanceRequestInvalidDataException e) {
			assertEquals("bal001",e.getRespCode());
		}
	}
	@Test
	public void testClientId_Null_Scenaraios() throws BalanceRequestInvalidDataException {
		request.setClientId(null); 
		BalanceValidator validator=new BalanceValidator();
		try {
		validator.validateRequest(request);
		}
		catch(BalanceRequestInvalidDataException e) {
			assertEquals("bal002",e.getRespCode());
		}//catch
	}//methd
	@Test
	public void testClientId_Empty_Scenarios() throws BalanceRequestInvalidDataException{
		request.setClientId(" ");
		BalanceValidator validator=new BalanceValidator();
		try {
			validator.validateRequest(request);
		} catch (BalanceRequestInvalidDataException e) {
			assertEquals("bal002",e.getRespCode());
		}//catch
	}//method
	@Test
	public void testRequestId_Null_Scenarios()throws BalanceRequestInvalidDataException{
		request.setRequestId(null);
		BalanceValidator validator=new BalanceValidator();
		try {
			validator.validateRequest(request);
		} catch (BalanceRequestInvalidDataException e) {
			assertEquals("bal003",e.getRespCode());
		}//catch
	}//method
	@Test
	public void testRequestId_Empty_Scenarios() {
		request.setRequestId(" ");
		BalanceValidator validator=new BalanceValidator();
		try {
			validator.validateRequest(request);
		} catch (BalanceRequestInvalidDataException e) {
			assertEquals("bal003",e.getRespCode());
		}//catch
	}//method
	@Test
	public void testMessageTS_Null_Scenarios() {
		request.setMsgts(null);
		BalanceValidator validator=new BalanceValidator();
		try {
			validator.validateRequest(request);
		} catch (BalanceRequestInvalidDataException e) {
			assertEquals("bal004",e.getRespCode());
			
		}//catch
	}//method
	
	public void testMessageTS_Empty_Scenarios() {
		request.setMsgts(" ");
		BalanceValidator validator=new BalanceValidator();
		try {
			validator.validateRequest(request);
		} catch (BalanceRequestInvalidDataException e) {
			assertEquals("bal004",e.getRespCode());
		}
	}
	@After
	public void tierDown() {
		request=null;
	}
}



