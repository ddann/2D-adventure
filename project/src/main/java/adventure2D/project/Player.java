package adventure2D.project;

/**
 * A class representing the character the player controls.
 * 
 * @param x the player's character's starting x-coordinate
 * @param y the player's character's starting y-coordinate
 */
public class Player extends Character{
	
	/**
	 * Whether there has been given a command to jump that hasn't been already deal with.
	 */
	boolean hasJumped = false;
	/**
	 * Tells if there is to be add acceleration to the left/"desired by player to move left" (player pressing left)
	 */
	boolean toLeft = false;
	/**
	 * Tells if there is to be add acceleration to the right/"desired by player to move right" (player pressing right)
	 */
	boolean toRight = false;
	/**
	 * If there has been given a command to shoot that hasn't been already deal with.
	 */
	boolean shoot = false;
	
	
	/**
	 * This is the "time"/loops that is to be waited until the player can shoot again.
	 */
	int timeSinceNextShoot=0;
	
	
	public Player(int x, int y) {
		super(x, y, 80);
	}
	
}
