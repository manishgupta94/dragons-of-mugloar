package main;

import game.DragonCreatorService;
import game.GameController;
import game.KnightDragonFactory;
import http.HttpClientImpl;
import http.HttpService;
import model.GameResult;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		int nrOfGames = Integer.parseInt(args[0]);

		GameController gameController = getGameController();
		logger.info("Welcome to Dragons of Mugloar.");

		int gameCounter = 0;
		int won = 0;
		while (gameCounter < nrOfGames) {
			logger.info("Game nr: " + (gameCounter + 1));
			try {
				GameResult result = gameController.game();
				if (result.getStatus().equals("Victory")) {
					won++;
				}
			} catch (IOException e) {
				logger.error("Faield to play game", e);
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				logger.error("Thread interrupted", e);
			}

			gameCounter++;
		}

		int lost = gameCounter - won;
		logger.info("Played games: " + gameCounter + ", Won: " + won + ", Lost: " + lost + ", Win ratio " + ( won/gameCounter) * 100 + "%");
	}

	private static GameController getGameController() {
		HttpService httpService = new HttpService(new HttpClientImpl());
		DragonCreatorService dragonCreatorService = new DragonCreatorService(new KnightDragonFactory());
		return new GameController(httpService, dragonCreatorService, logger);
	}
}
