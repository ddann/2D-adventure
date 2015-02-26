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
	boolean toLeft = false;
	boolean toRight = false;
	boolean shoot = false;
	
	
	/**
	 * This is the "time"/loops that is to be waited until the player can shoot again.
	 */
	int timeSinceNextShoot=0;
	
	
	public Player(int x, int y) {
		super(x, y, 80);
	}
	
}
