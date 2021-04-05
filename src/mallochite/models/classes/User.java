package mallochite.models.classes;

import java.util.ArrayList;
import java.util.Hashtable;

public class User 
{
	//make everything string for now...
	private String UUID;
	private String PasswordHash;
	private String IP;
	private String AllowedList; //change to list?
	private String AddressBook;
	private String DuressPassword;
	private String username;
	int port;
	Hashtable<String , ArrayList<String> > conversations = new Hashtable<String , ArrayList<String> >();
	private ArrayList<User> userList = new ArrayList<User>();
	private ArrayList<String>[] conversationHolder = new ArrayList[100]; // TODO dirty hack fix later
	private int conversationHolderCount = 0;

	//placeholder?
	public User (String UUID, String passwordHash, String iP, String allowedList, String addressBook,
			String duressPassword) {
		this.UUID = UUID;
		this.PasswordHash = passwordHash;
		this.IP = iP;
		this.AllowedList = allowedList;
		this.AddressBook = addressBook;
		this.DuressPassword = duressPassword;
	}
	
	public void addUser(User user) {
		this.userList.add(user);
	}
	
	public ArrayList<User> getUserList() {
		return this.userList;
	}
	
	
	public User () {}
	
	
	public void addConversation ( User user )
	{
		conversationHolder[ conversationHolderCount ] = new ArrayList<String>();
		this.conversations.put( user.getUUID() , conversationHolder[ conversationHolderCount ] );
		conversationHolderCount++;
	}
	
	
	public void addMessageToConversation ( String uuid , String message )
	{
		System.out.println(uuid);
		ArrayList<String> currentConversation = this.conversations.get( uuid );
		
		currentConversation.add( message );
	}
	

	public String toString() {
		return "Node [UUID=" + UUID + ", PasswordHash=" + PasswordHash + ", IP=" + IP + ", AllowedList=" + AllowedList
				+ ", DuressPassword=" + DuressPassword + "]";
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getPasswordHash() {
		return PasswordHash;
	}

	public void setPasswordHash(String passwordHash) {
		PasswordHash = passwordHash;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getAllowedList() {
		return AllowedList;
	}

	public void setAllowedList(String allowedList) {
		AllowedList = allowedList;
	}

	public String getDuressPassword() {
		return DuressPassword;
	}

	public void setDuressPassword(String duressPassword) {
		DuressPassword = duressPassword;
	}


	public int getPort()
	{
		return this.port;
	}


	public void setPort(int port)
	{
		this.port = port;
	}


	public String getUsername()
	{
		return username;
	}


	public void setUsername(String username)
	{
		this.username = username;
	}
}
