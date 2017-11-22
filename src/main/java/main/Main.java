package main;

import game.GameController;
import game.Play;
import game.PlayImpl;
import game.service.DragonCreatorService;
import game.service.KnightDragonFactory;
import http.HttpClientImpl;
import http.HttpService;
import http.ObjectSerializer;
import model.GameResult;
import org.apache.log4j.Logger;

import java.util.List;

public class Main {

	private static final Logger logger = Logger.getLogger("Logger");

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();

		int nrOfGames = Integer.parseInt(args[0]);
		boolean isMultiThreaded = Boolean.parseBoolean(args[1]);

		Play play = new PlayImpl(constructGameController(), isMultiThreaded);

		List<GameResult> resultList = play.start(nrOfGames);

		int games = resultList.size();
		long won = resultList.stream()
				.map(GameResult::isWon)
				.filter(b -> b)
				.count();

		logger.info("Time elapsed: " + (System.currentTimeMillis() - start)/1000 + " seconds");
		logger.info("Played games: " + games + ", Won: " + won + ", Lost: " + (games - won) + ", Win ratio " + ( won/games) * 100 + "%");
	}

	private static GameController constructGameController() {
		HttpService httpService = new HttpService(new HttpClientImpl(), new ObjectSerializer());
		DragonCreatorService dragonCreatorService = new DragonCreatorService(new KnightDragonFactory());
		return new GameController(httpService, dragonCreatorService);
	}

}
