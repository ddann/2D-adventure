package adventure2D.project;

public class Character {

	int x, y, radius;
	
	double speedx, speedy; //Actually a v-vector.
	double accelerationx, accelerationy; //Actually a a-vector.
	
	public Character(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	
	
}
