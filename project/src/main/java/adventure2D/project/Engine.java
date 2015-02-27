package adventure2D.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import adventure2D.gui.GUI;

/**
 * The game's engine class that is responsible of making the game run.
 * It has the game loop and calculates the games physics and makes all the game's calculations.
 * It is also in charge of crating the GUI-class and InputManager-class needed for running the game.
 */
public class Engine {
	
	InputManager inputManager;
	GUI gui;

	private File save = new File("save.save"); //Just a normal text file in the program's root folder, it's the game's save (nothing sencefull to save for now).
	
	Stage stage; //There is only one for now.
	Player player;
	Boss boss; //There is only one for now.
	
	/**
	 * A list containing the Characters of this game's instance.
	 */
	LinkedList<Character> characterList = new LinkedList<Character>();
	
	/**
	 * A list containing the GameObjects of this game's instance.
	 */
	LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	
	
	/**
	 * It would be the game's value for gravity, but the value isn't in use anymore/for now (directly).
	 */
	int g = 10;
	
	/**
	 * The game's 'time-step' in other words the time between frames/each iteration of a game loop.
	 */
	double timeStep = 1.0/60.0;
	
	
	/**
	 * Whether the player has lost or not. If has collided with boss or has been hit by a boss' shoot (unimplemented) the player has lost.
	 */
	boolean hasLost = false;
	/**
	 * Whether the player has won or not. If the boss is defeated the player has won.
	 */
	boolean hasWon = false;
	
	/**
	 * Wether there hasn't been found any problem while runnning or not.
	 */
	boolean allFine = true;
	
	
	//TODO: (May be needed for 'expanding' the program) Some way of storing inputs. (for now isn't needed)
	
	
	
	public Engine() {
		this.loadSave();
		inputManager = new InputManager(player);
		gui = new GUI(characterList, objectList, stage, inputManager);
	}
	
	
	/**
	 * This method is in charge of making the GUI to report an error to the player if one has happened.
	 * It stops the game for running so it doesn't crash.
	 * @param message a string containing the reason for the error and/or what it actually caused.
	 */
	protected void problemEncountered(String message) {
		this.allFine = false; //After this the game will stop looping
		//The below creates a GUI just for reporting the problem.
		gui = new GUI(characterList, objectList, new Stage(), new InputManager(new Player(111,222)));
		gui.problemDetected(message);
	}
	
	
	/**
	 * This method loads the save by 'loading' the save's info to the corresponding variables (or to ram if to be said).
	 * If there is no "save.save" file it is created.
	 */
	protected void loadSave() {
		if (!save.exists()) {
			try {
				save.createNewFile();
				PrintWriter writer = new PrintWriter(save);
				writer.println("1");
				writer.close();
			} catch (IOException e) {
				this.problemEncountered("Problem while creating the save file. Make sure that you have writing permissions in where this program resides.");
			}
		}
		
		Scanner scanner = null;
		try {
			scanner= new Scanner(save);
		} catch (Exception e) {
			this.problemEncountered("Unexpected problem: Cannot 'scan' for some reason the save. Check file integrity.");
		}
		
		if (scanner.hasNextLine()) {
			String level = scanner.nextLine();
			if (level == level) { //For now it always starts the same stage.
				this.stage = new Stage();
				player = new Player(stage.playerPositionX, stage.playerPositionY);
				boss = new Boss(stage.bossPositionX, stage.bosspositionY, stage.bossradius, stage.bossHealth);
				characterList.add(player);
				characterList.add(boss);
			}
			else {
				//There is only one stage but if there would be more this would be the way to load the stage where one is.
				//For this project for now it will always start the first stage (I won't change it, and as for "expandibility" I'll let this part like it is now)
			}
		}
		else {
			this.problemEncountered("Check for save's integrity. It exists but it is read to be empty.");
		}
		scanner.close();
	}
	
	/**
	 * A method in charge of 'saving' to as save-file.
	 * For now it is only called if the game is won, and writes just "won" to the save.
	 */
	protected void saveSave() {
		try {
			PrintWriter writer = new PrintWriter(save);
			writer.println("won");
			writer.close();
		} catch (FileNotFoundException e) {}
	}

	
	
	/**
	 * The class' "main method" that is the method that calls the method that makes the game run, call the GUI to draw and wait the needed time for steady FPS.
	 * It starts the game loop, then at ending the game it calls the method responsible of the game's session's ending. It may 'save' the game.
	 * Physics simulation is done with Euler integration and collision detections between objects with spherical collision detection.
	 */
	public void fullGameLoop() {
		if (allFine) {
			SwingUtilities.invokeLater(gui);
			while (!hasLost && !hasWon) {
				long timeAtStartingLoop = System.nanoTime();

				this.doOneLoop();
				gui.drawFrame();

				//Best way to wait the extra-time (eg. Thread.sleep() Inaccurate), works with any display's refresh-rate.
				//But keeps one thread completely busy, bad if using battery or if single-core(they are only old notebooks however);
				//There were other ways, but only for C++.
				while (System.nanoTime() - timeAtStartingLoop <= 16666666);
			}
			this.gameEnd();
		}
	}
	
	
	/**
	 * This method is just a method for making the code more readable as all 'things' hasn't to be calculated in just one method.
	 * In other word it just make calls to other methods that are in charge of the game's running.
	 * And two one-liner checks if the player has lost or win.
	 */
	protected void doOneLoop() {
		//TODO:Change things based on boss "AI". There is no AI at the moment thought. For now it will be left unimplemented (this is part of 'expandability')
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
	 * This method is in charge of notifying the player that the game has ended.
	 * It also calls save() if needed.
	 * @see this.save()
	 */
	protected void gameEnd() {
		//Only saves for now if won (there is only one stage...)
		if (this.hasWon) {
			this.saveSave();
			gui.victoryScreen();
		}
		else gui.looseScreen();
	}


	
	/**
	 * This method is in charge of crating the attacks' objects and making damage to the boss based on hits.
	 */
	protected void attack() {
		if (player.shoot && player.timeSinceNextShoot <1) {
			this.objectList.add(new GameObject(player.x, player.y, 30, 10,0,0)); //At this speed toInt-rounding has a great effect.
			player.timeSinceNextShoot =5; //5 * 1/60 s is the minimum time between shoots.
		}
		else player.timeSinceNextShoot-=1;
		player.shoot = false;
	    
	    //TODO Boss shooting...I have no idea how to do it by 'AI'. Unimplemented for now, to be part of 'expanding the game'.
	}
	
	/**
	 * This method checks if any 'hostile'(attack-object of "opposite side") object has touch a character and damages it based on which character/attack has happened.
	 * The player dies, if an object created by the boss and respectively boss' looses a static value of health.
	 */
	protected void checkAttacksCollision() {
		LinkedList<GameObject> removeList = new LinkedList<GameObject>();
		
		for (GameObject object: objectList) {
			if (object.type==1 && this.detectCollision(object, player)) this.hasLost = true;
			else if (object.type==0 && this.detectCollision(object, boss)) {
				boss.Health -= 50;
				//objectList.remove(object); For some reason "makes the game crash".
				removeList.add(object);
			}
		}
		
		for (GameObject object: removeList) {
			objectList.remove(object);
		}
	}
	
	/**
	 * A method that makes moves/"does things based in players inputs" that are only possible to do by the playable character.
	 * This method may "make the playable character to jump" and to change x movement based on Player.toLeft and Player.toRight.
	 * @see Player
	 */
	protected void movePlayer() {
		//All the below has just some random values, they may be changed if desired.
		
		if (player.y != stage.height - player.radius -1) {
			player.accelerationy += 30*g * timeStep; //Just drops faster and faster... TODO Maybe falling speed should be limited. 
		}
		else if (player.hasJumped) {
			//The jump's force is calculated directly as acceleration, inaccurate physics... 
			player.accelerationy -= 22250 * timeStep;
		}
		player.hasJumped = false;
		
		//Changes the x-axis acceleration, the more there is already to the same direction the less it changes.
		//Change calculated based on already had acceleration.
		//Do something if player is going to move left/right, if both (possible with keyboard) do like it would be no press.
		if (player.toLeft && !player.toRight) {
			if (player.accelerationx >-200) player.accelerationx =  player.accelerationx - (200 + player.accelerationx)*timeStep;
		}
		else if (player.toRight && !player.toLeft) {
			if (player.accelerationx <200) player.accelerationx =  player.accelerationx + (200 - player.accelerationx)*timeStep;
		}
		else {
			player.accelerationx *=  0.8;//If player is not pressing left or right, character stops moving left or right. In that case drop x-acceleration.
			
			//If the speed is low enough then stop character (and acceleration), done but check values.
			//Not in use for now as it stops already for rounding integer to zero (good enough).
			//if (player.speedx < 3*timeStep) {
			//	player.speedx=0;
			//	player.accelerationx=0;
			//}
			//The below it's most probably senseless and useless/bad. But if desired to prevent for some reason minimal y-axis movement they are left here commented.
			//if (player.speedy < 3*timeStep) {
			//	player.speedy=0;
			//	player.accelerationy=0;
			//}
		}
		
	}
	
	/**
	 * Moves all the characters based in physics laws. (some "hard-coded" calculations.)
	 * This is done with Euler integration. (Acceleration calculation is omitted here as they are done in other methods)
	 */
	protected void moveByPhisics() {
		for (Character c: characterList) {		
			c.x+= c.speedx * timeStep; c.y+= c.speedy * timeStep;
			c.speedx+= c.accelerationx * timeStep; c.speedy+= c.accelerationy * timeStep;
		}
	}
	
	/**
	 * Checks whether there has been a collision with a stages 'wall', actually the edge.
	 * For now it assumes that stages max y is the floor and other max/min values walls.
	 * In case of collision it resets to zero both the character's speed and acceleration.
	 * However acceleration/speed is kept to other directions than the one towards the collision with the wall.
	 * 
	 * Note: Characters doesn't bounce. (They loose all kinetic energy at a "collision" with the stage's edge)
	 * However there may happendd a 'strange bounce' if enought acceleration in the opposite direction of the wall.
	 */
	protected void wallCollisionCheck() {
		for (Character c: characterList) {
			if (c.y >= stage.height - c.radius -1) {
				if (c.accelerationy > 0) c.accelerationy =0;
				if (c.speedy > 0) c.speedy =0;
			}
			if (c.y <= c.radius) {
				if (c.accelerationy < 0) c.accelerationy =0;
				if (c.speedy < 0) c.speedy =0;
			}
			if (c.x >= stage.width - c.radius -1) {
				if (c.accelerationx > 0) c.accelerationx =0;
				if (c.speedx > 0) c.speedx =0;
			}
			if (c.x <= c.radius) {
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
		//TODO: (maybe) In case of the player going over stage it could be a loose. For now omitted.
		//The boss should't go over the stage, but in case it happens... (would it be actually a bug?)
		for (Character c: characterList) {
			if (c.x >= stage.width - c.radius) c.x = stage.width - c.radius  -1;
			else if (c.x < c.radius) c.x = c.radius;
			if (c.y >= stage.height - c.radius) c.y = stage.height - c.radius  -1;
			else if (c.y < c.radius) c.y = c.radius;
		}
	}
	
	/**
	 * This method makes the objects move based on their static speed.
	 */
	protected void moveObjects() {
		for (GameObject object: objectList) {
			object.x+= 10 * object.speedx * timeStep;
			object.y+= 10 * object.speedy * timeStep;
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
