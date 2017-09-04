package model;

public class GameResult {

	private String status;

	private String message;

	public String getStatus() {
		return this.status;
	}

	@Override
	public String toString() {
		return "GameResult{" +
				"status='" + status + '\'' +
				", message='" + message + '\'' +
				'}';
	}
}
