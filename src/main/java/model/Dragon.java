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
}
