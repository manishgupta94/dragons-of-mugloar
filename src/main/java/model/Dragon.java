package model;

public class Dragon {

	private int scaleThickness;

	private int clawSharpness;

	private int wingStrength;

	private int fireBreath;

	public Dragon() {}

	public Dragon(int scaleThickness, int clawSharpness, int wingStrength, int fireBreath) {
		this.scaleThickness = scaleThickness;
		this.clawSharpness = clawSharpness;
		this.wingStrength = wingStrength;
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
}
