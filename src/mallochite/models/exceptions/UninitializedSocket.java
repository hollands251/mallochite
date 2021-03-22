package mallochite.models.exceptions;

public class UninitializedSocket extends Exception
{	
	static String errorMessage = "Either a socket or a server socket hasn't been initialized";
	
    public UninitializedSocket() 
    {
        super( errorMessage );
    }
}
