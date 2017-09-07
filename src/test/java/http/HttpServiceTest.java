package http;

import generated.Report;
import model.Dragon;
import model.Game;
import model.GameResult;
import model.GameSolution;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HttpServiceTest {

	HttpClient client = new HttpClientImpl();

	@Test
	public void getWeatherTest() throws Exception {
		HttpService service = new HttpService(client);
		Report weather = service.getWeather(483158);
		assertEquals("SRO", weather.getCode());
	}

	@Test
	public void getGameTest() throws Exception {
		HttpService service = new HttpService(client);
		Game game = service.getGame();
		assertNotNull(game);
	}

	@Test
	public void solveGameTest() throws Exception {
		HttpService service = new HttpService(client);
		Dragon dragon = new Dragon(5, 5, 5, 5);
		GameResult gameResult = service.solveGame(new GameSolution(dragon), 7546064);
		assertNotNull(gameResult);
	}

}