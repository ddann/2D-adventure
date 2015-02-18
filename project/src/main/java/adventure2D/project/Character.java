package adventure2D.project;

/**
 * A class representing a character in the game's world.
 * All characters starts with acceleration and speed as zero.
 * 
 * @param x the character's starting x-coordinate
 * @param y the character's starting y-coordinate
 * @param radius the character's radius
 */
public abstract class Character extends GObject{
	
	double speedx = 0;
	double speedy = 0;
	
	double accelerationx = 0;
	double accelerationy = 0;
	
	public Character(int x, int y, int radius) {
		super(x, y, radius);
	}
	
}
