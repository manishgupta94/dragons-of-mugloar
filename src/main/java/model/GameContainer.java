package model;

public class GameContainer {

    private int gameId;
    private Knight knight;
    private WeatherCode weatherCode;

    public GameContainer(int gameId, Knight knight, WeatherCode weatherCode) {
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

    public WeatherCode getWeatherCode() {
        return this.weatherCode;
    }

}
