package game.service;

import model.Dragon;
import model.Knight;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DragonCreatorService {

	private KnightDragonFactory factory;

	public DragonCreatorService(KnightDragonFactory factory) {
		this.factory = factory;
	}

	public Dragon trainDragon(Knight knight, String weatherCode) {
		Dragon dragon = null;
		switch (weatherCode) {
			case "NMR":
				dragon = getDragonNormalWeather(knight);
				break;
			case "FUNDEFINEDG":
				dragon = factory.getDroughtDragon();
				break;
			case "HVA":
				dragon = factory.getRainDragon();
				break;
			case "T E":
				dragon = factory.getDroughtDragon();
				break;
			default:
				break;
		}
		return dragon;
	}

	private Dragon getDragonNormalWeather(Knight knight) {
		if (knight.equals(factory.getBlancedKnight())) {
			return factory.getBalancedKnightsDragon();
		}

		Map<String, Integer> abilityIndexMapDragon = knight.getAbilityIndexMapDragon();
		Map<String, Integer> sortedNewMap = getSortedMap(abilityIndexMapDragon);

		changeAbilityMapToDefeatKnight(abilityIndexMapDragon, sortedNewMap);

		return new Dragon(abilityIndexMapDragon.get("scaleThickness"),
				abilityIndexMapDragon.get("clawSharpness"),
				abilityIndexMapDragon.get("wingStrength"),
				abilityIndexMapDragon.get("fireBreath"));
	}

	private void changeAbilityMapToDefeatKnight(Map<String, Integer> abilityIndexMapDragon, Map<String, Integer> sortedMap) {
		int index = 1;
		int toDivide = 2;

		for (Map.Entry<String, Integer> e : sortedMap.entrySet()) {
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
		return map
					.entrySet()
					.stream()
					.filter(e -> e.getValue() > 0)
					.sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
					.collect(Collectors.
							toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
}
