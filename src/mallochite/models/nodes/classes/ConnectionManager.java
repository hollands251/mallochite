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
    private BufferedReader in;
    private PrintWriter out;
    
    
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
            MallochiteMessageManager mallochiteMessageManager = new MallochiteMessageManager();
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

	public void openSocketForChat(String ipAddressToConnect, String portToUseString) throws UnknownHostException, IOException
	{
		int portToUse = Integer.parseInt( portToUseString );
		
        Socket socketForSendingData = new Socket ( ipAddressToConnect , portToUse );
        ChatManager chatManager = new ChatManager( socketForSendingData );
        chatManager.start();
	}

}
