package org.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<PlayingCard> cards;

    public Hand() {
        cards = new ArrayList<PlayingCard>();
    }

    public PlayingCard getCard(int index) {
        return cards.get(index);
    }

    public void addCart(PlayingCard playingCard) {
        cards.add(playingCard);
    }

    public PlayingCard removeCard() {
        return cards.remove(0);
    }
}
