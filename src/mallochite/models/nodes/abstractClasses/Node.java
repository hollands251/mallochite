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
    
    public void makeConnection(String remoteIpAddress , int portToListen )
    {
		try
		{
			this.connectionManager.socketForFirstContact( remoteIpAddress, portToListen );
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
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
