package model;

public class Dragon {

	private int scaleThickness;

	private int clawSharpness;

	private int wingStrength;

	private int fireBreath;

	public Dragon() {}

	public Dragon(int scaleThickness, int clawSharpness, int wingStrength, int fireBreath) {
		if ((scaleThickness + clawSharpness + wingStrength + fireBreath) != 20) { throw new IllegalArgumentException("Sum of stats is not 20"); }
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
}
