package mallochite.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import mallochite.beans.User;




public class GetBeanDatabase {
	static ArrayList<User> userList = new ArrayList<User>();	
	public static void main(String[] args) throws Exception {
		

		getConnection();
		//getToNode();
		insert();
		
		
		//decide on which one to update
		//Update()
		
	}

	
	
	//Create connection
	static String databaseName = "mallochite"; 
	public static Connection getConnection() throws Exception
	{
		try {
			String driver = "com.mysql.jdbc.Driver";
		    String url = "jdbc:mysql://localhost:3306/" +databaseName; 
		    String username = "root";
			String password = "1234"; //my personal sql password
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");		
			return conn;
		} catch (Exception e)
		{
			System.out.println(e);
		}
	return null; 
	}
	
	//static ArrayList<User> userList = new ArrayList<User>();	
	//public static Node getToNode() throws Exception{
	public static ArrayList<User> getToNode() throws Exception{
		try
		{
			String table = "node"; //for dynamic table selection
			String UUID = "";
			String PasswordHash = "";
			String IP= "";
			String AllowedList = ""; //change to list?
			String AddressBook = "";
			String DuressPassword = "";						
			
			Connection conn = getConnection();
			PreparedStatement statment = conn.prepareStatement("SELECT * FROM "+table); //placeholder statement will work for anytable
			
			ResultSet result = statment.executeQuery();
			
			ArrayList<User> array = new ArrayList<User>();
					while(result.next()) {
						UUID = result.getInt("UUID")+"";	
						PasswordHash = result.getString("PasswordHash");
						IP = result.getString("IP");
						AllowedList =result.getString("AllowedList");
						AddressBook = result.getString("AddressBook");
						DuressPassword = result.getString("DuressPassword");	
						
						User user = new User(UUID, PasswordHash ,IP, AllowedList, AddressBook ,DuressPassword);
						userList.add(user);
						array.add(user);
					}			
					System.out.println("All records have put into node");
								
					return array;				
		} catch(Exception e) {
			System.out.println(e);
		return null;
		}	
	}
	
	//insert with attack prevention
	//insert into table
		public static boolean insert() throws Exception{
			//string.....
			
			int UUID = 1; 
			String passwordHash = "PasswordHash1";
			String iP = "IP1";
			String allowedList = "AllowedList1";
			String duressPassword = "DuressPassword1";
			
			
			try
			{
				Connection conn = getConnection();
				String insert = "INSERT INTO node (UUID, PasswordHash , IP , AllowedList, DuressPassword) VALUES (?, ? , ? , ?, ?)";
				//PreparedStatement insert = conn.prepareStatement("INSERT INTO node (UUID, PasswordHash , IP , AllowedList, DuressPassword) VALUES ('"+1+"', 'PasswordHash1' , 'IP1' , 'AllowedList1', 'DuressPassword1')"); z
				PreparedStatement ps = conn.prepareStatement(insert); 
				ps.setInt(1, UUID);
				ps.setString(2, passwordHash);
				ps.setString(3, iP);
				ps.setString(4, allowedList);
				ps.setString(5, duressPassword);
				
				
				ps.executeUpdate();
				
				
				return true;
			} catch(Exception e) {
				System.out.println(e);
				
				return false;
			}
			finally {
				System.out.println("Insert completed");
			}
			
			
		}
		
		public static boolean UpdateUser(int UUID, String whatToUpdate) throws Exception{ //decide on which one to update
			try
			{
			Connection conn = getConnection();
			
			String choose = whatToUpdate;
			String insert = "";
			
			if (choose.equals("PasswordHash"))
			{
				insert = "UPDATE node SET PasswordHash =? WHERE appartmentnum =UUID ";
			}
			else if (choose.equals("IP"))
			{
				insert = "UPDATE node SET PasswordHash =? WHERE appartmentnum =UUID ";
			}
			else if (choose.equals("DuressPassword"))
			{
				insert = "UPDATE node SET PasswordHash =? WHERE appartmentnum =UUID ";
			}	
				
				PreparedStatement ps = conn.prepareStatement(insert); 
			
				
				
				ps.executeUpdate();
				
				
				return true;
			} catch(Exception e) {
				System.out.println(e);
				
				return false;
			}
			finally {
				System.out.println("Insert completed");
			}
		
		}
	
	
	
	
	
	
}
