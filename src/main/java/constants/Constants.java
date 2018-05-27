package constants;

public class Constants {

    public static final String GAME_URL = "http://www.dragonsofmugloar.com/api/game";

    public static String getGameSolutionUrl(int gameId) {
        return "http://www.dragonsofmugloar.com/api/game/" + gameId + "/solution";
    }

    public static String getGameWeatherUrl(int gameId) {
        return "http://www.dragonsofmugloar.com/weather/api/report/" + gameId;
    }
}
