package mallochitechatapp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.util.ArrayList;


public class MallochiteMain {

	static final String driver = "com.mysql.jdbc.Driver";
    static final String url = "jdbc:mysql://127.0.0.1:3306/mallochite"; 
    static final String username = "root";	
    static final String password = "Pass@2021"; //my personal password
    static String sql;
    static ResultSet rs;
    static ResultSet rs1;

	public static void main(String[] args) throws Exception
	{
		
	      // TODO Auto-generated method stub
		getConnection();
		createTable();
		insert();
		get();
		delete();

	}
	
	public static Connection getConnection() throws Exception
	 {   
		    Connection conn = null;
	       // Statement stmt = null;
	    try {
	        Class.forName(driver);
	        conn = DriverManager.getConnection(url,username,password); 
	      //  stmt = conn.createStatement();  
	        // Class.forName(driver);
	   	   
	        System.out.println("Connected");	   
	        return conn;
	        
	  } catch (Exception e) {
		   System.out.println(e);
	  }
	  
	  
	  return null; //j unit failed connection
	 }


	 //create table
	 public static boolean createTable() throws Exception
	 {
		Connection conn = null;
		Statement stmt = null;
		   
	 try {
	     Class.forName(driver);
	     conn = DriverManager.getConnection(url,username,password); 
	     stmt = conn.createStatement();  
	     sql = "CREATE TABLE IF NOT EXISTS Node1(nodeID int NOT NULL AUTO_INCREMENT, UUID varchar(36), Password varchar(15), IPAddress varchar(16), AllowedListID int, AddressBookID int, DuressPass varchar(20) PRIMARY KEY(nodeID))";
	     stmt.executeUpdate(sql);
	   
	     return true; //was added  
	    }

	    catch (Exception e)
	    {
	        System.out.println("Exception:" + e);
	    }

	       return false; //was not added
	  }

	
	 
	 //insert into table
	 public static boolean insert() throws Exception
	 {
	   Connection conn = null;
	   Statement stmt = null;
		 
	   final String varUUID = "276111e33fd242688f14670706799f3f"; //placeholder variables
	   final String varPass = "password";
	   final String varIP = "127.0.0.1";
	   final int varAllowList = 1;
	   final int varAddBook = 1;
	   final String varDuress = "duress";
	   
		   
	 try {
	     Class.forName(driver);
	     conn = DriverManager.getConnection(url,username,password); 
	     stmt = conn.createStatement();  
	     sql = "INSERT INTO Node1 (UUID, PasswordHash, IPAddress, AllowListID, AddressBook, DuressPassword) VALUES ('"+varUUID+"', '"+varPass+"', '"+varIP+"', "
	   		+ "'"+varAllowList+"', '"+varAddBook+"', '"+varDuress+"')"; //placeholder statement"
	      stmt.executeUpdate(sql);
	      
	      int rowsInserted =  stmt.executeUpdate(sql);
	      if (rowsInserted > 0) {
		      System.out.println("A new user was inserted successfully!");
	         }
	   
	       return true;
	     }   catch(Exception e) {
		        System.out.println(e);	   
	            return false;
	  }
	  finally {
		  System.out.println("Insert completed");
	  }
	  	  
	 }
	 
 
	 //Select data
	 public static ArrayList<String> get() throws Exception{
		 Connection conn = null;
		 Statement stmt = null;
			   
		 try {
		     Class.forName(driver);
		     conn = DriverManager.getConnection(url,username,password); 
		     stmt = conn.createStatement();  
		     sql = "SELECT * FROM Node1"; //placeholder statement will work for anytable
	   
	         ResultSet result = stmt.executeQuery(sql);
	   
	         ArrayList<String> array = new ArrayList<String>();
	         while(result.next()) {
	              System.out.print(result.getString("UUID")); //column names
	              System.out.print(" ");
	              System.out.println(result.getString("Password"));
	              System.out.print(" ");
	              System.out.println(result.getString("IPAddress"));
	              System.out.print(" ");
	              System.out.println(result.getString("AllowedListID"));
	              System.out.print(" ");
	              System.out.println(result.getString("AddressBookID"));
	              System.out.print(" ");
	              System.out.println(result.getString("DuressPassword"));
	      
	      
	              array.add(result.getString("UUID")); //add to array list for display
	     }
	   
	      System.out.println("All records have been selected!");
	      return array;
	     
	  } catch(Exception e) {
	        System.out.println(e);
	        
	    return null;
	  }
	  
	 }
	 
	 //delete a row?
	 public static boolean delete() throws Exception
	 {
		 Connection conn = null;
		 Statement stmt = null;
			   
		 try {
		     Class.forName(driver);
		     conn = DriverManager.getConnection(url,username,password); 
		     stmt = conn.createStatement();  
		     sql = "DELETE FROM Node1 WHERE Password ='password'"; 
	   
		     stmt.executeUpdate(sql);
		     
		     return true;
	   
	  } catch(Exception e) {		  
		  	System.out.println(e);	   
		    return false;
	  }
	    finally {
		  System.out.println("delete completed");
	   }
	 }
	 
	 //Create connection
	 static String databaseName = "studentdatabase"; //temp database IF TESTING USE CREATED MYSQL DATABSE
	
	
	

	

}
