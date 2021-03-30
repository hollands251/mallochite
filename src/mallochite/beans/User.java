package mallochite.beans;

public class User {

	
	
	//make everything string for now...
	String UUID;
	String PasswordHash;
	String IP;
	String AllowedList; //change to list?
	String AddressBook;
	String DuressPassword;
	
	//placeholder?
	public User(String UUID, String passwordHash, String iP, String allowedList, String addressBook,
			String duressPassword) {
		UUID = UUID;
		PasswordHash = passwordHash;
		IP = iP;
		AllowedList = allowedList;
		AddressBook = addressBook;
		DuressPassword = duressPassword;
	}

	@Override
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
