package org.cardgame.controller;

import org.cardgame.model.Deck;
import org.cardgame.model.Player;
import org.cardgame.model.PlayingCard;
import org.cardgame.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    enum GameState{
        AddingPlayers, CardsDealt, WinnerRevealed;
    }

    Deck deck;
    List<Player> players;
    Player winner;
    View view;

    GameState gameState;

    public GameController(Deck deck, View view) {  // built elsewhere so needed in the constructor
        this.deck = deck;
        this.view = view;
        // and configure the other data defined directly inside the current class
        this.players = new ArrayList<Player>();
        this.gameState = GameState.AddingPlayers;
        view.setController(this);
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
        Player bestPlayer = null;
        int bestRank = -1;
        int bestSuit = -1;


        for (Player player : players) {
            boolean newBestPlayer = false;

            if (bestPlayer == null) {
                newBestPlayer = true;
            } else {
                PlayingCard playingCard = player.getCard(0);
                int thisRank = playingCard.getRank().value();
                if (thisRank >= bestRank) {
                    if (thisRank > bestRank) {
                        newBestPlayer = true;
                    } else {
                        if (playingCard.getSuit().value() > bestSuit) {
                            newBestPlayer = true;
                        }
                    }
                }
            }

            if (newBestPlayer) {
                bestPlayer = player;
                PlayingCard playingCard = player.getCard(0);
                bestRank = playingCard.getRank().value();
                bestSuit = playingCard.getSuit().value();
            }
        }

        winner = bestPlayer;



    }

     void displayWinner() {
    view.showWinner(winner.getName());
    }

     void rebuildDeck() {
        for (Player player : players) {
            deck.returnCardToDeck(player.removeCard());
        }
    }

}
