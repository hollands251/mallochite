package mallochitechatapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Wrapper;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




class MallochiteMainTest {

	@BeforeEach
	public void setUp() throws Exception {
			
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetConnection()throws Exception {
		
		Connection conn = (Connection) MallochiteMain.getConnection();
        System.out.println(conn);
	}
	
	@Test
	public void testCreateTableGood() throws Exception {
		
		
		boolean resultDB = MallochiteMain.createTable();
		boolean expected = true;
		assertEquals(expected,resultDB);
			
	}

	@Test
	public void testCreateTableBad() throws Exception {
		
		
		boolean resultDB = MallochiteMain.createTable();
		String value= Boolean.toString(resultDB);
		int result = Integer.parseInt(value);
		int finalResult = result -5;
		String strValue = String.valueOf(finalResult);
		assertThrows(Exception.class, ()-> {Integer.parseInt(strValue);});
			
	}
	
	@Test
	void testInsertGood() throws Exception {
		
	
		boolean res = MallochiteMain.insert();
		boolean expectedValue = true;
		assertEquals(res, expectedValue);		
	}
	
	@Test
	void testInsertBad() throws Exception {
		
		
		boolean res = MallochiteMain.insert();
		boolean expectedValue = false;
		assertEquals(res, expectedValue);
		
	}

	@Test
	void testGet() throws Exception {
		
		Connection conn = (Connection) MallochiteMain.getConnection();
		
		  final String varUUID = "276111e33fd242688f14670706799f3f"; //placeholder variables
		  final String varPass = "password";
		  final String varIP = "127.0.0.1";
		  final int varAllowList = 1;
		  final int varAddBook = 1;
		  final String varDuress = "duress";
		
		  PreparedStatement statment = conn.prepareStatement("SELECT * FROM Node1"); //placeholder statement will work for anytable
		   
		   ResultSet result = statment.executeQuery();
		   
		   //ArrayList<String> array = new ArrayList<String>();
		   
		     while(result.next()) {
		    	 assertEquals(result.getString("UUID"), varUUID); //column names
		    	 assertEquals(result.getString("Password"), varPass);
		    	 assertEquals(result.getString("IPAdress"), varIP);
		    	 assertEquals(result.getString("AllowedListID"), varAllowList);
		    	 assertEquals(result.getString("AddressBookID"), varAddBook);
		    	 assertEquals(result.getString("DuressPassword"), varDuress);
		     }		
	}

	@Test
	void testDeleteGood() throws Exception {

		
		boolean res = MallochiteMain.delete();
		boolean expectedValue = true;
		assertEquals(res, expectedValue);
	}
	
	@Test
	void testDeleteBad() throws Exception {

	
		boolean res = MallochiteMain.delete();
		boolean expectedValue = true;
		assertEquals(res, expectedValue);
	}
	

}


