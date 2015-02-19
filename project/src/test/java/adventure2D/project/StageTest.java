package adventure2D.project;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This test class does test the GObject class.
 * It actually only test the most simple getters so it isn't actually at all useful.
 */
public class StageTest {

	Stage stage;
	
	@Before
    public void setUp() {
		stage = new Stage();
	}
	
	@Test
	public void getWidth() {
		assertEquals(stage.width, stage.getWidth());
	}
	
	@Test
	public void getHeight() {
		assertEquals(stage.height, stage.getHeight());
	}
	
}
