package mallochite.models.nodes.classes;

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
    
    
    public ConnectionManager( Socket socket ) throws IOException, NoSuchAlgorithmException
    {
        this.metaSocket = socket;
    }

    
    @Override
    public void run()
    {
    	BufferedReader in = null;
    	PrintWriter out = null;
        String messageIn = "";
        String messageOut = "";
        String terminatingString = "RECEIVED";
        String localIpAddress = thisUser.ipAddress;
        String uuid = String.valueOf( thisUser.id );
        boolean listening = true;
        
    	// must initialize in and out within a try catch incase of IOException
    	try
    	{
             in = new BufferedReader( new InputStreamReader( this.metaSocket.getInputStream() ) );
             out = new PrintWriter( this.metaSocket.getOutputStream() );
    	}
    	catch ( IOException ex ) { ex.printStackTrace(); }
        
        // runs until message received or dropped 
        while ( listening )
        {
            try
            {
                System.out.println( "Received a message" );
                messageIn = in.readLine();

                // move into method to clean up code?
            	if ( messageIn != null && messageIn != "" )
            	{
            		// validate message
            		thisUser.addMessage( messageIn );
            		
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
            }
            
            catch( Exception e ) { e.printStackTrace(); }
            
            finally
            {
                out.close();
                this.interrupt();
                System.out.println( "Connection closed" );
            }
        }
    }
    
    
    /*
     * Creates a socket with the intent of sending a "GREET" header with public key
     * Waits for response from socket and reacts accordingly 
     */
	public void sendMessage( User userToContact , String messageToSend ) throws IOException
	{

		Socket socket = new Socket ( userToContact.ipAddress , userToContact.port );;
	    BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
	    PrintWriter out  = new PrintWriter( socket.getOutputStream() );
	    String messageIn = "";
        
        try
        {
        	String messageToSendFormated = mallochiteMessageManager.formatMessageToSend
        			( this.thisUser.id , this.thisUser.ipAddress , messageToSend );
        	
    		out.println( messageToSendFormated );
    		out.flush();
    		
            while ( messageIn != null )
            {
            	messageIn = in.readLine(); 
            	
            	if ( messageIn.contains( "RECEIVED" ) )
            	{
            		System.out.println( messageIn );
            		break;
            	}
            }
        }
        catch ( IOException ex ) { throw ex; }
        
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

