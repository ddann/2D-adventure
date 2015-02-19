package adventure2D.project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * This test class does test the GObject class by using the GameObject class (because GObject is abstract).
 * It actually only test the most simple getters so it isn't actually at all useful.
 */
public class GObjectTest {
	
	private GameObject gameobject;

	@Before
    public void setUp() {
		gameobject = new GameObject(1,2,3,0,0,2);
	}
	
	@Test
	public void getX() {
		assertEquals(1, gameobject.getX());
	}

	@Test
	public void getY() {
		assertEquals(2, gameobject.getY());
	}

	@Test
	public void getRadius() {
		assertEquals(3, gameobject.getRadius());
	}
	
}
