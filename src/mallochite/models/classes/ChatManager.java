package mallochite.models.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import mallochite.models.classes.nodes.SubNode;

public class ChatManager
{
	private Socket socket;
	private SubNode subNode;
	
	public ChatManager( SubNode subNode )
	{
		this.subNode = subNode;
	}
	
	
	public void menu()
	{
		Scanner scanner = new Scanner ( System.in );
		String response = scanner.nextLine();
		
		System.out.println( "What would you like to do?" );
		System.out.println( "\t 1. send message" );
		System.out.println( "\t 2. check messages" );
		System.out.println( "\t 3. add contact" );
		
		
		if ( response.equals( "1" ) ) 
		{
			System.out.println( "Who would you like to contact?" );
		}
		else if ( response.equals( "2" ) ) 
		{
			System.out.println( "Whos messages would you like to check?" );
		}
		else if ( response.equals( "3" ) ) 
		{
			System.out.println( "Who would you like to contact?" );
		}
		else
		{
			System.out.println( "Who would you like to connect to" );
			String usernameToSearch = scanner.nextLine();
			User userToConnect = new User();
    		
			if ( userToConnect != null ) 
			{
				System.out.println( "Connecting to " + usernameToSearch );
				//subNode1.makeConnection( userToConnect );
			}
		}
	}
	
	
	public void displayMessages ( User userToDisplayMessages )
	{
		
	}
	
	public void displayContacts()
	{
		
	}
}
