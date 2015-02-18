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
 */
public class GUI extends JPanel implements Runnable{
	
	/**
	 * 
	 */
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
	
	
	
	public void drawFrame() {
		super.repaint();
	}
	
	
	public void victoryScreen() {
		//TODO something that would tell the player about the victory.
	}
	
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
			g.fillOval(c.getX(), c.getY(), c.getRadius(), c.getRadius());
		}
		
		g.setColor(Color.WHITE);
		for (GameObject go: this.objectList) {
			g.fillOval(go.getX(), go.getY(), go.getRadius(), go.getRadius());
		}
	}

}
