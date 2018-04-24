package game.service;

import model.Dragon;
import model.Knight;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toMap;

public class DragonCreatorService {

	public Dragon trainDragon(Knight knight, String weatherCode) {
		Dragon dragon = null;
		switch (weatherCode) {
			case "NMR":
				dragon = trainDragonNormalWeather(knight);
				break;
			case "FUNDEFINEDG":
				dragon = KnightDragonFactory.getDroughtDragon();
				break;
			case "HVA":
				dragon = KnightDragonFactory.getRainDragon();
				break;
			case "T E":
				dragon = KnightDragonFactory.getDroughtDragon();
				break;
			default:
				break;
		}
		return dragon;
	}

	private Dragon trainDragonNormalWeather(Knight knight) {
		if (knight.equals(KnightDragonFactory.getBlancedKnight())) {
			return KnightDragonFactory.getBalancedKnightsDragon();
		}

		Map<String, Integer> abilityIndexMapDragon = knight.getAbilityIndexMapDragon();
		Map<String, Integer> sortedNewMap = getSortedMap(abilityIndexMapDragon);

		changeAbilityMapToDefeatKnight(abilityIndexMapDragon, sortedNewMap);

		return new Dragon(abilityIndexMapDragon);
	}

	private void changeAbilityMapToDefeatKnight(Map<String, Integer> abilityIndexMapDragon, Map<String, Integer> sortedMap) {
		int index = 1;
		int toDivide = 2;

		for (Entry<String, Integer> e : sortedMap.entrySet()) {
			int value = e.getValue();
			String ability = e.getKey();

			if (index == sortedMap.size()) {
				abilityIndexMapDragon.put(ability, value + 2);
				break;

			} else {
				if (toDivide > 0) {
					abilityIndexMapDragon.put(ability, value - 1);
					toDivide--;
				}
			}

			index++;
		}
	}

	private Map<String, Integer> getSortedMap(Map<String, Integer> map) {
		return map.entrySet()
				.stream()
				.filter(e -> e.getValue() > 0)
				.sorted(Comparator.comparing(Entry::getValue))
				.collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
