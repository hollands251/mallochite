package mallochite.models.nodes.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Key;
import java.security.KeyPair;
import java.util.Scanner;

import mallochite.encryption.RSAEncryption;

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
    
    public ConnectionManager()
    {
    	
    }
    
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
            String messageIn = "";
            byte[] messageOut = null;
            String message = ""; 
            HashMap<String , String> localMetaDataHashMap = new HashMap<String , String>();

//    		localMetaDataHashMap.put( "pubKey" , dataBaseManager.getPubKeyFromDatabase());
//    		localMetaDataHashMap.put( "UUID" , dataBaseManager.getPubKeyFromDatabase());
//    		localMetaDataHashMap.put( "ipv4" , dataBaseManager.getPubKeyFromDatabase());
            
            KeyPair keyPair = RSAEncryption.keyGenerator();
            Key pk = keyPair.getPublic();
    		
    		// temporary data
    		//localMetaDataHashMap.put( "publicKey" , pk.toString());
    		localMetaDataHashMap.put( "UUID" , "1");
    		localMetaDataHashMap.put( "ipv4" , "192.168.x.x");
            
            while ( messageIn != null )
            {	
            	
            	
            	messageIn = in.readLine();
            	if ( messageIn != null )
            	{
            		System.out.println( messageIn );
            		
            	
            		
            		HashMap<String , String> clientMetaDataHashMap = mallochiteMessageManager.parseHeader( messageIn, pk );
            		messageOut = mallochiteMessageManager.generateResponseServer( clientMetaDataHashMap , localMetaDataHashMap, keyPair);
            		
            		
            		if(!messageIn.contains("GREET")) {
            			//DECRYPTION HERE!!!!!!!!!!!!!!!!!
            		message= RSAEncryption.decrypt(keyPair.getPrivate(), messageOut);
            		}
            		else if (messageIn.contains("GREET")) {
            			for(byte b: messageOut) {
        					message = message + (char)b;
        				}
            		}
            		
            		
            	}
            	
            	if ( messageIn == null || messageIn.equals( "OPEN" ) )
            	{
            		break;
            	}
            	
            	if ( messageOut != null )
            	{
            		// encrypt 
            		out.println(message);
            		out.flush();
            	}
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
    		
            while ( messageIn != null )
            {
            	messageIn = in.readLine(); 
            	
            	if ( messageIn != "" )
            	{
            		System.out.println( messageIn );
            		// decrypt messageIn
            		HashMap<String , String> clientMetaDataHashMap = mallochiteMessageManager.parseHeader( messageIn, pk );
            		messageOut = mallochiteMessageManager.generateResponseSocket( clientMetaDataHashMap , localMetaDataHashMap);
            	}
            	
            	if ( !messageOut.equals( "" ) )
            	{
            		// encrypt meessageOut
            		out.println( messageOut );
            		out.flush();
            	}
            	
            	if ( messageOut.contains("DEPART") )
            	{
            		continue;
            	}
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
	

	public void openSocketForChat(String ipAddressToConnect, String portToUseString) throws UnknownHostException, IOException
	{
		int portToUse = Integer.parseInt( portToUseString );
		
        Socket socketForSendingData = new Socket ( ipAddressToConnect , portToUse );
        ChatManager chatManager = new ChatManager( socketForSendingData );
        chatManager.start();
	}
	
    public Socket getMetaSocket()
	{
		return metaSocket;
	}

	public void setMetaSocket(Socket metaSocket) throws IOException
	{
		this.metaSocket = metaSocket;
        this.in = new BufferedReader( new InputStreamReader( metaSocket.getInputStream() ) );
        this.out = new PrintWriter( metaSocket.getOutputStream() );
	}

}
