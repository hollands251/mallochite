package mallochite.models.nodes.abstractClasses;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Node extends Thread
{
	private String localIpAddress;
    private Socket socket;
    private ServerSocket serverSocket;
    private BufferedReader in;
    private PrintStream out;
    private boolean listening = false;
    
	public void listenForMessage ( int portNumberToListen )
	{
		
	}
	
	public void sendMessage ( String ipAddress , int portNumber )
	{
		
	}
}
