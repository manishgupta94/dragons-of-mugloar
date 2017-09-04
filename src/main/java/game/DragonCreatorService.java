package game;

import com.sun.org.apache.bcel.internal.generic.NEW;
import model.Dragon;
import model.Knight;

import java.util.Map;

public class DragonCreatorService {

	public DragonCreatorService() {}

	Dragon trainDragon(Knight knight, String weatherCode) {
		Dragon dragon = null;
		switch (weatherCode) {
			case "NMR":
				dragon = getDragonNormalWeather(knight);
				break;
			case "FUNDEFINEDG":
				dragon = getDroughtDragon();
				break;
			case "HVA":
				dragon = getRainDragon();
				break;
			case "T E":
				dragon = getDroughtDragon();
				break;
			default:
				break;
		}
		return dragon;
	}

	private Dragon getDroughtDragon() {
		return new Dragon(5, 5, 5, 5);
	}

	private Dragon getRainDragon() {
		return new Dragon(5, 10, 5, 0);
	}

	private Dragon getDragonNormalWeather(Knight knight) {
		if (knight.equals(getBlancedKnight())) {
			return getBalancedKnightsDragon();
		}
		Map<String, Integer> abilityIndexMapDragon = knight.getAbilityIndexMapDragon();

		NameAbilityHelper maximum = getMaximumAbility(abilityIndexMapDragon);
		NameAbilityHelper smallest = getMinumumNonZeroAbility(abilityIndexMapDragon);
		NameAbilityHelper secondSmallest = getSecondSmallesAbility(abilityIndexMapDragon, smallest, maximum);

		abilityIndexMapDragon.put(smallest.getAbility(), smallest.getValue() - 1);
		abilityIndexMapDragon.put(maximum.getAbility(), maximum.getValue() + 2);
		abilityIndexMapDragon.put(secondSmallest.getAbility(), secondSmallest.getValue() - 1);

		return new Dragon(abilityIndexMapDragon.get("scaleThickness"),
				abilityIndexMapDragon.get("clawSharpness"),
				abilityIndexMapDragon.get("wingStrength"),
				abilityIndexMapDragon.get("fireBreath"));
	}

	private Knight getBlancedKnight() {
		return new Knight(5, 5, 5, 5);
	}

	private Dragon getBalancedKnightsDragon() {
		return new Dragon(7, 5, 4, 4);
	}

	private NameAbilityHelper getMaximumAbility(Map<String, Integer> abilityIndexMapDragon) {
		int max = 0;
		String field = "";
		for (Map.Entry entry : abilityIndexMapDragon.entrySet()) {
			int currentVal = (int) entry.getValue();
			if (currentVal > max) {
				max = currentVal;
				field = (String) entry.getKey();
			}
		}
		return new NameAbilityHelper(field, max);
	}

	private NameAbilityHelper getMinumumNonZeroAbility(Map<String, Integer> abilityIndexMapDragon) {
		int value = Integer.MAX_VALUE;
		String filed = "";

		for (Map.Entry entry : abilityIndexMapDragon.entrySet()) {
			int currentVal = (int) entry.getValue();
			if (currentVal > 0) {
				if (currentVal < value) {
					value = currentVal;
					filed = (String) entry.getKey();
				}
			}
		}
		return new NameAbilityHelper(filed, value);
	}

	private NameAbilityHelper getSecondSmallesAbility(Map<String, Integer> abilityIndexMapDragon, NameAbilityHelper smallest,
	NameAbilityHelper biggest) {
		int secondSmallestValue = Integer.MAX_VALUE;
		String secondSmallestField = "";

		for (Map.Entry entry : abilityIndexMapDragon.entrySet()) {
			int currentVal = (int) entry.getValue();
			String currentStr = (String) entry.getKey();

			if (IsNotMaximumNorMinimumAlready(smallest, biggest, currentVal, currentStr)) {
				if (currentVal < secondSmallestValue) {
					secondSmallestValue = currentVal;
					secondSmallestField = currentStr;
				}
			}

		}

		return new NameAbilityHelper(secondSmallestField, secondSmallestValue);
	}

	private boolean IsNotMaximumNorMinimumAlready(NameAbilityHelper smallest, NameAbilityHelper biggest, int currentVal, String currentStr) {
		return currentVal >= smallest.getValue() && !currentStr.equals(smallest.getAbility()) && !currentStr.equals(biggest.getAbility());
	}
}
