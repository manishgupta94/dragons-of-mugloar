package model;

public class GameResult {

	private String status;

	private String message;

	public String getStatus() {
		return this.status;
	}

	public boolean isWon() {
		if (status.equals("Victory")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "GameResult{" +
				"status='" + status + '\'' +
				", message='" + message + '\'' +
				'}';
	}

}
