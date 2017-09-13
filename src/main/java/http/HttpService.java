package http;

import constants.Constants;
import generated.Report;
import model.Game;
import model.GameResult;
import model.GameSolution;

import java.io.IOException;

public class HttpService {

	private HttpClient httpClient;
	private SerializationService serializationService;

	public HttpService(HttpClient httpClient, SerializationService serializationService) {
		this.httpClient = httpClient;
		this.serializationService = serializationService;
	}

	public Game getGame() throws IOException {
		String response = httpClient.makeGetRequest(Constants.GAME_URL);
		return serializationService.getFromJson(response, Game.class);
	}

	public Report getWeather(int gameId) throws IOException {
		String resp = httpClient.makeGetRequest(Constants.getGameWeatherUrl(gameId));
		return serializationService.getObjectFromXml(resp, Report.class);
	}

	public GameResult solveGame(GameSolution solution, int gameId) throws IOException {
		String ans = httpClient.makePostRequest(Constants.getGameSolutionUrl(gameId), serializationService.getStringFromJson(solution));
		return serializationService.getFromJson(ans, GameResult.class);
	}
}
