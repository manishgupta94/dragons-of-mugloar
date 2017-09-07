package game;

import generated.Report;
import http.HttpService;
import model.*;
import org.apache.log4j.Logger;

import java.io.IOException;

public class GameController {

	private HttpService httpService;
	private DragonCreatorService dragonCreatorService;
	private Logger logger;

	public GameController(HttpService httpService, DragonCreatorService dragonCreatorService, Logger logger) {
		this.httpService = httpService;
		this.dragonCreatorService = dragonCreatorService;
		this.logger = logger;
	}

	public GameResult game() throws IOException {
		GameResult result = null;
		GameContainer data = getGameData();

		if (data != null) {
			logger.info("Fetched game with id: " + data.getGameId());
			logger.info("Knight: " + data.getKnight());
			logger.info("Weather code: " + data.getWeatherCode());

			Dragon dragon = dragonCreatorService.trainDragon(data.getKnight(), data.getWeatherCode());
			logger.info("Trained dragon: " + dragon);
			result = getGameResult(new GameSolution(dragon), data.getGameId());
			logger.info("Game result: " + result);
		}

		return result;
	}

	private GameContainer getGameData() {
		GameContainer gameContainer = null;

		try {
			Game game = httpService.getGame();
			Report report = httpService.getWeather(game.getGameId());
			gameContainer = new GameContainer(game.getGameId(), game.getKnight(), report.getCode());
		} catch (IOException e) {
			logger.error("Failed to fetch game data or weather!");
		}
		return gameContainer;
	}

	private GameResult getGameResult(GameSolution solution, int gameId) {
		GameResult gameResult = null;

		try {
			gameResult = httpService.solveGame(solution, gameId);
		} catch (IOException e) {
			logger.error("Failed to get game result");
		}
		return gameResult;
	}
}
