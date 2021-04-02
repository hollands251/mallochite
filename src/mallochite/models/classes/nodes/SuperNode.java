package mallochite.models.classes.nodes;

import java.security.NoSuchAlgorithmException;

import mallochite.models.abstractClasses.nodes.Node;
import mallochite.models.classes.User;

public class SuperNode extends Node
{
	public SuperNode ( User thisUser ) throws NoSuchAlgorithmException
	{
		super ( thisUser );
	}
}
