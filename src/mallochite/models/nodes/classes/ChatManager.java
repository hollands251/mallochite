package mallochite.models.nodes.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatManager extends Thread
{
	Socket socket;
	
	public ChatManager( Socket socket )
	{
		this.socket = socket;
	}
	
	@Override
	public void run()
	{
        try
		{
			PrintWriter out = new PrintWriter( socket.getOutputStream() );
			Scanner scanner = new Scanner( System.in );
			
			String outGoingMessage = "";
            
            while( !outGoingMessage.equals( "end" ) )
            {	
            	outGoingMessage = scanner.nextLine();
            	out.println( outGoingMessage );
            	out.flush();
            }
            
            out.close();
            socket.close();
            this.interrupt();
		} 
        catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally
        {
            this.interrupt();
        }
	}
}
