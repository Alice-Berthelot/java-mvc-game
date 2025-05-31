package org.cardgame.view;

import org.cardgame.controller.GameController;

import java.util.Scanner;

public class View {
    GameController controller;
    Scanner keyboard = new Scanner(System.in);


    public void setController(GameController gameController) {
        this.controller = gameController;
    }

    public void promptForPlayerName() {
        System.out.println("Enter a player name:");
        String name = keyboard.nextLine();
        if (name.isEmpty()) {
            controller.startGame();
        } else {
            controller.addPlayer(name);
        }
    }

    public void promptForFlip() {
        System.out.println("Press enter to reveal cards:");
        keyboard.nextLine();
        controller.flipCards();
    }

    public void promptForNewGame() {
        System.out.println("Press enter to deal again:");
        keyboard.nextLine();
        controller.startGame();
    }

    public void showPlayerName(int playerIndex, String playerName) {
      System.out.println("[" + playerIndex + "][" + playerName + "]");
    }

    public void showFaceDownCardForPlayer(int playerIndex, String playerName) {
        System.out.println("[" + playerIndex + "][" + playerName + "][X][X]");
    }

    public void showCardForPlayer(int i, String playerName, String rank, String suit) {
        System.out.println("[" + i + "][" + playerName + "][" + rank + "][" + suit + "]");
    }

    public void showWinner(String playerName) {
        System.out.println("The Winner is " + playerName + "!");
    }


}
