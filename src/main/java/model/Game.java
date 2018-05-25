package model;

public class Game {

	private int gameId;
	private Knight knight;

	public int getGameId() {
		return this.gameId;
	}

	public Knight getKnight() {
		return this.knight;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public void setKnight(Knight knight) {
		this.knight = knight;
	}

	@Override
	public String toString() {
		return "Play{" +
				"gameId=" + gameId +
				", knight=" + knight +
				'}';
	}

}
