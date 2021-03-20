package mallochite.implementations.main;

import java.io.IOException;
import java.net.SocketException;

import mallochite.models.nodes.classes.*;

public class Mallochite 
{
	public static void main ( String [] args )
	{
		SubNode subNode1 = new SubNode("192.168.2.58");
		SubNode subNode2 = new SubNode("192.168.2.58");
		
		try
		{
			subNode1.startListeningOnPort( 64341 );
			
            try
            {
                Thread.sleep( 60000 );
            }
            catch( Exception e )
            {
                e.printStackTrace();
            }
            
			subNode1.closeServerSocket();
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
