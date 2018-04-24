package game;

import game.service.DragonCreatorService;
import game.service.KnightDragonFactory;
import http.HttpClientImpl;
import http.HttpService;
import http.ObjectSerializer;
import model.GameResult;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayImpl implements Play {

	private final Logger logger = Logger.getLogger("Logger");

	private GameController gameController;

	private boolean isAsync;

	public PlayImpl(GameController gameController) {
		this.gameController = gameController;
	}

	public PlayImpl(GameController gameController, boolean isAsync) {
		this.gameController = gameController;
		this.isAsync = isAsync;
	}

	@Override
	public List<GameResult> start(int nrOfGames) {
		ExecutorService executor;

		logger.info("Welcome to Dragons of Mugloar.");
		if (isAsync) {
			logger.info("Playing multithreaded game");
			executor = Executors.newFixedThreadPool(5);
		} else {
			logger.info("Playing singlehreaded game");
			executor = Executors.newSingleThreadExecutor();
		}

		List<Future<GameResult>> futures = getFutures(nrOfGames, gameController, executor);
		List<GameResult>resultList = getGameResultsFromFutures(futures);

		executor.shutdown();
		return resultList;
	}

	private List<Future<GameResult>> getFutures(int nrOfGames, GameController gameController, ExecutorService executor) {
		List<Future<GameResult>> futures = new ArrayList<>();

		AtomicInteger atomicInteger = new AtomicInteger(0);
		for (int i = 0; i < nrOfGames; i++) {
			GameProcessor gameProcessor = new GameProcessor(gameController, atomicInteger);
			futures.add(executor.submit(gameProcessor));
		}
		return futures;
	}

	private List<GameResult> getGameResultsFromFutures(List<Future<GameResult>> futures) {
		List<GameResult>resultList = new ArrayList<>();
		for(Future<GameResult> future : futures) {
			try {
				GameResult result = future.get();
				resultList.add(result);
			} catch (InterruptedException | ExecutionException e) {
				logger.error("Thread interrupted", e);
			}
		}
		return resultList;
	}

}
