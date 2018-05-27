package game;

import model.GameResult;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class PlayImpl implements Play {

    private final Logger logger = Logger.getLogger(PlayImpl.class);

    private GameController gameController;
    private boolean isAsync;

    private ExecutorService executor;

    public PlayImpl(GameController gameController, boolean isAsync) {
        this.gameController = gameController;
        this.isAsync = isAsync;
        executor = determineExecutor();
    }

    @Override
    public List<GameResult> playGame(int nrOfGames) {
        logger.info("Welcome to Dragons of Mugloar!");

        GameProcessor gameProcessor = new GameProcessor(gameController, new AtomicInteger());

        List<Future<GameResult>> submitted = submitGames(nrOfGames, gameProcessor);
        List<GameResult> results = waitForGamesToComplete(submitted);

        executor.shutdown();
        return results;
    }

    private List<Future<GameResult>> submitGames(int nrOfGames, GameProcessor gameProcessor) {
        return IntStream.range(0, nrOfGames)
            .mapToObj(i -> gameProcessor)
            .map(executor::submit)
            .collect(toList());
    }

    private List<GameResult> waitForGamesToComplete(List<Future<GameResult>> games) {
        return games.stream()
                .map(this::waitForGameToComplete)
                .collect(toList());
    }

    private GameResult waitForGameToComplete(Future<GameResult> gameFuture) {
        try {
            return gameFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Thread interrupted", e);
        }
        return null;
    }

    private ExecutorService determineExecutor() {
        ExecutorService executor;
        if (isAsync) {
            logger.info("Playing multithreaded game");
            int availableCores = Runtime.getRuntime().availableProcessors();
            executor = Executors.newFixedThreadPool(availableCores * 2);
        } else {
            logger.info("Playing singlehreaded game");
            executor = Executors.newSingleThreadExecutor();
        }
        return executor;
    }

}
