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
            while( receivedMessage != null )
            {	
            	this.messageSegment = mallochiteMessageManager.reactToMessage( receivedMessage );
            	
            	if ( messageSegment.get( "method" ).equals( "HELLO" ) )
            	{
            		this.openSocketForChat ( messageSegment.get( "IPv4" ) , messageSegment.get( "port" ) );
            	}
            }
            
            this.in.close();
            this.metaSocket.close();

        }
        
        catch( Exception e ) { e.printStackTrace(); }
        
        finally
        {
        	System.out.println( "Connection closed" );
            this.out.close();
            this.interrupt();
        }
    }

	private void openSocketForChat(String ipAddressToConnect, String portToUseString) throws UnknownHostException, IOException
	{
		int portToUse = Integer.parseInt( portToUseString );
		
        Socket socketForSendingData = new Socket ( ipAddressToConnect , portToUse );
        ChatManager chatManager = new ChatManager( socketForSendingData );
        chatManager.start();
	}
    
    

}
