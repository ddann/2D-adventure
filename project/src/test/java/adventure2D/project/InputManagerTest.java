package adventure2D.project;

import static org.junit.Assert.*;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

/**
 * A class for testing the input management logic, however how it supposed one to be able to test input's reading automatically?
 * I guess better to do it with running the game. So this test-class does practically nothing.
 */
public class InputManagerTest {
	
	InputManager inputManager;
	Player player;

	@Before
    public void setUp() {
		player = new Player(222,333);
		inputManager = new InputManager(player);
	}
	
	@Test
	public void StoredPalyerTest() {
		assertEquals(player,inputManager.player);
	}

	//How it supposed one to be able to test input's reading automatically?, I guess better to do it with running the game.
	//@Test
	//public void KeyPressedTest_jump() {
	//	inputManager.keyPressed(???));
	//}

	//@Test
	//public void KeyReleased_jump() {
	//	inputManager.keyPressed(???));
	//}

}
