package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class GameDeckTest {

    @Test
    @DisplayName("덱이 비어있을 때 카드를 뽑으려고 하면 예외가 발생한다")
    void drawCardFromEmptyDeck() {
        GameDeck gameDeck = new GameDeck();

        for (int i = 0; i < 52; i++) {
            gameDeck.draw();
        }

        assertThatThrownBy(gameDeck::draw)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("카드 덱이 비었습니다!");
    }

    @Test
    @DisplayName("덱에 중복되지 않은 52장의 카드가 존재한다")
    void deckHasUniqueCards() {
        GameDeck gameDeck = new GameDeck();
        Set<Card> drawnCards = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            drawnCards.add(gameDeck.draw());
        }

        assertThat(drawnCards).hasSize(52);
    }

    @Test
    @DisplayName("shuffle() 호출 시 덱이 섞인다 (완전 검증은 어려우나 카드 순서가 바뀌는지 확인)")
    void shuffleDeck() {
        GameDeck gameDeck = new GameDeck();

        // 섞기 전 상태 복사
        GameDeck unshuffledDeck = new GameDeck();

        gameDeck.shuffle();

        // 단순히 같은 순서인지 비교 (완전한 무작위 검증은 아님)
        boolean isSameOrder = true;
        for (int i = 0; i < 52; i++) {
            if (!gameDeck.draw().equals(unshuffledDeck.draw())) {
                isSameOrder = false;
                break;
            }
        }

        assertThat(isSameOrder).isFalse();
    }
}
