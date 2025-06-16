package org.cardgame.games;

import org.cardgame.model.Player;
import org.cardgame.model.PlayingCard;

import java.util.List;

public interface GameEvaluator {
    public Player evaluateWinner(List<Player> players);
}
