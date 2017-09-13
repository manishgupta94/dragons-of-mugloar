package model;

public class Game {

	private int gameId;

	private Knight knight;

	public Game() {}

	public int getGameId() {
		return this.gameId;
	}

	public Knight getKnight() {
		return this.knight;
	}

	@Override
	public String toString() {
		return "Play{" +
				"gameId=" + gameId +
				", knight=" + knight +
				'}';
	}
}
