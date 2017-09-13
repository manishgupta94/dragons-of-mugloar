package main;

import game.Play;
import game.PlayImpl;
import model.GameResult;
import org.apache.log4j.Logger;

import java.util.List;

public class Main {

	private static final Logger logger = Logger.getLogger("Logger");

	private static final Play play = new PlayImpl();

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();

		int nrOfGames = Integer.parseInt(args[0]);
		boolean isMultiThreaded = Boolean.parseBoolean(args[1]);

		List<GameResult> resultList = play.start(nrOfGames, isMultiThreaded);

		int games = resultList.size();
		long won = resultList.stream()
				.map(GameResult::isWon)
				.filter(b -> b)
				.count();

		logger.info("Time elapsed: " + (System.currentTimeMillis() - start)/1000 + " seconds");
		logger.info("Played games: " + games + ", Won: " + won + ", Lost: " + (games - won) + ", Win ratio " + ( won/games) * 100 + "%");
	}
}
