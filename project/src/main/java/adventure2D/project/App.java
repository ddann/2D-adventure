package adventure2D.project;

/**
 * The main class of the game.
 * It only creates the needed to start the game.
 */
public class App {
	
	public static void main( String[] args ) {
		System.out.println("hei"); //for testing
        Engine engine = new Engine();
        engine.fullGameLoop();
    }
}
