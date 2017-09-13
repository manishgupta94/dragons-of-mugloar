package game;

import game.GameController;
import model.GameResult;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class GameProcessor implements Callable<GameResult> {

	private GameController gameController;
	private AtomicInteger atomicInteger;

	private final Logger logger = Logger.getLogger("Logger");

	public GameProcessor(GameController gameController, AtomicInteger atomicInteger) {
		this.gameController = gameController;
		this.atomicInteger = atomicInteger;
	}

	@Override
	public GameResult call() throws Exception {
		logger.info("Play nr: " + atomicInteger.incrementAndGet());

		GameResult result = null;
		try {
			result = gameController.game();
		} catch (IOException e) {
			logger.error("Faield to play game", e);
		}
		return result;
	}
}
