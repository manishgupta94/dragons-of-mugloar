package model;

import java.util.Map;

public class Dragon {

	private int scaleThickness;
	private int clawSharpness;
	private int wingStrength;
	private int fireBreath;

	public Dragon(int scaleThickness, int clawSharpness, int wingStrength, int fireBreath) {
		this.scaleThickness = scaleThickness;
		this.clawSharpness = clawSharpness;
		this.wingStrength = wingStrength;
		this.fireBreath = fireBreath;
		assertAbilities();
	}

    public Dragon(Map<String, Integer> abilityIndexMapKnight) {
        this.scaleThickness = abilityIndexMapKnight.get("scaleThickness");
        this.clawSharpness = abilityIndexMapKnight.get("clawSharpness");
        this.wingStrength = abilityIndexMapKnight.get("wingStrength");
        this.fireBreath = abilityIndexMapKnight.get("fireBreath");
		assertAbilities();
    }

	public void setScaleThickness(int scaleThickness) {
		this.scaleThickness = scaleThickness;
	}

	public void setClawSharpness(int clawSharpness) {
		this.clawSharpness = clawSharpness;
	}

	public void setWingStrength(int wingStrength) {
		this.wingStrength = wingStrength;
	}

	public void setFireBreath(int fireBreath) {
		this.fireBreath = fireBreath;
	}

	@Override
	public String toString() {
		return "Dragon{" +
				"scaleThickness=" + scaleThickness +
				", clawSharpness=" + clawSharpness +
				", wingStrength=" + wingStrength +
				", fireBreath=" + fireBreath +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || this.getClass() != o.getClass()) return false;

		Dragon dragon = (Dragon) o;

		if (this.scaleThickness != dragon.scaleThickness) return false;
		if (this.clawSharpness != dragon.clawSharpness) return false;
		if (this.wingStrength != dragon.wingStrength) return false;
		return this.fireBreath == dragon.fireBreath;
	}

	@Override
	public int hashCode() {
		int result = this.scaleThickness;
		result = 31 * result + this.clawSharpness;
		result = 31 * result + this.wingStrength;
		result = 31 * result + this.fireBreath;
		return result;
	}

	private void assertAbilities() {
		if (sumOfAbilities() != 20) {
			throw new IllegalArgumentException("Expected the sum of 20, got " + sumOfAbilities());
		}
	}

	private int sumOfAbilities() {
		return scaleThickness + clawSharpness + wingStrength + fireBreath;
	}

}
