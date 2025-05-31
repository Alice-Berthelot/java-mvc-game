package org.cardgame.model;

public class Player {
    private String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
        hand = new Hand();
    }

public void addCardToHand(PlayingCard playingCard) {
        hand.addCart(playingCard);
}

public PlayingCard removeCard() {
        return hand.removeCard();
}

public PlayingCard getCard(int index) {
        return hand.getCard(index);
}

    public String getName() {
        return name;
    }
}
