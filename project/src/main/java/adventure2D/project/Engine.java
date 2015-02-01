package adventure2D.project;

import java.util.LinkedList;

public class Engine {

	protected Stage stage  = new Stage(); //There is only one for now.
	protected Player player = new Player(stage.playerPositionX, stage.playerPositionY);
	protected Boss boss = new Boss(stage.bossPositionX, stage.bosspositionY, stage.bossHealth);
	
	protected LinkedList<Character> characterList = new LinkedList<Character>();
	
	protected int g = 10; //It is actually the acceleration down (there is no  air resistance)
	
	protected double timeStep = 1/60;
	
	//TODO: Some way of storing inputs.
	
	
	protected boolean hasLost = false;
	protected boolean hasWin = false;
	
	
	/*
	 * The main method that calls the "sub-methods" to move the character.
	 * The player is always moved before the boss.
	 */
	public void fullGameLoop() {
		characterList.add(player);
		characterList.add(boss);
		
		while (!hasLost && !hasWin) {
			long timeAtStartingLoop = System.nanoTime();
			//TODO:Reads inputs. (another class)
			this.doOneLoop();
			//Should this class use GUI instead of the contrary? TODO: Plan the project's structure for what comes to the GUI.
			
			while( System.nanoTime()-timeAtStartingLoop <= 16666666 );
			//Best way to wait the extra-time (eg. Thread.sleep() Inaccurate), works with any display's refresh-rate.
			//But keeps one thread completely busy, bad if using battery or if single-core(they are only old notebooks however);
			//There were other ways, but only for C++.
		}
		//TODO:Something.
	}
	
	protected void doOneLoop() {
		//TODO:Change things based on inputs and boss' "AI".
		this.moveByPhisics();
		this.overStageTest();
		this.detectCollision();
	}
	
	/*
	 * Detects if there has been a collision, in such a case if it is player and boss, then the "player is killed".
	 * For now it is a simple circle implementation.
	 */
	protected void detectCollision() {
		if (Math.sqrt((player.x - boss.x)*(player.x - boss.x) + (player.y - boss.y)*(player.y - boss.y)) < player.radius + boss.radius) {
			this.hasLost = true;
		}
	}
	
	/*
	 * Test whether the character has gone over the stage and does something to it.
	 * May have repeated code, but (at least for now) if changes are to be done it could be easier to this structure.
	 * (Now it only get the character back to the stage)
	 */
	protected void overStageTest() {
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
	 * For now it assumes that stages max y is the floor and other max/min values walls.
	 */
	protected void moveByPhisics() {
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
			
			//Characters doesn't bounce. (They loose all kinetic energy at a "collision" with the stage's edge.)
			if (c.y >= stage.height-1) {
				if (c.accelerationy > 0) c.accelerationy =0;
				if (c.speedy > 0) c.speedy =0;
			}
			if (c.y <= 0) {
				if (c.accelerationy < 0) c.accelerationy =0;
				if (c.speedy < 0) c.speedy =0;
			}
			if (c.x >= stage.width-1) {
				if (c.accelerationx > 0) c.accelerationx =0;
				if (c.speedx > 0) c.speedx =0;
			}
			if (c.x <= 0) {
				if (c.accelerationx < 0) c.accelerationx =0;
				if (c.speedx < 0) c.speedx =0;
			}
		}
	}
	
}
