package adventure2D.project;

import java.util.LinkedList;

public class Engine {

	private Stage stage  = new Stage(); //There is only one for now.
	private Player player = new Player(stage.playerPositionX, stage.playerPositionY);
	private Boss boss = new Boss(stage.bossPositionX, stage.bosspositionY);
	
	private LinkedList<Character> characterList = new LinkedList<Character>();
	
	private int g = 10; //It is actually the acceleration down (there is no  air resistance)
	
	private double timeStep = 1/60;
	
	//TODO: Some way of storing inputs.
	
	
	private boolean hasLost = false;
	
	
	/*
	 * The main method that calls the "sub-methods" to move the character.
	 * The player is always moved before the boss.
	 */
	public void fullGameLoop() {
		characterList.add(player);
		characterList.add(boss);
		
		while (!hasLost) {
			//TODO:Reads inputs. (another class)
			this.doOneLoop();
			//Should this class use GUI instead of the contrary? TODO: Plan the project's structure for what comes to the GUI.
			//TODO:Some way of waiting so that every loop is 1/60 s.
		}
		//TODO:Something.
	}
	
	private void doOneLoop() {
		//TODO:Change things based on inputs and boss' "AI".
		this.moveByPhisics();
		this.detectCollision();
		this.overStageTest();

	}
	
	/*
	 * Detects if there has been a collision, in such a case if it is player and boss, then the "player is killed".
	 * For now it is a simple circle implementation.
	 */
	private void detectCollision() {
		if (Math.abs(player.x-boss.x) < player.radius + boss.radius || Math.abs(player.y-boss.y) < player.radius + boss.radius ) {
			this.hasLost = true;
		}
	}
	
	/*
	 * Test whether the character has gone over the stage and does something to it.
	 * May have repeated code, but (at least for now) if changes are to be done it could be easier to this structure.
	 * (Now it only get the character back to the stage)
	 */
	private void overStageTest() {
		//(maybe)TODO: In case of the player going over stage it could be a loose.
		//The boss should't go over the stage, but in case it happens... (would it be actually a bug?)
		for (Character c: characterList) {
			if (c.x >= stage.width) c.x = stage.width-1;
			else if (c.x < 0) c.x = 0;
			if (c.y >= stage.height) c.y = stage.height-1;
			else if (c.y < 0) c.y = 0;
		}
	}
	
	/*
	 * Moves the character based in physics laws. (and some "hard-coded" calculations.)
	 * For now it assumes that stages max y is the floor.
	 */
	private void moveByPhisics() {
		//TODO:Something based on the inputs and boss AI.
		
		for (Character c: characterList) {
			if (c==player) {
				if (c.y != stage.height-1) {
					c.accelerationy += g * timeStep; 
				}
				else if (player.hasJumped) { //Only the player can jump.
					//TODO: Define the force (actually for ease directly the acceleration) of the jump and the calculations.
					player.hasJumped = false;
				}
			}
			//TODO: Change acceleration based on forces. (I think is better to update acceleration first)
			c.x+= c.speedx * timeStep; c.y+= c.speedy * timeStep;
			c.speedx+= c.accelerationx * timeStep; c.speedy+= c.accelerationy * timeStep;
		}
	}
	
}
