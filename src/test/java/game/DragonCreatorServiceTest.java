package game;

import model.Dragon;
import model.Knight;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DragonCreatorServiceTest {

	private final String NORMAL_WEATHER = "NMR";

	private DragonCreatorService service;
	private KnightDragonFactory factory;

	@Before
	public void setUp() {
		factory = new KnightDragonFactory();
		service = new DragonCreatorService(factory);
	}

	@Test
	public void trainDragon() throws Exception {
		Knight knight1 = new Knight(5, 8, 5, 2);
		Knight knight2 = new Knight(0, 8, 4, 8);
		Knight knight3 = factory.getBlancedKnight();
		Dragon dragon1 = service.trainDragon(knight1, NORMAL_WEATHER);
		Dragon dragon2 = service.trainDragon(knight2, NORMAL_WEATHER);
		Dragon dragon3 = service.trainDragon(knight3, NORMAL_WEATHER);

		assertEquals(new Dragon(5, 10 , 4, 1), dragon1);
		assertEquals(new Dragon(0, 10, 3, 7), dragon2);
		assertEquals(factory.getBalancedKnightsDragon(), dragon3);
	}

}