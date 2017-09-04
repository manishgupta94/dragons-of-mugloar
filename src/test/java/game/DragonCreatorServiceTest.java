package game;

import model.Knight;
import org.junit.Before;
import org.junit.Test;

public class DragonCreatorServiceTest {

	private final String NORMAL_WEATHER = "NMR";

	private DragonCreatorService service;

	@Before
	public void setUp() {
		service = new DragonCreatorService();
	}

	@Test
	public void trainDragon() throws Exception {
		Knight knight1 = new Knight(5, 8, 5, 2);
		Knight knight2 = new Knight(0, 8, 4, 8);
		Knight knight3 = new Knight(5, 5, 5, 5);
		service.trainDragon(knight1, NORMAL_WEATHER);
		service.trainDragon(knight2, NORMAL_WEATHER);
		service.trainDragon(knight3, NORMAL_WEATHER);
	}

}