package mallochite.implementations.main;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

import mallochite.models.nodes.classes.*;

public class Mallochite 
{
	public static void main ( String [] args )
	{
		
		Scanner scanner = new Scanner( System.in );
		
		System.out.println( "Listen or send? [L/s]" );
		String mode = scanner.nextLine();	
		System.out.println( "enter your IP address" );
		String localIpAddress = scanner.nextLine();
		SubNode subNode1 = new SubNode( localIpAddress );
		
		try
		{
			if ( mode.equals( "s" ) )
			{
				System.out.println( "enter IP to connect to" );
				String remoteIpAddress = scanner.nextLine();
				System.out.println( "enter port to send on" );
				int portToSend = scanner.nextInt();
				subNode1.openSocket( remoteIpAddress , portToSend );
			}
			else
			{
				System.out.println( "enter port to listen on" );
				int portToListen = scanner.nextInt();
				subNode1.startListeningOnPort( portToListen );
			}

			
            try
            {
                Thread.sleep( 60000 );
            }
            catch( Exception e )
            {
                e.printStackTrace();
            }
            
			subNode1.closeServerSocket();
			subNode1.closeSocket();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
