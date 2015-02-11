package adventure2D.project;

/**
 * A class representing the boss of a stage.
 * As for now there is only one stage in the game (if the game would be to be expanded it would be different) it is directly the game's boss.
 * 
 * @param x the boss' starting x-coordinate
 * @param y the boss' starting y-coordinate
 * @param h the boss' health
 */
public class Boss extends Character{
	
	/**
	 * The boss' health. Attacking decreases it and if <= 0, it is assumed to be defeated.
	 */
	int Health;
	
	//TODO Something that would store what the boss is going to do.

	public Boss(int x, int y, int r, int h) {
		super(x, y, r);
		Health = h;
	}
	
}
