package game.service;

import model.Dragon;
import model.Knight;
import model.WeatherCode;
import org.javatuples.Pair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DragonCreatorService {

	public Dragon trainDragon(Knight knight, WeatherCode weatherCode) {
		Dragon dragon = null;
		switch (weatherCode) {
            case NORMAL_WEATHER:
				dragon = trainDragonNormalWeather(knight);
				break;
			case DROUGHT:
				dragon = KnightDragonFactory.droughtDragon();
				break;
			case RAIN:
				dragon = KnightDragonFactory.rainDragon();
				break;
			case FOG:
				dragon = KnightDragonFactory.droughtDragon();
				break;
            case STORM:
                break;
			default:
				throw new IllegalArgumentException("Weather code not found: " + weatherCode);
		}
		return dragon;
	}

	private Dragon trainDragonNormalWeather(Knight knight) {
		if ((KnightDragonFactory.balancedKnight().equals(knight))) {
			return KnightDragonFactory.balancedKnightsDragon();
		}

        final Map<String, Integer> abilityIndexMapDragon = makeDragonAbilityKnightToDefeatKnight(knight);

        return new Dragon(abilityIndexMapDragon);
	}

    private Map<String, Integer> makeDragonAbilityKnightToDefeatKnight(Knight knight) {
        final Map<String, Integer> abilityIndexMapDragon = knight.getAbilityIndexMapDragon();
        final List<Pair<String, Integer>> collect = getSortedDragonAbilityMaping(knight);

        Pair<String, Integer> firstPair = collect.get(0);
        abilityIndexMapDragon.put(firstPair.getValue0(), firstPair.getValue1() + 2);

        if (collect.size() == 4) {
            Pair<String, Integer> secondPair = collect.get(1);
            abilityIndexMapDragon.put(secondPair.getValue0(), secondPair.getValue1() - 1);

            Pair<String, Integer> thirdPair = collect.get(2);
            abilityIndexMapDragon.put(thirdPair.getValue0(), thirdPair.getValue1() - 1);
        } else if (collect.size() == 3) {
            Pair<String, Integer> secondPair = collect.get(1);
            abilityIndexMapDragon.put(secondPair.getValue0(), secondPair.getValue1() - 1);

            Pair<String, Integer> thirdPair = collect.get(2);
            abilityIndexMapDragon.put(thirdPair.getValue0(), thirdPair.getValue1() - 1);
        } else if (collect.size() == 2) {
            Pair<String, Integer> secondPair = collect.get(1);
            abilityIndexMapDragon.put(secondPair.getValue0(), secondPair.getValue1() - 2);
        }

        return abilityIndexMapDragon;
    }

    private List<Pair<String, Integer>> getSortedDragonAbilityMaping(Knight knight) {
        return knight.getAbilityIndexPairsDragon()
            .stream()
            .filter(pair -> pair.getValue1() > 0)
            .sorted((x, y) -> y.getValue1().compareTo(x.getValue1()))
            .collect(Collectors.toList());
    }

}
