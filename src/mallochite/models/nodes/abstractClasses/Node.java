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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import mallochite.models.exceptions.UninitializedSocket;
import mallochite.models.nodes.classes.*;

public abstract class Node extends Thread
{
	private String hostIpAddress;
    private ServerSocket serverSocket;
    private ConnectionManager connectionManager;
    
    public Node ( String hostIpAddress )
    {
    	this.hostIpAddress = hostIpAddress;
    }
	
	public void closeServerSocket () throws IOException
	{
        try
        {
            this.serverSocket.close();
            this.interrupt();
        }
        catch ( SocketException ex )
        {
            throw ex;
        }
	}
	
    public void startListeningOnPort ( int portNumberToUse )
    {
        try
        {
            this.serverSocket = new ServerSocket( portNumberToUse );
        }
        catch ( IOException ex )
        {
            ex.printStackTrace();
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
        	if ( this.serverSocket != null )
        	{
            	Socket socketForListening = this.serverSocket.accept();
            	this.connectionManager = new ConnectionManager( socketForListening );
                this.connectionManager.start();
        	}
        	else
        	{
        		UninitializedSocket usEx = new UninitializedSocket();
        		throw usEx;
        	}
            
        }
        catch ( IOException | UninitializedSocket ex )
        {
            ex.printStackTrace();
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
}
