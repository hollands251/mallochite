package mallochite.models.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mallochite.models.classes.nodes.SubNode;

public class ChatManager
{
	private Socket socket;
	private SubNode subNode;
	private Scanner sc = new Scanner(System.in);
	
	private boolean debugging = false;
	
	public ChatManager( SubNode subNode )
	{
		this.subNode = subNode;
	}
	
	
	public void menu() throws IOException, InterruptedException
	{
		Scanner scanner = new Scanner ( System.in );
		
		System.out.println( "What would you like to do?" );
		System.out.println( "\t 1. send message" );
		System.out.println( "\t 2. check messages" );
		System.out.println( "\t 3. add contact" );
		System.out.println( "\t 4. list contacts" );
		
		String response = scanner.nextLine();
		
		if ( response.equals( "1" ) ) 
		{
			User userToContact = null;
			System.out.println( "Who would you like to contact?" );
			String userName = this.sc.nextLine();
		    
			ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
			
			for(User user: userList ){
				if(user.getUsername().equals( userName )) {
				
					userToContact = user;
					this.sendMessage( userToContact );
				}
				else
				{
					System.out.println( "user not found" );
				}
			}

		}
		else if ( response.equals( "2" ) ) 
		{	
			User userToRead = null;
			System.out.println( "Whos messages would you like to check?" );
			String userName = this.sc.nextLine();
		    
			ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
			
			for(User user: userList )
			{
				if(user.getUsername().equals( userName )) 
				{		
					userToRead = user;
					
					ArrayList<String> conversation = userToRead.getConversation();
					
					for ( String message : conversation )
					{
						System.out.println( message );
					}
				}
			}
			
			if ( userToRead == null )
			{
				System.out.println( "user not found" );
			}
		}
		else if ( response.equals( "3" ) ) 
		{
			this.addContact();
		}
		else if ( response.equals( "4" ) )
		{
			this.displayContacts();
		}
	}
	
	
	private void sendMessage(User userToContact) throws IOException 
	{
		
		System.out.println("Enter message to send: ");
		String messageToSend = this.sc.nextLine();
		
		this.subNode.makeConnection(userToContact, messageToSend);
	}


	public void displayMessages ( User userToDisplayMessages )
	{
		
	}
	
	public void displayContacts()
	{
		ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
		
		for(User user: userList ){
			System.out.println(user.getUsername());
		}
	}
	
	public void addContact() 
	{
		User contact = new User();
		
//		System.out.println("Enter the username:");
//		String contactUsername = this.sc.nextLine();
//		
//		System.out.println("Enter the IP address: ");
//		String contactIP = this.sc.nextLine();
//		
//		System.out.println("Enter the UUID ");
//		String contactUUID = this.sc.nextLine();
//		
//		System.out.println("Enter the port:  ");
//		int contactPort = this.sc.nextInt();
//
//		contact.setUsername(contactUsername);
//		contact.setIP(contactIP);
//		contact.setUUID(contactUUID);
//		contact.setPort(contactPort);
		
		contact.setUsername("user2");
		contact.setIP("192.168.0.16");
		contact.setUUID("asdf-321");
		contact.setPort(23456);
		
		this.subNode.getThisUser().addUser( contact );
		this.subNode.getThisUser().addConversation( contact );
	}
}
