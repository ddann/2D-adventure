package adventure2D.gui;

import java.util.List;

import java.awt.*;

import javax.swing.*;

import adventure2D.project.GameObject;
import adventure2D.project.Character;
import adventure2D.project.InputManager;
import adventure2D.project.Stage;

/**
 * The game's GUI in charge of making the GUI, actually: the 'HUD', drawing the game's world (via using another class), etc.
 * It directly creates a graphical output window which resolution is the same that the size (in Int) of the stage to be drawn (stage's width and height)
 * (If objects would have graphics attached to them, it would be possible, and the best option, to have only one GObject list...now apart for different colors)
 * 
 * @param characterList a list containing the characters in the stage.
 * @param objectList a list containing the objects in the stage.
 * @param stage the stage to be draw.
 * @param inputManager a input manager(extends key listener), that makes possible to listen to the inputs of the player.
 */
public class GUI extends JPanel implements Runnable{
	
	
	private static final long serialVersionUID = 1L; //???
	
	private List<Character> characterList;
	private List<GameObject> objectList;
	private Stage stage;
	
	private JFrame frame = new JFrame("'2D-adventure'");

	private InputManager inputmanager;
	
	
	public GUI(List<Character> characterList, List<GameObject> objectList, Stage stage, InputManager inputManager) {
		this.characterList = characterList;
		this.objectList = objectList;
		this.stage = stage;
		this.inputmanager = inputManager;
	}
	
	
	/**
	 * This method draws a frame of the game, that's said it draws the current situation of the game.
	 * Better said it actually makes this class override-method paintComponent to update the graphical output.
	 * @see this.paintComponent
	 */
	public void drawFrame() {
		super.repaint();
	}
	
	/**
	 * Makes a graphical representation to show the player that (s)he has win.
	 */
	public void victoryScreen() {
		frame.getContentPane().setLayout(new GridLayout(2,1));
		frame.remove(this);
		JButton infoMessage = new JButton("Congratulations for the victory on this extremely hard game!");
		frame.add(infoMessage);
		JButton button = new JButton("Play again this 'marvelous' game."
				+ "\n Press alt + F4 for doing it correctly...then run the program again.");
		frame.add(button);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Makes a graphical representation to show the player that (s)he has lost.
	 */
	public void looseScreen() {
		frame.getContentPane().setLayout(new GridLayout(2,1));
		frame.remove(this);
		JButton infoMessage = new JButton("You are...well...just a...LOOSER!");
		frame.add(infoMessage);
		JButton button = new JButton("Play again this 'marvelous' game... Or are you a chicken? (Guess you are it anyway.)"
				+ "\n Press alt + F4 for doing it correctly...then run the program again.");
		frame.add(button);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	
	/**
	 * It gives a message to the player that the game has encountered a error.
	 * NOTE: For now it is not in use, but it is made for 'expandability'.
	 * @param message a string containing the reason for the error and/or what it actually caused.
	 */
	public void problemDetected(String message) {
		frame.remove(this);
		JButton problemMessage = new JButton("This program has encountered an error: " + message
				+ "\n (And no starting the game again before doing something to this, OK?)");
		frame.add(problemMessage);
		
		frame.pack();
		frame.setVisible(true);
	}
	

	

	@Override
	public void run() {
		frame.setPreferredSize(new Dimension(this.stage.getWidth(), this.stage.getHeight()));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(this); //One would think it can't work, but it just does.
		frame.addKeyListener(inputmanager);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * A override method that is the method responsible for updating the graphical output.
	 * It draws all the game's objects as circles. Characters are drawn black and objects white.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		for (Character c: this.characterList) {
			g.fillOval((int) (c.getX() - c.getRadius()), (int) (c.getY() - c.getRadius()), 2*c.getRadius(), 2*c.getRadius());
		}
		
		g.setColor(Color.WHITE);
		for (GameObject go: this.objectList) {
			g.fillOval((int) (go.getX() - go.getRadius()), (int) (go.getY() - go.getRadius()), 2*go.getRadius(), 2*go.getRadius());
		}
	}

}
