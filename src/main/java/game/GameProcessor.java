package game;

import model.GameResult;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class GameProcessor implements Callable<GameResult> {

    private final Logger logger = Logger.getLogger(GameProcessor.class);

    private final GameController gameController;
    private final AtomicInteger atomicInteger;

    public GameProcessor(GameController gameController, AtomicInteger atomicInteger) {
        this.gameController = gameController;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public GameResult call() {
        logger.info("Game nr: " + atomicInteger.incrementAndGet());
        return gameController.playGame();
    }

}
