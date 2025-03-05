package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Dealer {
    private final PlayDeck playDeck;
    private final GameDeck gameDeck;

    public Dealer(PlayDeck playDeck, GameDeck gameDeck) {
        this.playDeck = playDeck;
        this.gameDeck = gameDeck;
    }

    public static Dealer createWithDeck(GameDeck gameDeck) {
        PlayDeck playDeck = new PlayDeck();
        playDeck.add(gameDeck.draw());
        playDeck.add(gameDeck.draw());
        return new Dealer(playDeck, gameDeck);
    }

    public boolean hasTakenExtraCard() {
        if (mustTakeExtraCard()) {
            takeExtraCard();
            return true;
        }
        return false;
    }

    private boolean mustTakeExtraCard() {
        return calculateScore() <= 16;
    }

    private void takeExtraCard() {
        playDeck.add(gameDeck.draw());
    }

    public int calculateScore() {
        Set<Integer> possibleScore = playDeck.calculatePossibleSum();
        return Collections.max(possibleScore);
    }

    public boolean isBust() {
        return calculateScore() > 21;
    }

    public List<Card> getCardDeck() {
        return playDeck.getCards();
    }

}
