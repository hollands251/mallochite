package mallochite.implementations.main;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

import mallochite.models.nodes.classes.*;

public class Mallochite 
{
	public static void main ( String [] args ) throws IOException
	{
		
		Scanner scanner = new Scanner( System.in );
		SubNode subNode1 = null;
		
		System.out.println( "enter your IP address" );
		String localIpAddress = scanner.nextLine();

		System.out.println( "enter port to listen on" );
		int portToListen = scanner.nextInt();

		subNode1 = new SubNode( localIpAddress );
		subNode1.startListeningOnPort( portToListen );
	
        try
        {
        	Thread.sleep( 15000 );
			subNode1.openSocket( "192.168.2.19" , 34344 );
            Thread.sleep( 60000 );
			subNode1.closeServerSocket();
			subNode1.closeSocket();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        
		subNode1.closeServerSocket();
		subNode1.closeSocket();
	}
}
