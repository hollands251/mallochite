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

//		System.out.println( "enter port to listen on" );
//		int portToListen = scanner.nextInt();

		subNode1 = new SubNode( "192.168.2.58" );
		subNode1.startListeningOnPort( 34343 );
	
		while ( subNode1.isListening() ) { }
		
    	subNode1.closeServerSocket();
        
	}
}
