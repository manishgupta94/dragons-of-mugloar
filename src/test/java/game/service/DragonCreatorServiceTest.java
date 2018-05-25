package game.service;

import model.Dragon;
import model.Knight;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static model.WeatherCode.DROUGHT;
import static model.WeatherCode.FOG;
import static model.WeatherCode.NORMAL_WEATHER;
import static model.WeatherCode.RAIN;
import static model.WeatherCode.STORM;

public class DragonCreatorServiceTest {

    private final DragonCreatorService service = new DragonCreatorService();

	@Test
	public void trainDragon_normalWeather() {
		Knight knight = new Knight(5, 8, 5, 2);
        Dragon dragon = service.trainDragon(knight, NORMAL_WEATHER);
		assertEquals(new Dragon(4, 10, 4, 2), dragon);
	}

    @Test
    public void trainDragon_drought() {
	    Dragon result = service.trainDragon(new Knight(), DROUGHT);
        assertEquals(KnightDragonFactory.droughtDragon(), result);
    }

    @Test
    public void trainDragon_rain() {
        Dragon result = service.trainDragon(new Knight(), RAIN);
        assertEquals(KnightDragonFactory.rainDragon(), result);
    }

    @Test
    public void trainDragon_fog() {
        Dragon result = service.trainDragon(new Knight(), FOG);
        assertEquals(KnightDragonFactory.droughtDragon(), result);
    }

    @Test
    public void trainDragon_normalWeather_balancedKnight() {
        Dragon dragon = service.trainDragon(KnightDragonFactory.balancedKnight(), NORMAL_WEATHER);
        assertEquals(KnightDragonFactory.balancedKnightsDragon(), dragon);
    }

    @Test
    public void trainDragon_normalWeather_hasTwoEmptyValues() {
        Knight knight = new Knight(10, 10, 0, 0);
        Dragon dragon = service.trainDragon(knight, NORMAL_WEATHER);
        assertEquals(new Dragon(12, 8, 0, 0), dragon);
    }

    @Test
    public void trainDragon_normalWeather_hasOneEmptyValue() {
        Knight knight = new Knight(0, 8, 4, 8);
        Dragon dragon = service.trainDragon(knight, NORMAL_WEATHER);
        assertEquals(new Dragon(0, 10, 3, 7), dragon);
    }

    @Test
    public void trainDragon_stormyWeather_nullDragon() {
        assertNull(service.trainDragon(new Knight(), STORM));
    }

}