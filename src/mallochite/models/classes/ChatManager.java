package mallochite.models.classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mallochite.models.classes.nodes.SubNode;
import ui.FrameUserChat;

public class ChatManager
{
	private Socket socket;
	private SubNode subNode;
	private Scanner sc = new Scanner(System.in);
	FrameUserChat frameChat = new FrameUserChat();
	
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
			frameChat.setVisible(true);
			
			User userToContact = null;
			System.out.println( "Who would you like to contact?" );
			String userName = this.sc.nextLine();
		    
			ArrayList<User> userList = (ArrayList<User>) this.subNode.getThisUser().getUserList();
			
			for(User user: userList ){
				if(user.getUsername().equals( userName )) {
				
					
					//frameChat.setTextArea_1();
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
			frameChat.setVisible(true);
			
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
						frameChat.setTextArea_1(userName+": "+message);
						
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
	String messageToSend = "";
	
	private void sendMessage(User userToContact) throws IOException  ///////////////////////////////////////rgdeftqjnklmdflesrnmawk jewfrashjn ikijuhbnfswdea hjuikm,.n bdfseaw hybjnu, kmdfsea
	{
		messageToSend = "";
		System.out.println("Enter message to send: ");	
				
		frameChat.getBtnSendMsg().addActionListener((new ActionListener() {
 
		 

			@Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
		    	messageToSend = frameChat.gettxtChatArea()+"";		
		    	System.out.println("workd");
		    	
		    }
		}));
			
		
		while (true) {
			//messageToSend = "";
			System.out.print("WHY IS THIS HERE"); //IF NOT HERE IT BREAK
			
			 if (messageToSend.length() > 0)
			 {
				 frameChat.setTextArea_1("You: "+messageToSend);
				 this.subNode.makeConnection(userToContact, messageToSend);
				 break;
			 }
		}
		
		
	
		
		
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
		
		
			contact.setUsername( this.subNode.getThisUser().getUserList().get( 0 ).getUsername() );
			contact.setIP( this.subNode.getThisUser().getUserList().get( 0 ).getIP() );
			contact.setUUID( this.subNode.getThisUser().getUserList().get( 0 ).getUUID() );
			contact.setPort( this.subNode.getThisUser().getUserList().get( 0 ).getPort() );
		
			/*//System.out.println("Enter the username:");
			String contactUsername = this.sc.nextLine();
			
			//System.out.println("Enter the IP address: ");
			String contactIP = this.sc.nextLine();
			
			//System.out.println("Enter the UUID ");
			String contactUUID = this.sc.nextLine();
			
			//System.out.println("Enter the port:  ");
			int contactPort = this.sc.nextInt();

			contact.setUsername(contactUsername);
			contact.setIP(contactIP);
			contact.setUUID(contactUUID);
			//contact.setPort(contactPort);*/
		
		
		this.subNode.getThisUser().addUser( contact );
		this.subNode.getThisUser().addConversation( contact );
	}
}

