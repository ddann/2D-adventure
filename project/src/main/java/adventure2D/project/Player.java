package adventure2D.project;

/**
 * A class representing the character the player controls.
 * 
 * @param x the player's character's starting x-coordinate
 * @param y the player's character's starting y-coordinate
 */
public class Player extends Character{
	
	/**
	 * Whether there is a jump to be done.
	 */
	boolean hasJumped = false;
	
	public Player(int x, int y) {
		super(x, y, 100);
	}
	
}
