package adventure2D.project;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A test class for testing the project's 'engine'-class.
 */
public class EngineTest {

	private Engine engine;
	
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
	
	
	@Test
	public void bossCordinatesAreRightX() {
		assertEquals(engine.stage.bossPositionX, engine.boss.x);
	}
	@Test
	public void bossCordinatesAreRightY() {
		assertEquals(engine.stage.bosspositionY, engine.boss.y);
	}
	
	@Test
	public void bossHealthIsRight() {
		assertEquals(engine.stage.bossHealth, engine.boss.Health);
	}
	
	
	
	//This test may not be definitive
	@Test
	public void characterListHasthecharacters() {
		assertEquals(engine.characterList.size(), 2);
		assertTrue(engine.characterList.contains(engine.boss) && engine.characterList.contains(engine.player));
	}
	
	@Test
	public void guiIsCreated() {
		assertTrue(engine.gui != null);
	}
	
	
	@Test
	public void loosingEndsLoopDirectly() {
		engine.hasLost = true;
		engine.player.x=1000000;
		engine.fullGameLoop();
		assertEquals(engine.player.x, 1000000);
	}
	@Test
	public void winningEndsLoopDirectly() {
		engine.hasWon = true;
		engine.player.x=1000000;
		engine.fullGameLoop();
		assertEquals(engine.player.x, 1000000);
	}
	
	
	//Naming could be improved... and maybe there is too many. (The last one's are "extra")
	@Test
	public void overStageTest1() {
		Character c = new Player(0,0);
		engine.characterList.add(c);
		c.x-= 20;
		engine.overStageTest();
		assertEquals(c.radius, c.x);
	}
	@Test
	public void overStageTest2() {
		Character c = new Player(0,0);
		engine.characterList.add(c);
		c.x+= 100000;
		engine.overStageTest();
		assertEquals(c.x, engine.stage.width - c.radius -1);
	}
	@Test
	public void overStageTest3() {
		Character c = new Player(0,0);
		engine.characterList.add(c);
		c.y-= 33;
		engine.overStageTest();
		assertEquals(c.radius, c.y);
	}
	@Test
	public void overStageTest4() {
		Character c = new Player(0,0);
		engine.characterList.add(c);
		c.y+= 100000;
		engine.overStageTest();
		assertEquals(c.y, engine.stage.height -c.radius  -1);
	}
	@Test
	public void overStageTest5() {
		Character c = new Player(0,0);
		engine.characterList.add(c);
		c.x+= c.radius+10;
		c.y+= c.radius+20;
		engine.overStageTest();
		assertEquals(c.x, c.radius+10);
		assertEquals(c.y, c.radius+20);
	}
	@Test
	public void overStageTest6() {
		Character c = new Player(0,0);
		engine.characterList.add(c);
		c.y+= engine.stage.height;
		engine.overStageTest();
		assertEquals(c.y, engine.stage.height - c.radius  -1);
	}
	@Test
	public void overStageTest7() {
		Character c = new Player(0,0);
		engine.characterList.add(c);
		c.x+= engine.stage.width;
		engine.overStageTest();
		assertEquals(c.x, engine.stage.width - c.radius  -1);
	}
	@Test
	public void overStageTest8() {
		Character c = new Player(0,0);
		engine.characterList.add(c);
		engine.overStageTest();
		assertEquals(c.x, c.radius);
		assertEquals(c.y, c.radius);
	}
	@Test
	public void overStageTest9() {
		Character c = new Player(0,0);
		engine.characterList.add(c);
		c.x-= c.radius-1;
		c.y-= c.radius-1;
		engine.overStageTest();
		assertEquals(c.x, c.radius);
		assertEquals(c.y, c.radius);
	}
	@Test
	public void overStageTest10() {
		Character c = new Player(0,0);
		engine.characterList.add(c);
		c.x+= c.radius+1;
		c.y+= c.radius+1;
		engine.overStageTest();
		assertEquals(c.x, c.radius+1);
		assertEquals(c.y, c.radius+1);
	}
	@Test
	public void overStageTest11() {
		Character c = new Player(0,0);
		engine.characterList.add(c);
		c.x+= engine.stage.width-200;
		c.y+= engine.stage.height-200;
		engine.overStageTest();
		assertEquals(c.x, engine.stage.width-200);
		assertEquals(c.y, engine.stage.height-200);
	}
	
	
	//Tests actually the Stage. (useful indeed)
	@Test
	public void charactersDoNotcollideAtTheBeginning() {
		assertTrue(!engine.detectCollision(engine.player, engine.boss));
	}
	
	//These tests are for now "hard-coded". They "know" the radius of the characters.
	@Test
	public void detectCollision1() {
		engine.boss.x = 200;
		engine.player.x = 200;
		engine.boss.y = 0;
		engine.player.y = 1000;
		assertTrue(!engine.detectCollision(engine.player, engine.boss));
	}
	
	@Test
	public void detectCollision2() {
		engine.boss.y = 200;
		engine.player.y = 200;
		engine.boss.x = 0;
		engine.player.x = 1000;
		assertTrue(!engine.detectCollision(engine.player, engine.boss));
	}
	
	@Test
	public void detectCollision3() {
		engine.boss.x = 0;
		engine.player.x = engine.stage.width-1;
		assertTrue(!engine.detectCollision(engine.player, engine.boss));
	}
	
	@Test
	public void detectCollision4() {
		engine.boss.y = 0;
		engine.player.y = engine.stage.height-1;
		assertTrue(!engine.detectCollision(engine.player, engine.boss));
	}
	
	@Test
	public void detectCollision5() {
		engine.boss.x = engine.stage.width-1;
		engine.player.x = 0;
		assertTrue(!engine.detectCollision(engine.player, engine.boss));
	}
	
	@Test
	public void detectCollision6() {
		engine.boss.y = engine.stage.height-1;
		engine.player.y = 0;
		assertTrue(!engine.detectCollision(engine.player, engine.boss));
	}
	
	@Test
	public void detectCollision7() {
		engine.boss.y = 200;
		engine.player.y = 200 + engine.boss.radius;
		engine.boss.x = 0;
		engine.player.x = 0 +engine.player.radius-1;
		assertTrue(engine.detectCollision(engine.player, engine.boss));
	}
	
	@Test
	public void detectCollision8() {
		engine.boss.radius=2;
		engine.player.radius=2;
		engine.boss.y = 0;
		engine.player.y = 4;
		engine.boss.x = 0;
		engine.player.x = 4;
		assertTrue(!engine.detectCollision(engine.player, engine.boss));
	}
	
	@Test
	public void detectCollision9() {
		engine.boss.radius=2;
		engine.player.radius=2;
		engine.boss.y = 0;
		engine.player.y = 1;
		engine.boss.x = 0;
		engine.player.x = 2;
		assertTrue(engine.detectCollision(engine.player, engine.boss));
	}
	
	
	@Test
	public void attack_BossHealth0() {
		engine.boss.Health =0;
		engine.doOneLoop();
		assertTrue(engine.hasWon);
	}
	
	@Test
	public void attack_BossHealthless0() {
		engine.boss.Health =-1;
		engine.doOneLoop();
		assertTrue(engine.hasWon);
	}
	
	@Test
	public void attack_BossHealthmore0() {
		engine.boss.Health =1;
		engine.doOneLoop();
		assertTrue(!engine.hasWon);
	}
	
	
	@Test
	public void movePlayer_Jump0() {
		engine.player.hasJumped =true;
		engine.player.y = engine.stage.height-1;
		engine.movePlayer();
		assertEquals(engine.player.hasJumped, false);
	}
	
	@Test
	public void movePlayer_Jump1() {
		engine.player.hasJumped =true;
		engine.player.y = engine.stage.height - engine.player.radius  -1;
		engine.movePlayer();
		assertEquals(engine.player.accelerationy, -22250 * engine.timeStep, 0.00001);
	}
	
	@Test
	public void movePlayer_playerDirection_both() {
		engine.player.toLeft = true;
		engine.player.toRight = true;
		engine.movePlayer();
		assertEquals(engine.player.accelerationx, 0, 0.00001);
	}
	
	@Test
	public void movePlayer_playerDirection_Left() {
		engine.player.toLeft = true;
		engine.movePlayer();
		assertEquals(engine.player.accelerationx, - 200 * engine.timeStep, 0.00001);
	}
	
	@Test
	public void movePlayer_playerDirection_Right() {
		engine.player.toRight = true;
		engine.movePlayer();
		assertEquals(engine.player.accelerationx, 200 * engine.timeStep, 0.00001);
	}
	
	@Test
	public void movePlayer_playerDirection_Left2() {
		engine.player.toLeft = true;
		engine.player.accelerationx =-1000;
		engine.movePlayer();
		assertEquals(engine.player.accelerationx, -1000, 0.00001);
	}
	
	@Test
	public void movePlayer_playerDirection_Right2() {
		engine.player.toRight = true;
		engine.player.accelerationx =1000;
		engine.movePlayer();
		assertEquals(engine.player.accelerationx, 1000, 0.00001);
	}
	
	@Test
	public void movePlayer_playerDirection_None() {
		engine.player.accelerationx =10;
		engine.movePlayer();
		assertEquals(engine.player.accelerationx, 10*0.8, 0.00001);
	}

	@Test
	public void movePlayer_Falling1() {
		engine.player.y = 555;
		engine.movePlayer();
		double expected = 30*engine.g * engine.timeStep;
		assertEquals(engine.player.accelerationy, expected, 0.00001);
	}
	
	@Test
	public void movePlayer_Falling2() {
		engine.player.y = engine.stage.height - engine.player.radius  -1;
		engine.movePlayer();
		assertEquals(engine.player.accelerationy, 0, 0.00001);
	}
	
	//The tests below actually tests the wallCollisionCheck-method. (and it's calling)
	@Test
	public void wallCollisionTest_1() {
		engine.player.y = engine.stage.height-1;
		engine.player.accelerationy =1;
		engine.wallCollisionCheck();
		assertEquals(engine.player.accelerationy, 0, 0.00001);
	}
	
	@Test
	public void wallCollisionTest_2() {
		engine.player.y = 0;
		engine.player.accelerationy =-1;
		engine.wallCollisionCheck();
		assertEquals(engine.player.accelerationy, 0, 0.00001);
	}
	
	@Test
	public void wallCollisionTest_3() {
		engine.player.x = engine.stage.width-1;
		engine.player.accelerationx =1;
		engine.wallCollisionCheck();
		assertEquals(engine.player.accelerationx, 0, 0.00001);
	}
	
	@Test
	public void wallCollisionTest_4() {
		engine.player.x = 0;
		engine.player.accelerationx =-1;
		engine.wallCollisionCheck();
		assertEquals(engine.player.accelerationx, 0, 0.00001);
	}
	
	@Test
	public void wallCollisionTest_5() {
		engine.player.y = engine.stage.height-1;
		engine.player.speedy =1;
		engine.wallCollisionCheck();
		assertEquals(engine.player.speedy, 0, 0.00001);
	}
	
	@Test
	public void mwallCollisionTest_6() {
		engine.player.y = 0;
		engine.player.speedy =-1;
		engine.wallCollisionCheck();
		assertEquals(engine.player.speedy, 0, 0.00001);
	}
	
	@Test
	public void wallCollisionTest_7() {
		engine.player.x = engine.stage.width-1;
		engine.player.speedx =1;
		engine.wallCollisionCheck();
		assertEquals(engine.player.speedx, 0, 0.00001);
	}
	
	@Test
	public void wallCollisionTest_8() {
		engine.player.x = 0;
		engine.player.speedx =-1;
		engine.wallCollisionCheck();
		assertEquals(engine.player.speedx, 0, 0.00001);
	}
	
	@Test
	public void doOneLoopTest_Collision() {
		engine.boss.y = 200;
		engine.player.y = 200;
		engine.boss.x = 200;
		engine.player.x = 200;
		engine.doOneLoop();
		assertEquals(engine.hasLost, true);
	}
	
	@Test
	public void doOneLoopTest_MoveByPhisics() {
		engine.player.y = engine.stage.height - engine.player.radius-1;
		engine.player.hasJumped = true;
		engine.player.accelerationx =1;
		engine.doOneLoop();
		assertEquals(engine.player.hasJumped, false);
		assertEquals(0.8, engine.player.accelerationx, 0.0001);
		assertEquals(0.8*engine.timeStep, engine.player.speedx, 0.0001); //The method movePlayer affects the testing of this.
		
	}
	
	@Test
	public void doOneLoopTest_OverStageTest() {
		engine.player.y = -1;
		engine.doOneLoop();
		assertEquals(engine.player.y, engine.player.radius);
	}
	
	@Test
	public void doOneLoopTest_attack_bossHealth() {
		engine.boss.Health =0;
		engine.doOneLoop();
		assertTrue(engine.hasWon);
	}
	
	@Test
	public void doOneLoopTest_attack_shoot() {
		engine.player.shoot = true;
		engine.doOneLoop();
		assertTrue(!engine.player.shoot);
		assertTrue(!engine.objectList.isEmpty());
	}
	
	@Test
	public void doOneLoopTest_moveByphisics() {
		engine.player.y = engine.stage.height-1;
		engine.player.x =100;
		engine.player.speedx=1;
		engine.doOneLoop();
		assertEquals(engine.player.x, 100 + 1/60, 0.00001);
	}
	
	
	
	@Test
	public void doOneLoopTest_wallCollisionCheck() {
		engine.player.y = engine.stage.height-1;
		engine.player.accelerationy =1;
		engine.doOneLoop();
		assertEquals(engine.player.accelerationy, 0, 0.00001);
	}
	
	@Test
	public void doOneLoopTest_moveObjects() {
		GameObject go = new GameObject(0, 0, 0, 10, 10, 0);
		engine.objectList.add(go);
		engine.doOneLoop();
		assertEquals(go.x, 1); //It rounds it to int...
	}
	
	@Test
	public void doOneLoopTest_Winning() {
		engine.boss.Health = 0;
		engine.doOneLoop();
		assertTrue(engine.hasWon);
	}
	
	@Test
	public void doOneLoopTest_loosing() {
		engine.player.x = engine.boss.x;
		engine.player.y = engine.boss.y;
		engine.doOneLoop();
		assertTrue(engine.hasLost);
	}
	
	
	@Test
	public void FullGameLoopTest_OneLoopCall() {
		engine.boss.y = 200;
		engine.player.y = 200;
		engine.boss.x = 200;
		engine.player.x = 200;
		engine.fullGameLoop();
		assertEquals(engine.hasLost, true);
	}
	
	@Test
	public void FullGameLoopTest_Waiting() {
		engine.boss.y = 200;
		engine.player.y = 200;
		engine.boss.x = 200;
		engine.player.x = 200;

		long timeAtStartingCall = System.nanoTime();
		engine.fullGameLoop();
		assertEquals(System.nanoTime() - timeAtStartingCall > 16666666, true);
	}
	
	
	
	@Test
	public void createsInputManager() {
		assertTrue(engine.inputManager != null);
	}
	
	
	@Test
	public void moveObjectsTestx() {
		GameObject go = new GameObject(0, 0, 0, 10, 10, 0);
		engine.objectList.add(go);
		engine.moveObjects();
		assertEquals(go.x, 1);
	}
	
	@Test
	public void moveObjectsTesty() {
		GameObject go = new GameObject(0, 0, 0, 10, 10, 0);
		engine.objectList.add(go);
		engine.moveObjects();
		assertEquals(go.y, 1);
	}
	
	@Test
	public void checkAttacksCollision_player() {
		engine.objectList.add(new GameObject(engine.player.x, engine.player.y, 100, 0, 0, 1));
		engine.checkAttacksCollision();
		assertTrue(engine.hasLost);
	}
	
	@Test
	public void checkAttacksCollision_boss() {
		engine.objectList.add(new GameObject(engine.boss.x, engine.boss.y, 100, 0, 0, 0));
		engine.boss.Health = 50;
		engine.checkAttacksCollision();
		assertEquals(engine.boss.Health, 0);
	}

}
