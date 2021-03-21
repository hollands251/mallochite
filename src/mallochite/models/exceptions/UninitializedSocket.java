package mallochite.models.exceptions;

public class UninitializedSocket extends Exception
{	
    public UninitializedSocket(String errorMessage) {
        super(errorMessage);
    }
}
