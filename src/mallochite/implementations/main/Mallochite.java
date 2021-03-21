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
		
		System.out.println( "enter your IP address" );
		String localIpAddress = scanner.nextLine();
		System.out.println( "enter IP to connect to" );
		String remoteIpAddress = scanner.nextLine();
		System.out.println( "enter port to listen on" );
		int port = scanner.nextInt();
		SubNode subNode1 = new SubNode( localIpAddress );
		
		try
		{
			subNode1.startListeningOnPort( port );
			subNode1.openSocket( remoteIpAddress , port );
			
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
