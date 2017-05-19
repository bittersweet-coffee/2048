package generic;

/**
 * TODO
 */
public class GameLogic {

	private static int score = 0;
	
	public static int getScore() {
		return score;
	}
	
	public static void setScore(int score) {
		GameLogic.score = score;
	}
	
	public static void doStuff() {
		System.out.println("Do Stuff");
	}
	
	
	
}
