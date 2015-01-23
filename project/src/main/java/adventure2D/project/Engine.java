package adventure2D.project;

public class Engine {

	Stage stage  = new Stage(); //This version is for testing methods.
	Player player = new Player(stage.playerPositionX, stage.playerPositionY);
	
	int g = 10;
	
	boolean hasLost = false;
	
	//For now it only checks if the character is over the stage, and fix the situation.
	public void move(Character character, int xMoveAmount, int yMoveAmount) {
		character.x = character.x + xMoveAmount;
		character.y = character.y + yMoveAmount;
		
		//Below corrections inn case of having gone over the stage.
		if (character.x >= stage.width) character.x = stage.width-1;
		else if (character.x < 0) character.x = 0;
		if (character.y >= stage.height) character.y = stage.height-1;
		else if (character.y < 0) character.y = 0;
	}
	
}
