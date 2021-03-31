package mallochite.models.classes;

import java.util.ArrayList;
import java.util.Hashtable;

public class User 
{
	//make everything string for now...
	String UUID;
	String PasswordHash;
	String IP;
	String AllowedList; //change to list?
	String AddressBook;
	String DuressPassword;
	Hashtable<String , ArrayList<String> > conversations = new Hashtable<String , ArrayList<String> >();

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
	
	
	public void addConversation ( String uuid )
	{
		ArrayList<String> conversation = new ArrayList();
		conversations.put( uuid, conversation );
	}
	
	
	public void addMessageToConversation ( String uuid , String message )
	{
		ArrayList<String> currentConversation = conversations.get( uuid );
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
}
