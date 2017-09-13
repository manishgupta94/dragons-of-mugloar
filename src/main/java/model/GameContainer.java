package model;

public class GameContainer {

	private int gameId;

	private Knight knight;

	private String weatherCode;

	public GameContainer(int gameId, Knight knight, String weatherCode) {
		this.gameId = gameId;
		this.knight = knight;
		this.weatherCode = weatherCode;
	}

	public int getGameId() {
		return this.gameId;
	}

	public Knight getKnight() {
		return this.knight;
	}

	public String getWeatherCode() {
		return this.weatherCode;
	}
}
