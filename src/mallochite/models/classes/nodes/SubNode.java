package mallochite.models.classes.nodes;

import java.security.NoSuchAlgorithmException;

import mallochite.models.abstractClasses.nodes.Node;
import mallochite.models.classes.User;

public class SubNode extends Node
{
	public SubNode ( User thisUser ) throws NoSuchAlgorithmException
	{
		super ( thisUser );
	}
}
