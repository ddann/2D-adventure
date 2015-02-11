package adventure2D.project;

/**
 * A class representing a object in the game's world.
 * Objects has x,y coordinates, radius and (only) speed to one direction (both x and y).
 * 
 * @param x the object's starting x-coordinate
 * @param y the object's starting y-coordinate
 * @param radius the object's radius
 * @param speedx the objects speed's x-component.
 * @param speedy the objects speed's y-component.
 */
public class GameObject {

	int x, y, radius;
	
	double speedx, speedy;
	
	
	public GameObject(int x, int y, int radius, int speedx, int speedy) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.speedx = speedx;
		this.speedy = speedy;
	}
	
}
