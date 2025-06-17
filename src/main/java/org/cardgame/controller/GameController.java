package org.cardgame.controller;

import org.cardgame.games.GameEvaluator;
import org.cardgame.model.Deck;
import org.cardgame.model.Player;
import org.cardgame.model.PlayingCard;
import org.cardgame.view.GameViewable;

import java.util.ArrayList;
import java.util.List;

public class GameController {



    enum GameState{
        AddingPlayers, CardsDealt, WinnerRevealed;
    }

    Deck deck;
    List<Player> players;
    Player winner;
    GameViewable view;

    GameState gameState;
    GameEvaluator gameEvaluator;

    public GameController(Deck deck, GameViewable view, GameEvaluator gameEvaluator) {  // built elsewhere so needed in the constructor
        this.deck = deck;
        this.view = view;
        // and configure the other data defined directly inside the current class
        this.players = new ArrayList<Player>();
        this.gameState = GameState.AddingPlayers;
        view.setController(this);
        this.gameEvaluator = gameEvaluator;
    }

    public void run() {
        while (gameState == GameState.AddingPlayers) {
            view.promptForPlayerName();
        }

        switch (gameState) {
            case CardsDealt :
                view.promptForFlip();
                break;
            case WinnerRevealed :
                view.promptForNewGame();
                break;
        }
    }

    public void addPlayer(String playerName) {
        if (gameState == GameState.AddingPlayers) {
            players.add(new Player(playerName));
            view.showPlayerName(players.size(), playerName);
        }
    }

    public void startGame() {
        if (gameState != GameState.CardsDealt) {
            deck.shuffle();
            int playerIndex = 1;
            for (Player player : players) {
                player.addCardToHand(deck.removeTopCard());
                view.showFaceDownCardForPlayer(playerIndex++, player.getName());
            }
            gameState = GameState.CardsDealt;
        }
        this.run();
    }

    public void flipCards() {
        int playerIndex = 1;

        for (Player player : players) {
            PlayingCard playingCard = player.getCard(0);
            playingCard.flip();
            view.showCardForPlayer(playerIndex++, player.getName(), playingCard.getRank().toString(), playingCard.getSuit().toString());
        }

        evaluateWinner();
        displayWinner();
        rebuildDeck();
        gameState = GameState.WinnerRevealed;
        this.run();
    }

    void evaluateWinner() {
        winner = gameEvaluator.evaluateWinner(players);

    }

     void displayWinner() {
    view.showWinner(winner.getName());
    }

     void rebuildDeck() {
        for (Player player : players) {
            deck.returnCardToDeck(player.removeCard());
        }
    }

    void exitGame() {
        System.exit(0);
    }

    public void nextAction(String nextChoice) {
        if ("+q".equals(nextChoice)) {
            exitGame();
        } else {
            startGame();
        }
    }
}
