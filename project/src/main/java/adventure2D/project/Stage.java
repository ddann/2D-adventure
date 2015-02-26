package adventure2D.project;

/**
 * This class represent a stage.
 * As for now there is only one stage in the game (if the game would be to be expanded it would be different) it is directly the game's stage.
 */
public class  Stage {
	
	int width = 1920;
	int height = 1080;
	
	int playerPositionX = 100;
	int playerPositionY = 222;
	
	int bossPositionX = 1500;
	int bosspositionY =333;
	int bossradius = 200;
	int bossHealth = 1000;
	
	
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
