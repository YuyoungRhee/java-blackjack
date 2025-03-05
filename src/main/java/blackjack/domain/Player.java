package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Player {
    private final String name;
    private final PlayDeck playDeck;
    private final GameDeck gameDeck;

    public Player(String name, PlayDeck playDeck, GameDeck gameDeck) {
        this.name = name;
        this.playDeck = playDeck;
        this.gameDeck = gameDeck;
    }

    public static Player createInitialPlayer(String name, GameDeck gameDeck) {
        PlayDeck playDeck = new PlayDeck();
        playDeck.add(gameDeck.draw());
        playDeck.add(gameDeck.draw());
        return new Player(name, playDeck, gameDeck);
    }


    public boolean canTakeExtraCard() {
        Set<Integer> possibleSum = playDeck.calculatePossibleSum();
        int minScore = Collections.min(possibleSum);

        return minScore <= 21;
    }

    public int calculateScore() {
        Set<Integer> possibleSums = playDeck.calculatePossibleSum();

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
        Card card = gameDeck.draw();
        playDeck.add(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCardDeck() {
        return playDeck.getCards();
    }
}
