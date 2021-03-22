package mallochite.models.nodes.classes;

import java.io.IOException;
import java.util.HashMap;

public class MallochiteMessageManager
{
    public MallochiteMessageManager () {};
    
    
    /*
     * Takes message from node that is connecting. If it is encrypted, there will be a ENCRYPTED
     * string prepended to it to let us know to decrypt it.
     * 
     * After decrypting the metadata of the message is parsed, stored in a hashmap and returned to be
     * used by the connection Negotiator 
     */
    public HashMap<String , String> reactToMessage ( String message )
    {
    	
    	HashMap messageSegment = null;
    
    	if ( message.equals( "ENCRYPTED" ) )
    	{
    		//message = encryptionManager.decrypt()
    	}
    	
    	switch ( message )
    	{
    		case "GREETINGS":
        		//String pubKey = keyManager.getPubKey();
        		messageSegment.put( "publicKey" , "tempKey");
        		messageSegment.put( "body" , "" );
    			break;
    			
    		case "HELLO":
    			messageSegment = parseHelloHeader( message );
    			break;
    			
    		default:
    			break;
    	}
    	
    	return messageSegment;
    }
    
    public HashMap parseHelloHeader( String message )
    {
    	//deliminators = " "; // change to new line
    	//String parsedMessage = message
    	HashMap parsedHelloMessage = null;
    	
    	return parsedHelloMessage;
    }
}
