package game;

import game.service.DragonCreatorService;
import game.service.KnightDragonFactory;
import model.Dragon;
import model.Knight;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
		Knight knight3 = KnightDragonFactory.getBlancedKnight();

		Dragon dragon1 = service.trainDragon(knight1, NORMAL_WEATHER);
		Dragon dragon2 = service.trainDragon(knight2, NORMAL_WEATHER);
		Dragon dragon3 = service.trainDragon(knight3, NORMAL_WEATHER);

		assertEquals(new Dragon(5, 10 , 4, 1), dragon1);
		assertEquals(new Dragon(0, 10, 3, 7), dragon2);
		assertEquals(KnightDragonFactory.getBalancedKnightsDragon(), dragon3);
	}

}