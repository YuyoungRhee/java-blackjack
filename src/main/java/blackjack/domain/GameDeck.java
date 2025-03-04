package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameDeck {
    private final List<Card> cards;

    public GameDeck() {
        this.cards = initializeDeck();
        shuffle();
    }

    private List<Card> initializeDeck() {
        List<Card> deck = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            for (CardRank rank : CardRank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        return deck;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드 덱이 비었습니다!");
        }
        return cards.remove(0);  // 한 장 뽑기
    }

}
