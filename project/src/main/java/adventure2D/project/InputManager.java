package adventure2D.project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class is in charge of handling the player's inputs and managing some changes based on the inputs.
 * (Changes are done directly on this class, because of being able to make the code simpler and this is the most clear way.)
 * 
 * @param player The playable character of the game.
 */
public class InputManager implements KeyListener{
	
	protected Player player;

	public InputManager(Player player) {
		this.player = player;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_D) {
			player.hasJumped = true;
		}
		if (key == KeyEvent.VK_RIGHT)  {
			player.toRight = true;
		}
		if (key == KeyEvent.VK_LEFT)  {
			player.toLeft = true;
		}
		if (key == KeyEvent.VK_A) {
			player.shoot = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT)  {
			player.toRight = false;
		}
		if (key == KeyEvent.VK_LEFT)  {
			player.toLeft = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
