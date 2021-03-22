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
    private Socket socket;
    private ServerSocket serverSocket;
    private String messageBuffer;
    
    /* Must have hostIp Address
     */
    public Node ( String hostIpAddress )
    {
    	this.hostIpAddress = hostIpAddress;
    }
    
    /*
     * optionally set port right away instead of getter and setter
     */
    public Node ( String hostIpAddress , int portToUse )
    {
    	this.hostIpAddress = hostIpAddress;
    }
    
    // must add a stopListenOnPort method
	
	
	/* In: 			take no arguments but works with this.in , this.out and this.socket
	 * Process: 	closes all attributes listed above
	 * out:			Returns void on success, throws an IOException or SocketException on fail
	 */
	public void closeSocket () throws SocketException , IOException
	{
        try
        {
            this.socket.close();
        }
        catch ( SocketException ex )
        {
            throw ex;
        }
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
	
	
	
	/* in: 		takes a String to send to client
	 * process:	prints message to this.out (which is the output for the socket)
	 * out:		returns void on success, throws SocketException on fail
	 */
//	public void sendMessage ( String message ) throws SocketException
//	{
//		this.out.println( message );
//		this.out.flush();
//	}
	
	
    /* in: 		Takes no arguments but works with this.in
     * process: Reads from this.in and stores the value in messageBuffer
     * out: 	Return void or throws IOException on failure
     */
//	public void updateMessageBuffer () throws IOException , UninitializedSocket
//	{
//		if ( this.socket != null )
//		{
//	        try
//	        {
//	            this.messageBuffer = this.in.readLine();
//	            
//	            while( this.messageBuffer != null )
//	            {
//	                this.messageBuffer += in.readLine();
//	            }
//	        }
//	        catch ( IOException iOException )
//	        {
//	            throw iOException;
//	        }
//		}
//		else
//		{
//			UninitializedSocket usEx = new UninitializedSocket( "this method requires openSocket to be run first" );
//			throw usEx;
//		}
//	}
	
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
	
    /* in: 		Takes String of IP address to send message to and port as an int
     * process: Tries to open Socket that connects to a remote client. Creates a buffer reader and printstream
     * 			to listen to the socket's traffic 
     * out: 	Return void or throws SocketException or IOException on failure
     */
	public void openSocket ( String remoteIpAddress , int portNumberToUse ) throws IOException , SocketException
	{
		
        try
        {
            this.socket = new Socket ( remoteIpAddress , portNumberToUse );
        }
        catch ( SocketException socketException )
        {
            throw socketException;
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
            	
                // Pass the socket to the RequestHandler thread for processing
                RequestHandler requestHandler = new RequestHandler( socketForListening );
                
                requestHandler.start();
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


//	public String getMessageBuffer() throws UninitializedSocket , IOException
//	{
//		try
//		{
//			this.updateMessageBuffer ();
//			return messageBuffer;
//		}
//		catch ( UninitializedSocket ex )
//		{
//			throw ex = new UninitializedSocket ("this method requires openSocket to be run first");
//		}
//		
//	}


	public void setMessageBuffer(String messageBuffer)
	{
		this.messageBuffer = messageBuffer;
	}
	
	
}
