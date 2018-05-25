package game;

import model.GameResult;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayImpl implements Play {

	private final Logger logger = Logger.getLogger(PlayImpl.class);

	private GameController gameController;
	private boolean isAsync;

	private ExecutorService executor;

	public PlayImpl(GameController gameController, boolean isAsync) {
		this.gameController = gameController;
		this.isAsync = isAsync;
        executor = getExecutorService();
	}

	@Override
	public List<GameResult> playGame(int nrOfGames) {
        logger.info("Welcome to Dragons of Mugloar!");

        List<Future<GameResult>> futures = getFutures(nrOfGames, gameController);
		List<GameResult>resultList = getGameResultsFromFutures(futures);

		executor.shutdown();
		return resultList;
	}

    private List<Future<GameResult>> getFutures(int nrOfGames, GameController gameController) {
        final GameProcessor gameProcessor = new GameProcessor(gameController, new AtomicInteger());

	    return IntStream.range(0, nrOfGames)
            .mapToObj(i -> gameProcessor)
            .map(executor::submit)
            .collect(Collectors.toList());
	}

	private List<GameResult> getGameResultsFromFutures(List<Future<GameResult>> futures) {
	    return futures.stream()
            .map(this::waitForGameToComplete)
            .collect(Collectors.toList());
	}

	private GameResult waitForGameToComplete(Future<GameResult> gameFuture) {
        try {
            return gameFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Thread interrupted", e);
        }
        return null;
    }

    private ExecutorService getExecutorService() {
        ExecutorService executor;
        if (isAsync) {
            logger.info("Playing multithreaded game");
            final int availableCores = Runtime.getRuntime().availableProcessors();
            executor = Executors.newFixedThreadPool(availableCores * 2);
        } else {
            logger.info("Playing singlehreaded game");
            executor = Executors.newSingleThreadExecutor();
        }
        return executor;
    }

}
