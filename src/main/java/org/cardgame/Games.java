package org.cardgame;

import org.cardgame.controller.GameController;
import org.cardgame.games.HighCardGameEvaluator;
import org.cardgame.model.Deck;
import org.cardgame.view.View;

public class Games {

        public static void main(String[] args) {
            GameController gameController = new GameController(new Deck(), new View(), new HighCardGameEvaluator());
            gameController.run();
        }

}


// IMPLEMENTATION CARTE JOKER, voir chapitre « L » pour le principe de substitution de Liskov