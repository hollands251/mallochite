package mallochite.models.nodes.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class RequestHandler extends Thread
{
    private Socket socket;
    
    public RequestHandler( Socket socket )
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println( "Received a connection" );

            // Get input and output streams
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            String line = in.readLine();
            while( line != null )
            {	
            	if ( !line.equals( "" ) )
            	{
            		System.out.println( line );
            		line = "";
            	}
            	else
            	{
            		line = in.readLine();
            	}
            }

            // Close our connection
            in.close();
            socket.close();
            this.interrupt();

            System.out.println( "Connection closed" );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
