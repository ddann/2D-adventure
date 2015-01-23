package adventure2D.project;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EngineTest {

	Engine engine;
	
	@Before
    public void setUp() {
		engine = new Engine();
	}
	
	@Test
	public void playesCordinatesAreRightX() {
		assertEquals(engine.stage.playerPositionX, engine.player.x);
	}
	@Test
	public void playesCordinatesAreRightY() {
		assertEquals(engine.stage.playerPositionY, engine.player.y);
	}
	
	//Naming could be improved...
	@Test
	public void move1() {
		Character c = new Character(0,0);
		engine.move(c, -20, 0);
		assertEquals(c.x, 0);
	}
	@Test
	public void move2() {
		Character c = new Character(0,0);
		engine.move(c, 100000, 0);
		assertEquals(c.x, engine.stage.width-1);
	}
	@Test
	public void move3() {
		Character c = new Character(0,0);
		engine.move(c, 0, -33);
		assertEquals(c.y, 0);
	}
	@Test
	public void move4() {
		Character c = new Character(0,0);
		engine.move(c, 0, 100000);
		assertEquals(c.y, engine.stage.height-1);
	}
	@Test
	public void move5() {
		Character c = new Character(0,0);
		engine.move(c, 10, 20);
		assertEquals(c.x, 10);
		assertEquals(c.y, 20);
	}
	@Test
	public void move6() {
		Character c = new Character(0,0);
		engine.move(c, 0, engine.stage.height);
		assertEquals(c.y, engine.stage.height-1);
	}
	@Test
	public void move7() {
		Character c = new Character(0,0);
		engine.move(c, engine.stage.width, 0);
		assertEquals(c.x, engine.stage.width-1);
	}
	@Test
	public void move8() {
		Character c = new Character(0,0);
		engine.move(c, -0, 0);
		assertEquals(c.x, 0);
		assertEquals(c.y, 0);
	}
	@Test
	public void move9() {
		Character c = new Character(0,0);
		engine.move(c, -1, -1);
		assertEquals(c.x, 0);
		assertEquals(c.y, 0);
	}
	@Test
	public void move10() {
		Character c = new Character(0,0);
		engine.move(c, 1, 1);
		assertEquals(c.x, 1);
		assertEquals(c.y, 1);
	}
	@Test
	public void move11() {
		Character c = new Character(0,0);
		engine.move(c, 0, 0);
		assertEquals(c.x, 0);
		assertEquals(c.y, 0);
	}
	

}
