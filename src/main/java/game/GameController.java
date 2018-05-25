package game;

import game.service.DragonCreatorService;
import generated.Report;
import http.HttpService;
import model.*;
import org.apache.log4j.Logger;

import java.io.IOException;

public class GameController {

	private final Logger logger = Logger.getLogger(GameController.class);

	private HttpService httpService;
	private DragonCreatorService dragonCreatorService;

	public GameController(HttpService httpService, DragonCreatorService dragonCreatorService) {
		this.httpService = httpService;
		this.dragonCreatorService = dragonCreatorService;
	}

	public GameResult playGame() {
		GameResult result = null;
		GameContainer data = getGameData();

		if (data != null) {
			logger.info("Fetched playGame with id: " + data.getGameId());
			logger.info("Knight: " + data.getKnight());
			logger.info("Weather code: " + data.getWeatherCode());

			Dragon dragon = dragonCreatorService.trainDragon(data.getKnight(), data.getWeatherCode());
			logger.info("Trained dragon: " + dragon);
			result = solveGame(new GameSolution(dragon), data.getGameId());
			logger.info("Game " + data.getGameId() + " result: " + result);
		}

		return result;
	}

	private GameContainer getGameData() {
		GameContainer gameContainer = null;

		try {
			Game game = httpService.getGame();
			Report report = httpService.getWeather(game.getGameId());
			gameContainer = new GameContainer(game.getGameId(), game.getKnight(), WeatherCode.fromReport(report));
		} catch (IOException e) {
			logger.error("Failed to fetch game data or weather!");
		}
		return gameContainer;
	}

	private GameResult solveGame(GameSolution solution, int gameId) {
		GameResult gameResult = null;

		try {
			gameResult = httpService.solveGame(solution, gameId);
		} catch (IOException e) {
			logger.error("Failed to get game result");
		}
		return gameResult;
	}

}
