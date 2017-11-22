package http;

import constants.Constants;
import generated.Report;
import model.Game;
import model.GameResult;
import model.GameSolution;

import java.io.IOException;

public class HttpService {

	private HttpClient httpClient;
	private ObjectSerializer objectSerializer;

	public HttpService(HttpClient httpClient, ObjectSerializer objectSerializer) {
		this.httpClient = httpClient;
		this.objectSerializer = objectSerializer;
	}

	public Game getGame() throws IOException {
		String response = httpClient.makeGetRequest(Constants.GAME_URL);
		return objectSerializer.getFromJson(response, Game.class);
	}

	public Report getWeather(int gameId) throws IOException {
		String resp = httpClient.makeGetRequest(Constants.getGameWeatherUrl(gameId));
		return objectSerializer.getObjectFromXml(resp, Report.class);
	}

	public GameResult solveGame(GameSolution solution, int gameId) throws IOException {
		String ans = httpClient.makePostRequest(Constants.getGameSolutionUrl(gameId), objectSerializer.getJson(solution));
		return objectSerializer.getFromJson(ans, GameResult.class);
	}

}
