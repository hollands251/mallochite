package mallochite.models.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class ConnectionManager extends Thread
{
    private Socket metaSocket;	// responsible for listening for incoming connections
    private MallochiteMessageManager mallochiteMessageManager = new MallochiteMessageManager();
    private User thisUser;
    private boolean metaSocketAvailable;
    
    public ConnectionManager()
    {
    	this.metaSocketAvailable = false; // some method require the metaSocket so throw an exception if metaSocketAvailable is false
    }
    
    public ConnectionManager( Socket socket ) throws IOException, NoSuchAlgorithmException
    {
        this.metaSocket = socket;
        metaSocketAvailable = true;
    }

    
    @Override
    public void run()
    {
    	BufferedReader in = null;
    	PrintWriter out = null;
        String localIpAddress = thisUser.getIP();
        String uuid = String.valueOf( thisUser.getUUID() );
        boolean listening = true;
        	
        try
		{
        	System.out.println( "Received a message" );
        	// must initialize in and out within a try catch in case of IOException
            in = new BufferedReader( new InputStreamReader( this.metaSocket.getInputStream() ) );
            out = new PrintWriter( this.metaSocket.getOutputStream() );
            String messageIn = "";
            String messageOut = "";
            String terminatingString = "RECEIVED";
            
			while ( listening )
			{

		        // move into method to clean up code?
		    	if ( messageIn != null && messageIn != "" )
		    	{
		    		// validate message
		    		System.out.println(messageIn);
		    		thisUser.addMessageToConversation( uuid , messageIn );
		    		
		    		messageOut = mallochiteMessageManager.messageRecievedReply ( uuid , localIpAddress );
		    		
		    		out.println( messageOut );
		    		out.flush();
		    	}
		    	
		    	if ( messageOut != null && messageOut.contains( terminatingString ) )
		    	{
		    		listening = false;
		    		in.close(); 
		    		this.metaSocket.close();
		    	}
		    	
		    	messageIn = in.readLine();
			 }
		}   
        
        catch( Exception e ) { e.printStackTrace(); }
        
        finally
        {
            out.close();
            this.interrupt();
            System.out.println( "Connection closed" );
        }
    }
    
    
    /*
     * Creates a socket with the intent of sending a "GREET" header with public key
     * Waits for response from socket and reacts accordingly 
     */
	public void sendMessage( User userToContact , String messageToSend ) throws IOException
	{
		Socket socket = new Socket ( userToContact.getIP() , userToContact.getPort() );
	    BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
	    PrintWriter out  = new PrintWriter( socket.getOutputStream() );
	    String messageIn = "";
    	
        try
        {
        	
        	String messageToSendFormated = mallochiteMessageManager.formatMessageToSend
        			( this.thisUser.getIP() , this.thisUser.getUUID() , messageToSend );
        	
    		out.println( messageToSendFormated );
    		out.flush();
    		
    		while ( true )
    		{
    			if ( messageIn != null && messageIn != "" )
    			{
    				System.out.println( messageIn );
    				
        			if ( messageIn.contains("RECEIVED") )
        			{
        				break;
        			}
    			}
    			else
    			{
    				messageIn = in.readLine();
    			}
    			
    		}
    		
        }
        catch ( Exception ex ) { throw ex; }
        
        finally
        {
            System.out.println( "closing socket for sending messages" );
    		socket.close();
            in.close();
            out.close();
        }
	}
	
	
    public Socket getMetaSocket()
	{
		return metaSocket;
	}

	public void setMetaSocket(Socket metaSocket) throws IOException
	{
		this.metaSocket = metaSocket;
		this.metaSocketAvailable = true;
	}

	public User getThisUser()
	{
		return thisUser;
	}

	public void setThisUser(User thisUser)
	{
		this.thisUser = thisUser;
	}

}

