package mallochite.models.nodes.classes;

import mallochite.models.nodes.abstractClasses.*;

public class SuperNode extends Node
{
	public SuperNode ( String hostIpAddress )
	{
		super ( hostIpAddress );
	}
	
    public SuperNode ( String hostIpAddress , int portToUse )
    {
    	super ( hostIpAddress , portToUse );
    }
}
