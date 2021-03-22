package mallochite.models.nodes.classes;

import java.io.IOException;

public class MallochiteMessageManager
{
    //public MallochiteMessageManager () {};
    
    
    public String reactToMessage ( String message )
    {
    
    	if ( message.equals( "ENCRYPTED" ) )
    	{
    		//message = encryptionManager.decrypt()
    	}
    	
    	switch ( message )
    	{
    		case "GREETINGS":
        		//String pubKey = keyManager.getPubKey();
        		message = "publicKey";
    			break;
    			
    		case "HELLO":
    			message = this.parseHelloHeader( message );
    			break;
    			
    		default:
    			break;
    	}
    	
    	return message;
    }
    
    public String parseHelloHeader( String message )
    {
    	return "hello parsed";
    }
}
