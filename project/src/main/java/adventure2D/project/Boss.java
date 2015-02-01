package adventure2D.project;

public class Boss extends Character{
	
	//Hard-coded by now the radius.
	
	int Health;

	public Boss(int x, int y, int h) {
		super(x, y, 300);
		Health = h;
	}
	
}
