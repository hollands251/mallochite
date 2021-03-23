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
		
//		System.out.println( "enter your IP address" );
//		String localIpAddress = scanner.nextLine();
//
//		System.out.println( "enter port to listen on" );
//		int portToListen = scanner.nextInt();

		try
		{
			subNode1 = new SubNode( "192.168.2.19" );
			subNode1.startListeningOnPort( 33373 );
//			subNode1.createSocketForSendingData();
		
			while ( subNode1.isListening() ) { }
		}
		finally
		{
			subNode1.closeServerSocket();
		}
        
	}
}
