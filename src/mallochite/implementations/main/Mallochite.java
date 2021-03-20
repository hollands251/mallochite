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
			subNode1.openSocket("192.168.2.58", 43434);
			subNode1.closeSocket();
		}
		catch ( IOException ex )
		{
			ex.printStackTrace();
		}
		
	}
}
