package org.cardgame.games;

import org.cardgame.controller.GameController;
import org.cardgame.model.Deck;
import org.cardgame.view.View;

public class Games {

        public static void main(String[] args) {
            GameController gameController = new GameController(new Deck(), new View());
            gameController.run();
        }

}
