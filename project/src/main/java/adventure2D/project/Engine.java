package adventure2D.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The game's engine class that is responsible of making the game run.
 * It has the game loop and calculates the games physics and makes all the game's calculations.
 */
public class Engine {
	
	protected InputManager inputManager;

	private File save = new File("save.save"); //Just a normal text file in the program's root folder, it' s the game's save (nothing sencefull to save for now).
	
	protected Stage stage; //There is only one for now.
	protected Player player;
	protected Boss boss;
	
	protected LinkedList<Character> characterList = new LinkedList<Character>();
	
	protected int g = 10; //It is actually the acceleration down (there is no  air resistance)
	
	protected double timeStep = 1/60;
	
	//TODO: Some way of storing inputs.
	
	
	protected boolean hasLost = false;
	protected boolean hasWin = false;
	
	public Engine() {
		this.loadSave();
		inputManager = new InputManager(player);
	}
	
	protected void loadSave() {
		if (!save.exists()) {
			try {
				save.createNewFile();
				PrintWriter writer = new PrintWriter(save);
				writer.println("1");
				writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		Scanner scanner = null;
		try {
			scanner= new Scanner(save);
		} catch (Exception e) {
			//TODO something
        }
		
		String level = scanner.nextLine();
		if (level == level) { //For now it always starts the same stage.
			this.stage = new Stage();
			player = new Player(stage.playerPositionX, stage.playerPositionY);
			boss = new Boss(stage.bossPositionX, stage.bosspositionY, stage.bossradius, stage.bossHealth);
			characterList.add(player);
			characterList.add(boss);
		}
		else {
			//TODO tell the game has been completed. If want to play again from the beginning...to delete/move away the save?
			//TODO And close the program...or something
			//There is only one stage but if there would be more this would be the way to load the stage where one is.
		}
		scanner.close();
	}
	
	protected void saveSave() {
		try {
			PrintWriter writer = new PrintWriter(save);
			writer.println("won");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The class' "main method" that is the method that calls all the methods that makes the game run and partially makes itself game's calculations.
	 * First is initialize the rest of the game and start the game loop, then at ending the game it may "save".
	 */
	public void fullGameLoop() {
		
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
		//TODO:Something
		
		//Only saves for now if won (there is only one stage...)
		if (this.hasWin) this.saveSave();
	}
	
	protected void doOneLoop() {
		//TODO:Change things based on inputs and boss' "AI".
		//TODO Attacks before moving?
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
					c.accelerationy += g * timeStep; //Just drops faster and faster... TODO Maybe falling speed should be limited. 
				}
				else if (player.hasJumped) { //Only the player can jump.
					//The jump's force is calculated directly as acceleration, inaccurate physics... 
					player.accelerationy -= 50 * timeStep;
					player.hasJumped = false;
				}
				//Changes the x-axis acceleration, the more there is already to the same direction the less it changes.
				//Change calculated based on already had acceleration.
				if (player.toLeft && player.toRight) {} //Do something if player is going to move left/right, if both (possible with keyboard) do nothing.
				else if (player.toLeft) {
					if (player.accelerationx >-7) player.accelerationx =  player.accelerationx - (7 + player.accelerationx)*timeStep;
				}
				else if (player.toRight) {
					if (player.accelerationx <7) player.accelerationx =  player.accelerationx + (7 - player.accelerationx)*timeStep;
				}
				//TODO if player is not pressing left or right maybe the character should stop moving left or right.
			}
			
			
			//All the above changes the acceleration based on forces. (I think is better to update acceleration first)
			c.x+= c.speedx * timeStep; c.y+= c.speedy * timeStep;
			c.speedx+= c.accelerationx * timeStep; c.speedy+= c.accelerationy * timeStep;
		}
		this.wallCollisionCheck();
	}
	
	protected void wallCollisionCheck() {
		for (Character c: characterList) {
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
