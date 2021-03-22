package mallochite.models.nodes.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.HashMap;

public class Negotiator extends Thread
{
    private Socket metaSocket;				// responsible for establishing connections, not for chat
    private HashMap<String , Socket> chatSockets;
    private BufferedReader in;
    private PrintWriter out;
    
    
    public Negotiator( Socket socket ) throws IOException
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

            Socket socketForSendingData = new Socket ( remoteIpAddress , portNumberToUse );
            ResponseHandler responseHandler = new ResponseHandler( socketForSendingData );
            responseHandler.start();
            
            String receivedMessage = in.readLine();
            while( receivedMessage != null )
            {	
            	MMPManager ( receivedMessage );
            }

        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        finally
        {
        	System.out.println( "Connection closed" );
            this.in.close();
            this.out.close();
            this.metaSocket.close();
            this.interrupt();
        }
    }
    
    

}
