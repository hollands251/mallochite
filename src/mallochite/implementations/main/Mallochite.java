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

		try
		{
			subNode1 = new SubNode( "192.168.2.58" );
			
			while ( subNode1.isListening() )
			{
				
				subNode1.startListeningOnPort( 23894 );
				subNode1.start();
				
	    		System.out.println( "Make Connection? [Y/n]" );
	    		String response = scanner.nextLine();
	    		
	    		if ( response.equals( "n" ) ) 
	    		{
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
      
		} catch (InvalidKeyException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
