package adventure2D.gui;

import java.util.List;

import adventure2D.project.GameObject;
import adventure2D.project.Character;
import adventure2D.project.Stage;

public class GUI {
	
	private List<Character> characterList;
	private List<GameObject> objectList;
	private Stage stage;
	
	public GUI(List<Character> characterlist, List<GameObject> objectList, Stage stage) {
		this.characterList = characterlist;
		this.stage = stage;
		this.objectList = objectList;
	}

}
