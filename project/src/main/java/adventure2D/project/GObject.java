package adventure2D.project;

/**
 * This class is the abstract super class for all the objects of the game (whether they are characters or other type of objects).
 * @param x the objects's starting x-coordinate
 * @param y the objects's starting y-coordinate
 * @param radius the object's radius
 */
public abstract class GObject {

	int x, y, radius;
	
	public GObject(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}
	
}
