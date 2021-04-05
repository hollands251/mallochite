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
		
		thisUser = new User();
		thisUser.setUUID( "1234-12346743-3423567" );
		thisUser.setUsername( "user1" );
		thisUser.setIP( "192.168.0.16" );
		thisUser.setPort( 23232 );
 

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
