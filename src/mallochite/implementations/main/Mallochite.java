package mallochite.implementations.main;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import mallochite.models.nodes.classes.*;

public class Mallochite 
{
	public static void main ( String [] args ) throws IOException
	{
		
		Scanner scanner = new Scanner( System.in );
		SubNode subNode1 = null;
		boolean makeConnection = true;
		
//		System.out.println( "enter your IP address" );
//		String localIpAddress = scanner.nextLine();

//		System.out.println( "enter port to listen on" );
//		int portToListen = scanner.nextInt();

		try
		{
			subNode1 = new SubNode( "192.168.2.58" );
			subNode1.startListeningOnPort( 32323 );
			
			while ( makeConnection )
			{
	    		System.out.println( "Make Connection? [Y/n]" );
	    		String response = scanner.nextLine();
	    		
	    		if ( response.equals( "n" ) ) 
	    		{
	    			makeConnection = false;
	    			break;
	    		}
	    		else
	    		{
	        		System.out.println( "enter IP address to connect to" );
	        		String remoteIpAddress = scanner.nextLine();

	        		System.out.println( "enter port to connect to" );
	        		int portToListen = scanner.nextInt();
	        		
	        		subNode1.makeConnection( remoteIpAddress , portToListen );
	    		}
			}   
//			subNode1.createSocketForSendingData();
		
			while ( subNode1.isListening() ) { }
		}
		finally
		{
			subNode1.closeServerSocket();
		}
        
	}
}
