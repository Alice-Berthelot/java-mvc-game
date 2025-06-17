package org.cardgame;

import org.cardgame.controller.GameController;
import org.cardgame.games.HighCardGameEvaluator;
import org.cardgame.model.Deck;
import org.cardgame.view.SwingView;

public class Games {

        public static void main(String[] args) {
            SwingView swingView = new SwingView();
            swingView.createAndShowGUI();

            GameController gameController = new GameController(new Deck(), swingView, new HighCardGameEvaluator());
            gameController.run();
        }

}


// IMPLEMENTATION CARTE JOKER, voir chapitre « L » pour le principe de substitution de Liskov