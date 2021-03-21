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
		System.out.println( "enter IP to connect to" );
		String remoteIpAddress = scanner.nextLine();
		System.out.println( "enter port to listen on" );
		int portToListen = scanner.nextInt();
		System.out.println( "enter port to send on" );
		int portToSend = scanner.nextInt();
		
	
        try
        {
			subNode1 = new SubNode( localIpAddress );
			subNode1.startListeningOnPort( portToListen );
			subNode1.openSocket( remoteIpAddress , portToSend );
            Thread.sleep( 60000 );
			subNode1.closeServerSocket();
			subNode1.closeSocket();
        }
        catch( Exception e )
        {
        	if ( subNode1 != null )
        	{
    			subNode1.closeServerSocket();
    			subNode1.closeSocket();
        	}

            e.printStackTrace();
        }
	}
}
