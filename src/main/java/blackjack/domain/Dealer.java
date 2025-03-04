package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Dealer {
    private final CardDeck cardDeck;
    private final CardGenerator cardGenerator;

    public Dealer(CardDeck cardDeck, CardGenerator cardGenerator) {
        this.cardDeck = cardDeck;
        this.cardGenerator = cardGenerator;
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
        cardDeck.add(cardGenerator.generate());
    }

    public int calculateScore() {
        Set<Integer> possibleScore = cardDeck.calculatePossibleSum();
        return Collections.max(possibleScore);
    }

    public boolean isBust() {
        return calculateScore() > 21;
    }

    public List<Card> getCardDeck() {
        return cardDeck.getCards();
    }

}
