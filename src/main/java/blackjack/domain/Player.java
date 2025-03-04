package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Player {
    private final String name;
    private final CardDeck cardDeck;
    private final CardGenerator cardGenerator;

    public Player(String name, CardDeck cardDeck, CardGenerator cardGenerator) {
        this.name = name;
        this.cardDeck = cardDeck;
        this.cardGenerator = cardGenerator;
    }


    public boolean canTakeExtraCard() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        int minScore = Collections.min(possibleSum);

        return minScore <= 21;
    }

    public int calculateScore() {
        Set<Integer> possibleSums = cardDeck.calculatePossibleSum();

        // 21 이하 최대값 찾기
        return possibleSums.stream()
                .filter(sum -> sum <= 21)
                .max(Integer::compareTo)
                .orElse(Collections.min(possibleSums)); // 전부 버스트라면 최소값 리턴
    }

    public boolean isBust() {
        return calculateScore() > 21;
    }

    public void addCard() {
        Card card = cardGenerator.generate();
        cardDeck.add(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCardDeck() {
        return cardDeck.getCards();
    }
}
