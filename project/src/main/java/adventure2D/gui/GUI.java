package adventure2D.gui;

import java.util.List;

import adventure2D.project.GameObject;
import adventure2D.project.Character;
import adventure2D.project.Stage;

/**
 * The game's GUI in charge of making the GUI, actually: the HUD, drawing the game's world, etc.
 * 
 * @param characterList a list containing the characters in the stage.
 * @param objectList a list containing the objects in the stage.
 * @param stage the stage to be draw.
 */
public class GUI {
	
	private List<Character> characterList;
	private List<GameObject> objectList;
	private Stage stage;
	
	public GUI(List<Character> characterList, List<GameObject> objectList, Stage stage) {
		this.characterList = characterList;
		this.stage = stage;
		this.objectList = objectList;
	}

}
