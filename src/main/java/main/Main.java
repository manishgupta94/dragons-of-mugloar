package main;

import game.GameController;
import model.GameResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		GameController gameController = new GameController();
		List<GameResult> results = new ArrayList<>();
		Scanner in = new Scanner(System.in);

		logger.info("Welcome to Dragons of Mugloar.");
		logger.info("Enter the number of knights you want to encounter: ");

		int nrOfGames = 0;

		while (nrOfGames == 0) {
			String nextLn = in.nextLine();

			if (!StringUtils.isNumeric(nextLn)) {
				logger.info("Please enter number");
			} else {
				if (Integer.parseInt(nextLn) <= 0) {
					logger.info("Please enter number that is bigger than zero");
				} else {
					nrOfGames = Integer.parseInt(nextLn);
				}
			}
		}

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
		logger.info("Played games: " + results.size() + ", Won: " + won + ", Lost: " + lost + ", Win ratio " + ( won/gameCounter) * 100 + "%");
	}
}
