package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardDeck {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        Set<Integer> possibleSums = calculatePossibleSum();

        // 21 이하 최대값 찾기
        return possibleSums.stream()
                .filter(sum -> sum <= 21)
                .max(Integer::compareTo)
                .orElse(Collections.min(possibleSums)); // 전부 버스트라면 최소값 리턴
    }

    public Set<Integer> calculatePossibleSum() {
        Set<Integer> sums = new HashSet<>();
        sums.add(0);

        for (Card card : cards) {
            Set<Integer> newSums = new HashSet<>();
            for (int sum : sums) {
                for (int value : card.checkScore()) {
                    newSums.add(sum + value);
                }
            }
            sums = newSums;
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
