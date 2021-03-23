package mallochite.models.nodes.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.HashMap;

public class ConnectionManager extends Thread
{
    private Socket metaSocket;				// responsible for establishing connections, not for chat
    private HashMap<String , Socket> chatSockets;
    private HashMap<String , String> messageSegment;
    //private DatabaseManager dbManager; // get from node???
    private BufferedReader in;
    private PrintWriter out;
    MallochiteMessageManager mallochiteMessageManager = new MallochiteMessageManager();
    
    
    public ConnectionManager( Socket socket ) throws IOException
    {
        this.metaSocket = socket;
        this.in = new BufferedReader( new InputStreamReader( metaSocket.getInputStream() ) );
        this.out = new PrintWriter( metaSocket.getOutputStream() );
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println( "Received a connection" );
            String receivedMessage = in.readLine();
            
            while ( receivedMessage != null && !receivedMessage.equals( "end" ) )
            {	
            	this.messageSegment = mallochiteMessageManager.parseHeader( receivedMessage );
            	
				if ( this.messageSegment != null )
            	{        		
            		this.out.println( messageSegment.get( "method" ) );
            		this.out.println( messageSegment.get( "UUID" ) );
            		this.out.println( messageSegment.get( "ipv4" ) );
            		this.out.println( messageSegment.get( "port" ) );
            		this.out.flush();
            		
            		this.messageSegment = null;
            		
            		//this.openSocketForChat ( messageSegment.get( "IPv4" ) , messageSegment.get( "port" ) );
            	} 
				else
				{
            		this.out.println( "test" );
            		this.out.flush();
				}
            	
				if ( !receivedMessage.equals( "" ) )
            	{
            		System.out.println( receivedMessage );
            		receivedMessage = "";
            	}
            	
            	receivedMessage = in.readLine(); // always listening to the socket
            }

        }
        
        catch( Exception e ) { e.printStackTrace(); }
        
        finally
        {
        	System.out.println( "Connection closed" );
            try
			{
				this.in.close();
				this.metaSocket.close();
			} 
            catch (IOException e) { e.printStackTrace(); }
            
            this.out.close();
            this.interrupt();
        }
    }
    
    /*
     * Creates a socket with the intent of sending a "GREET" header with public key
     * Waits for response from socket and reacts accordingly 
     */
	public void socketForFirstContact( String remoteIpAddress , int portToListen ) throws UnknownHostException, IOException
	{

		Socket socket;
	    BufferedReader in;
	    PrintWriter out;
		HashMap<String , String> localMetaDataHashMap;
        String messageIn = "";
        String messageOut = "GREET:pubkey";
			
        socket = new Socket ( remoteIpAddress , portToListen );
        in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
        out = new PrintWriter( socket.getOutputStream() );
		localMetaDataHashMap = new HashMap<String , String>();

//		localMetaDataHashMap.put( "pubKey" , dataBaseManager.getPubKeyFromDatabase());
//		localMetaDataHashMap.put( "UUID" , dataBaseManager.getPubKeyFromDatabase());
//		localMetaDataHashMap.put( "ipv4" , dataBaseManager.getPubKeyFromDatabase());
		
		// temporary data
		localMetaDataHashMap.put( "publicKey" , "public key");
		localMetaDataHashMap.put( "UUID" , "1");
		localMetaDataHashMap.put( "ipv4" , "192.168.x.x");
		
        
        try
        {
        	// initial message sent to listening socket
        	// GREET should always be the first message sent
    		out.println( messageOut );
    		out.flush();
    		
            while ( !messageIn.contains("DEPART") || !messageIn.contains("INVALID") )
            {
            	if ( in.readLine() != null )
            	{
            		System.out.println( messageIn );
            		HashMap<String , String> clientMetaDataHashMap = mallochiteMessageManager.parseHeader( messageIn );
            		messageOut = mallochiteMessageManager.generateResponse( clientMetaDataHashMap , localMetaDataHashMap);
            	}
            	
            	if ( !messageOut.equals( "" ) )
            	{
            		out.println( messageOut );
            		out.flush();
            	}
            	
            	messageIn = in.readLine();
            }
        }
        finally
        {
        	/*
        	 *Totally self contained socket. After all data is sent, it will close itself. No need for a helper method 
        	 */
            System.out.println( "closing stuff" );
    		socket.close();
            in.close();
            out.close();
        }
		
	}

	public void openSocketForChat(String ipAddressToConnect, String portToUseString) throws UnknownHostException, IOException
	{
		int portToUse = Integer.parseInt( portToUseString );
		
        Socket socketForSendingData = new Socket ( ipAddressToConnect , portToUse );
        ChatManager chatManager = new ChatManager( socketForSendingData );
        chatManager.start();
	}
	


}
