package game.service;

import model.Dragon;
import model.Knight;

public class KnightDragonFactory {

	public static Dragon getDroughtDragon() {
		return new Dragon(5, 5, 5, 5);
	}

	public static Dragon getRainDragon() {
		return new Dragon(5, 10, 5, 0);
	}

	public static Knight getBlancedKnight() {
		return new Knight(5, 5, 5, 5);
	}

	public static Dragon getBalancedKnightsDragon() {
		return new Dragon(7, 5, 4, 4);
	}

}
