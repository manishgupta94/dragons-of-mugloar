package http;

import constants.Constants;
import generated.Report;
import model.Game;
import model.GameResult;
import model.GameSolution;

import java.io.IOException;

public class HttpService {

	private final HttpClient httpClient;
	private final ObjectSerializer objectSerializer;

	public HttpService(HttpClient httpClient, ObjectSerializer objectSerializer) {
		this.httpClient = httpClient;
		this.objectSerializer = objectSerializer;
	}

	public Game getGame() throws IOException {
		String response = httpClient.makeGetRequest(Constants.GAME_URL);
		return objectSerializer.getFromJson(response, Game.class);
	}

	public Report getWeather(int gameId) throws IOException {
		String response = httpClient.makeGetRequest(Constants.getGameWeatherUrl(gameId));
		return objectSerializer.getObjectFromXml(response, Report.class);
	}

	public GameResult solveGame(GameSolution solution, int gameId) throws IOException {
		String response = httpClient.makePostRequest(Constants.getGameSolutionUrl(gameId), objectSerializer.getJson(solution));
		return objectSerializer.getFromJson(response, GameResult.class);
	}

}
