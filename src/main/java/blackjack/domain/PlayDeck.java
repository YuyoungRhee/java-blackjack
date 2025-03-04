package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayDeck {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public Set<Integer> calculatePossibleSum() {
        Set<Integer> sums = new HashSet<>();
        sums.add(0);

        for (Card card : cards) {
            sums = sums.stream()
                    .flatMap(sum -> card.checkScore().stream().map(value -> sum + value))
                    .collect(Collectors.toSet());
        }
        return sums;
    }

    public int getDeckSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
