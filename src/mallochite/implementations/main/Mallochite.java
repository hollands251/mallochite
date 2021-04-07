package mallochite.implementations.main;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import mallochite.models.classes.*;
import mallochite.models.classes.nodes.SubNode;

public class Mallochite 
{
	public static void main ( String [] args ) throws IOException, NoSuchAlgorithmException, InterruptedException
	{
		
		Scanner scanner = new Scanner( System.in );
		SubNode subNode1 = null;
		User thisUser = null;
		User remoteUser = new User();	
		
		System.out.println("pick your port");
		int port = scanner.nextInt();
		
		String uuid = scanner.nextLine();
		
		System.out.println("set your UUID");
		uuid = scanner.nextLine();
		
		thisUser = new User();
		thisUser.setUsername( "user1" );
		thisUser.setIP( "192.168.0.16" );
		
		thisUser.setPort( port );
		thisUser.setUUID( uuid );
 

		try
		{
			subNode1 = new SubNode( thisUser );
			subNode1.openServerSocket( subNode1.getThisUser().getPort());
			subNode1.start();
			ChatManager manager  = new ChatManager(subNode1);
			
			while( subNode1.isListening() )
			{
				manager.menu();
			}   
      
		}
		finally
		{
			if ( subNode1.getServerSocket() != null )
			{
				subNode1.closeServerSocket();
			}
		}
        
	}
}
