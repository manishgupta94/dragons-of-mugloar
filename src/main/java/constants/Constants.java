package constants;

public class Constants {

    public static final String GAME_URL = "https://www.dragonsofmugloar.com/api/game";

    public static String getGameSolutionUrl(int gameId) {
        return "https://www.dragonsofmugloar.com/api/game/" + gameId + "/solution";
    }

    public static String getGameWeatherUrl(int gameId) {
        return "https://www.dragonsofmugloar.com/weather/api/report/" + gameId;
    }

}
