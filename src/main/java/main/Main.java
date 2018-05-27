package main;

import game.GameController;
import game.Play;
import game.PlayImpl;
import game.service.DragonCreatorService;
import http.HttpClientImpl;
import http.HttpService;
import http.ObjectSerializer;
import model.GameResult;
import org.apache.log4j.Logger;

import java.util.List;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int nrOfGames = Integer.parseInt(args[0]);
        boolean isMultiThreaded = Boolean.parseBoolean(args[1]);

        Play play = new PlayImpl(constructGameController(), isMultiThreaded);

        List<GameResult> resultList = play.playGame(nrOfGames);

        int games = resultList.size();
        long won = resultList.stream()
            .filter(GameResult::isWon)
            .count();

        logger.info("Time elapsed: " + (System.currentTimeMillis() - start) / 1000 + " seconds");
        logger.info("Played games: " + games + ", Won: " + won + ", Lost: " + (games - won) + ", Win ratio " + (won / games) * 100 + "%");
    }

    private static GameController constructGameController() {
        HttpService httpService = new HttpService(new HttpClientImpl(), new ObjectSerializer());
        DragonCreatorService dragonCreatorService = new DragonCreatorService();
        return new GameController(httpService, dragonCreatorService);
    }

}
