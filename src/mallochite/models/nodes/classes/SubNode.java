package mallochite.models.nodes.classes;

import mallochite.models.nodes.abstractClasses.*;

public class SubNode extends Node
{
	public SubNode ( String hostIpAddress )
	{
		super ( hostIpAddress );
	}
	
    public SubNode ( String hostIpAddress , int portToUse )
    {
    	super ( hostIpAddress , portToUse );
    }
}
