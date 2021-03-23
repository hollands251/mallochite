package mallochite.models.nodes.abstractClasses;

/*
 * Andrew Hollands
 * March 20th 2020
 * holandre@sheridancollege.ca
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import mallochite.models.exceptions.UninitializedSocket;
import mallochite.models.nodes.classes.*;

public abstract class Node extends Thread
{
	private String hostIpAddress;
    private ServerSocket serverSocket;
    private ConnectionManager connectionManager;
    private boolean listening;
    
    public Node ( String hostIpAddress )
    {
    	this.hostIpAddress = hostIpAddress;
    	this.listening = false;
    }
	
	public void closeServerSocket () throws IOException
	{
        try
        {
            this.serverSocket.close();
            this.listening = false;
            this.interrupt();
        }
        catch ( SocketException ex ) { throw ex; }
	}
	
    public void startListeningOnPort ( int portNumberToUse )
    {
        try
        {
            this.serverSocket = new ServerSocket( portNumberToUse );
            this.listening = true;
            this.start();    
        }
        
        catch ( IOException ex ) { ex.printStackTrace(); }
    }
	
	/* runs methods on a separate thread 
	 * 
	 * 
	 */
	@Override
	public void run ()
	{
        try 
        {
        	Socket socketForListening = this.serverSocket.accept();
        	this.connectionManager = new ConnectionManager( socketForListening );
            this.connectionManager.start();
        }
        
        catch ( IOException ex ) { ex.printStackTrace(); }
	}

	
	public void createSocketForSendingData() throws UnknownHostException, IOException
	{
		Scanner scanner = new Scanner ( System.in );
	    BufferedReader in;
	    PrintWriter out;
		Socket socket;
		
		System.out.println( "enter IP address to connect to" );
		String remoteIpAddress = scanner.nextLine();

		System.out.println( "enter port to connect to" );
		int portToListen = scanner.nextInt();
		
		socket = new Socket ( remoteIpAddress , portToListen );
        in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
        out = new PrintWriter( socket.getOutputStream() );
        
        //for ( int i = 0 ; i < 100000 ; i++ )  { } // waits for thread. Very bad practice, for debugging only

    	String messageOut = "";
    	String messageIn = "";
        
        try
        {
        	// initial message sent to listening socket
    		out.println( "HELLO" );
    		out.flush();
    		
            while ( !messageOut.equals("end") )
            {
            	if ( in.readLine() != null )
            	{
            		System.out.println( messageIn );
            	}
            	
            	if ( !messageOut.equals( "" ) )
            	{
            		out.println( messageOut );
            		out.flush();
            	}

            	messageOut = scanner.nextLine();
            	
            	System.out.println( in.readLine() );
            }
        }
        finally
        {
            System.out.println( "closing stuff" );
    		socket.close();
            in.close();
            out.close();
        }
		
	}

	public String getHostIpAddress()
	{
		return hostIpAddress;
	}
	
	public void setHostIpAddress(String hostIpAddress)
	{
		this.hostIpAddress = hostIpAddress;
	}

	public boolean isListening()
	{
		return listening;
	}

	public void setListening(boolean listening)
	{
		this.listening = listening;
	}
}
