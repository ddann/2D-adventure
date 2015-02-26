package adventure2D.gui;

import java.util.List;
import java.awt.*;

import javax.swing.*;

import adventure2D.project.GameObject;
import adventure2D.project.Character;
import adventure2D.project.InputManager;
import adventure2D.project.Stage;

/**
 * The game's GUI in charge of making the GUI, actually: the HUD, drawing the game's world (via using another class), etc.
 * (It is badly done, but I didn't figure a better way to do this.)
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
	 */
	public void drawFrame() {
		super.repaint();
	}
	
	/**
	 * Makes a graphical representation to show the player that (s)he has win.
	 */
	public void victoryScreen() {
		//TODO something that would tell the player about the victory.
	}
	
	/**
	 * Makes a graphical representation to show the player that (s)he has lost.
	 */
	public void looseScreen() {
		//TODO something that would tell the player about the loosing.
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
