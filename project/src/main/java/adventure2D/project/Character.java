package adventure2D.project;

/**
 * A class representing a character in the game's world.
 * 
 * @param x the character's starting x-coordinate
 * @param y the character's starting y-coordinate
 * @param radius the character's radius
 */
public abstract class Character {

	int x, y, radius;
	
	double speedx = 0;
	double speedy = 0;
	
	double accelerationx = 0;
	double accelerationy = 0;
	
	public Character(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	
	
}
