package mallochite.models.nodes.classes;

//dummy class to store data until DB implementation 
public class User
{
	public int id;
	public String username;
	public String password;
	public String[] message = new String[100];
	public String ipAddress;
	public int port;
	public User[] user = new User[10];
	public int userCount , messageCount = 0;
	
	public void addMessage ( String messageIn )
	{
		this.message[ messageCount ] = messageIn;
		this.messageCount++;
	}
	
	public void addUser ( User user )
	{
		this.user[ this.userCount ] = user;
		this.userCount++;
	}
}
