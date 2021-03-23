package mallochite.models.nodes.classes;

import java.io.IOException;
import java.util.HashMap;

public class MallochiteMessageManager
{
	private String deliminators = " "; // change to new line
	private final int GREETINGS_PARSE_COUNT = 1;
	private final int CONVERSE_PARSE_COUNT = 4;
	private final int AFFIRMATIVE_PARSE_COUNT = 4;
	
    public MallochiteMessageManager () {};
    
    
    /* 
     * After decrypting the metadata of the message is parsed, stored in a hashmap and returned to be
     * used by the connection Negotiator 
     */
    public HashMap<String , String> parseHeader( String message )
    {
    	
    	HashMap messageSegment;
    
    	if ( message.contains( "GREETINGS" ) ) // TODO must make regex to ensure start of line
    	{
    		messageSegment = this.parseDataFromHeader( message );
    	}
    	else
    	{
    		// decrypt message as GREETINGS is the only one allowed not to be encrypted
    		messageSegment = this.parseDataFromHeader( message );
    	}
    	
    	return messageSegment;
    }
    
    public HashMap<String , String> parseDataFromHeader( String message )
    {
    	String[] parsedMessage = message.split( this.deliminators );
    	HashMap<String , String > parsedMessageHashMap = new HashMap<String , String>();
    	
    	switch ( message )
    	{
    	case "GREET":
    		break;
    	case "CONVERSATE":
    		break;
    	case "AFFIRM":
    		break;
    	case "DEPART":
    		break;
    	default:
    		parsedMessageHashMap.put( "Method", "INVALID");
    	}
    	


    	
    	return helloMessageHashMap;
    }
    
    private HashMap<String , String> parseGreet( String message )
    {
    	
    }
    
    private HashMap<String , String> parseConverse( String message )
    {
    	HashMap<String , String> parsedConverse = new HashMap<String , String>();
    	
    	if ( message.length >= CONVERSE_PARSE_COUNT )
    	{
    		parsedMessageHashMap.put( "Method", parsedMessage[0] );
    		parsedMessageHashMap.put( "UUID", parsedMessage[1] );
    		parsedMessageHashMap.put( "IPv4", parsedMessage[2] );
    		parsedMessageHashMap.put( "Port", parsedMessage[3] );
    	}
    	else
    	{
    		parsedMessageHashMap.put( "Method", "INVALID");
    	}
    	
    	return 
    		
    }
    
    private HashMap<String , String> parseAffirm( Stringmessage )
    {
    	
    }
    
    private HashMap<String , String> parseDepart( Stringmessage )
    {
    	
    }
}
