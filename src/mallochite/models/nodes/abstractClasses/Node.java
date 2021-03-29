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
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import mallochite.models.exceptions.UninitializedSocket;
import mallochite.models.nodes.classes.*;

public abstract class Node extends Thread
{
	private String hostIpAddress;
	private int portNumberToUse;
    private ServerSocket serverSocket;
	private ConnectionManager connectionManager;
    private boolean listening;
    private Key keypair;
    private User user;
    
    public Node ( String hostIpAddress ) throws NoSuchAlgorithmException
    {
    	this.connectionManager = new ConnectionManager();
    	this.hostIpAddress = hostIpAddress;
    	this.listening = true;
    }
	
    public void startListeningOnPort ( int portNumberToUse ) throws IOException
    {
        try
        {
            this.serverSocket = new ServerSocket( portNumberToUse );
            this.portNumberToUse = portNumberToUse;
            this.listening = true;  
        }
        
        catch ( IOException ex ) { ex.printStackTrace(); }
        
    }
    
	public void closeServerSocket () throws IOException
	{
        try
        {
        	System.out.println("closing server socket");
            this.serverSocket.close();
        }
        catch ( SocketException ex ) { throw ex; }
        finally
        {
            this.serverSocket.close();
        }
	}
    
    public void makeConnection(String remoteIpAddress , int portToListen ) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
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
        	while ( this.listening )
        	{
        		if ( this.serverSocket != null )
        		{
                	Socket socketForListening = this.serverSocket.accept();
                	
                	if ( !this.connectionManager.isAlive() )
                	{
                		this.connectionManager = new ConnectionManager();
                		this.connectionManager.setMetaSocket( socketForListening );
                		this.connectionManager.start();
                	}
        		}
        	}
        }
        
        catch ( IOException | NoSuchAlgorithmException ex ) { }
        
        finally 
        { 
        	this.interrupt(); 
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
	
    public ServerSocket getServerSocket()
	{
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket)
	{
		this.serverSocket = serverSocket;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
