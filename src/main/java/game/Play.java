package game;

import model.GameResult;

import java.util.List;

public interface Play {
	List<GameResult> start(int nrOfGames, boolean isAsync);
}
