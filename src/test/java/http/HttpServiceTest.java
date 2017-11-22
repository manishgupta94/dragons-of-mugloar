package http;

import generated.Report;
import model.Dragon;
import model.Game;
import model.GameResult;
import model.GameSolution;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HttpServiceTest {

	HttpService service;

	@Before
	public void setUp() {
		HttpClient client = new HttpClientImpl();
		ObjectSerializer objectSerializer = new ObjectSerializer();
		service = new HttpService(client, objectSerializer);
	}

	@Test
	public void getWeatherTest() throws Exception {
		Report weather = service.getWeather(483158);
		assertEquals("SRO", weather.getCode());
	}

	@Test
	public void getGameTest() throws Exception {
		Game game = service.getGame();
		assertNotNull(game);
	}

	@Test
	public void solveGameTest() throws Exception {
		Dragon dragon = new Dragon(5, 5, 5, 5);
		GameResult gameResult = service.solveGame(new GameSolution(dragon), 7546064);
		assertNotNull(gameResult);
	}

}