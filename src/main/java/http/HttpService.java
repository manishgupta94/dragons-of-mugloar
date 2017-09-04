package http;

import com.google.gson.Gson;
import constants.Constants;
import generated.Report;
import model.Game;
import model.GameResult;
import model.GameSolution;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;

public class HttpService {

	private HttpClient httpClient;

	public HttpService() {
		this.httpClient = new HttpClient();
	}

	public Game getGame() throws IOException {
		String response = httpClient.makeGetRequest(Constants.GAME_URL);
		return getGameFromJson(response);
	}

	public Report getWeather(int gameId) throws IOException {
		String resp = httpClient.makeGetRequest(Constants.getGameWeatherUrl(gameId));
		return getReportFromXml(resp);
	}

	public GameResult solveGame(GameSolution solution, int gameId) throws IOException {
		String ans = httpClient.makePostRequest(Constants.getGameSolutionUrl(gameId), getStringFromGameSoultion(solution));
		return getGameResultFromJson(ans);
	}

	private Report getReportFromXml(String xml) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Report.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(xml);
			return (Report) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			System.err.println("Failed to unmarshall weather");
		}
		return null;
	}

	private Game getGameFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, Game.class);
	}

	private GameResult getGameResultFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, GameResult.class);
	}

	private String getStringFromGameSoultion(GameSolution solution) {
		Gson gson = new Gson();
		return gson.toJson(solution);
	}
}
