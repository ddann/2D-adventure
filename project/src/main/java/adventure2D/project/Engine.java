package adventure2D.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

import adventure2D.gui.GUI;

/**
 * The game's engine class that is responsible of making the game run.
 * It has the game loop and calculates the games physics and makes all the game's calculations.
 * It is also in charge of crating the GUI-class and InputManager-class needed for running the game.
 */
public class Engine {
	
	protected InputManager inputManager;
	protected GUI gui;

	private File save = new File("save.save"); //Just a normal text file in the program's root folder, it' s the game's save (nothing sencefull to save for now).
	
	protected Stage stage; //There is only one for now.
	protected Player player;
	protected Boss boss;
	
	protected LinkedList<Character> characterList = new LinkedList<Character>();
	
	protected LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	
	protected int g = 10; //It is actually the acceleration down (there is no  air resistance...)
	
	protected double timeStep = 1/60;
	
	//TODO: Some way of storing inputs. (for now isn't needed)
	
	protected boolean hasLost = false;
	protected boolean hasWon = false;
	
	
	public Engine() {
		this.loadSave();
		inputManager = new InputManager(player);
		gui = new GUI(characterList, objectList, stage);
	}
	
	
	
	/**
	 * This method loads the save by 'loading' the save's info to the corresponding variables (or to ram if to be said).
	 */
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
			//For this project most likely it will always start the first stage (I won't change it, and as for "expandibility" I'll let this like it is now)
		}
		scanner.close();
	}
	
	/**
	 * A method in charge of saving to as save-file.
	 * For now it is only called if the game is won, and writes just "won" to the save.
	 */
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
	 * The class' "main method" that is the method that calls the method that makes the game run, call the GUI to draw and wait the needed time for steady FPS.
	 * It starts the game loop, then at ending the game it may "save".
	 */
	public void fullGameLoop() {
		
		while (!hasLost && !hasWon) {
			long timeAtStartingLoop = System.nanoTime();
			this.doOneLoop();
			//Should this class use GUI instead of the contrary? TODO: Plan the project's structure for what comes to the GUI.
			
			while( System.nanoTime()-timeAtStartingLoop <= 16666666 );
			//Best way to wait the extra-time (eg. Thread.sleep() Inaccurate), works with any display's refresh-rate.
			//But keeps one thread completely busy, bad if using battery or if single-core(they are only old notebooks however);
			//There were other ways, but only for C++.
		}
		//TODO:Something
		
		//Only saves for now if won (there is only one stage...)
		if (this.hasWon) this.saveSave();
	}
	
	
	/**
	 * This method is just a method for making the code more readable as all 'things' hasn't to be calculated in just one method.
	 * In other word it just make calls to other methods that are in charge of the game's running.
	 * And a one-liner check if the player has lost or win.
	 */
	protected void doOneLoop() {
		//TODO:Change things based on inputs and boss' "AI".
		this.attack();
		this.checkAttacksCollision();
		this.movePlayer();
		this.moveByPhisics();
		this.wallCollisionCheck();
		this.overStageTest();
		this.moveObjects();
		if (boss.Health <= 0) hasWon = true;
		if (this.detectCollision(player, boss)) this.hasLost = true;;
	}
	
	


	/**
	 * This method is in charge of crating the attacks' objects and making damage to the boss based on hits.
	 * It also check if the boss' health is 0 or less, and respectively saves the winning to the variable.
	 */
	protected void attack() {
		if (player.shoot && player.timeSinceNextShoot <1) {
			//TODO shooting
			player.timeSinceNextShoot =5; //5* 1/60s is the minimum time between shoots.
		}
	    player.timeSinceNextShoot-=1;
	    
	    //TODO Boss shooting...I have no idea how to do it by 'AI'.
	}
	
	/**
	 * This method checks if any 'hostile'(attack-object of "opposite side") object has touch a character and damages it based on which character/attack has happened.
	 * Note: for now player dies, if object created by boss and respectively boss' looses a static value of health.
	 */
	protected void checkAttacksCollision() {
		for (GameObject object: objectList) {
			if (object.type==1 && this.detectCollision(object, player)) this.hasLost = true;
			else if (object.type==0 && this.detectCollision(object, boss)) boss.Health -= 50;
		}
	}
	
	/**
	 * A protected method that makes moves/"does things based in players inputs" that are only possible to do by the playable character.
	 */
	protected void movePlayer() {
		if (player.y != stage.height-1) {
			player.accelerationy += g * timeStep; //Just drops faster and faster... TODO Maybe falling speed should be limited. 
		}
		else if (player.hasJumped) {
			//The jump's force is calculated directly as acceleration, inaccurate physics... 
			player.accelerationy -= 50 * timeStep;
		}
		player.hasJumped = false;
		
		//Changes the x-axis acceleration, the more there is already to the same direction the less it changes.
		//Change calculated based on already had acceleration.
		//Do something if player is going to move left/right, if both (possible with keyboard) do like it would be no press.
		if (player.toLeft && !player.toRight) {
			if (player.accelerationx >-7) player.accelerationx =  player.accelerationx - (7 + player.accelerationx)*timeStep;
		}
		else if (player.toRight && !player.toLeft) {
			if (player.accelerationx <7) player.accelerationx =  player.accelerationx + (7 - player.accelerationx)*timeStep;
		}
		else {
			player.accelerationx *=  0.8;//If player is not pressing left or right, character stops moving left or right. In that case drop x-speed.
			//TODO if the speed is low enough stop character (and acceleration), done but check values
			if (player.speedx < 3*timeStep) {
				player.speedx=0;
				player.accelerationx=0;
			}
			//TODO the below it's most probably senseless and useless/bad.
			//if (player.speedy < 3*timeStep) {
			//	player.speedy=0;
			//	player.accelerationy=0;
			//}
		}
		
	}
	
	/**
	 * Moves all the characters based in physics laws. (some "hard-coded" calculations.)
	 */
	protected void moveByPhisics() {
		//TODO:Something based on the boss AI.
		
		for (Character c: characterList) {		
			//All the above changes the acceleration based on forces. (I think is better to update acceleration first)
			c.x+= c.speedx * timeStep; c.y+= c.speedy * timeStep;
			c.speedx+= c.accelerationx * timeStep; c.speedy+= c.accelerationy * timeStep;
		}
	}
	
	/**
	 * Checks whether there has been a collision with a stages 'wall', actually the edge.
	 * For now it assumes that stages max y is the floor and other max/min values walls.
	 * In case of collision it resets to zero both the character's speed and acceleration.
	 */
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
	
	/**
	 * Test whether the character has gone over the stage and does something to it.
	 * May have repeated code, but (at least for now) if changes are to be done it could be easier to this structure.
	 * (For now it only get the character back to the stage)
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
	
	/**
	 * This method makes the objects move based on their static speed.
	 */
	protected void moveObjects() {
		for (GameObject object: objectList) {
			object.x+= object.speedx * timeStep;
			object.y+= object.speedy * timeStep;
		}
	}
	
	/**
	 * Detects if there has been a collision, in such a case if it is player and boss, then the "player is killed".
	 * For now it is a simple circle implementation.
	 * @param ob1 a GObject to test if collides with the other.
	 * @param ob2 a GObject to test if collides with the other.
	 */
	protected boolean detectCollision(GObject ob1, GObject ob2) {
		if (Math.sqrt((ob1.x - ob2.x)*(ob1.x - ob2.x) + (ob1.y - ob2.y)*(ob1.y - ob2.y)) < ob1.radius + ob2.radius) {
			return true;
		}
		return false;
	}
	
}
