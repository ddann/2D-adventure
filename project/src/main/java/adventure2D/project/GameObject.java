package adventure2D.project;

/**
 * A class representing a object in the game's world.
 * Objects has x,y coordinates, radius and (only) speed to one direction (both x and y).
 * IMPORTANT NOTE: GObject is actually what the term game object means, this class represent objects like "bullet's" not characters alike.
 * (A good name would have been object...but already in use)
 * 
 * @param x the object's starting x-coordinate
 * @param y the object's starting y-coordinate
 * @param radius the object's radius
 * @param speedx the objects speed's x-component.
 * @param speedy the objects speed's y-component.
 * @param type the type of the object (player's, boss', neutral). See same-named variable's information for more detailed explanation.
 */
public class GameObject extends GObject{
	
	final double speedx, speedy;
	
	/**
	 * This type variable represents the type of the objects.
	 * 0 means object created by player's character, well the shot objects by the player.
	 * 1 like before but of boss'.
	 * 2 is neutral and is in no use for now (I am writing code that pretends to be for expanding the game in the future, for this project "being easily expandable")
	 */
	int type;
	
	
	public GameObject(int x, int y, int radius, int speedx, int speedy, int type) {
		super(x, y, radius);
		this.speedx = speedx;
		this.speedy = speedy;
		this.type = type;
	}
	
}
