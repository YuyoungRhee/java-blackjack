package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameDeckTest {

    @DisplayName("카드를 랜덤 생성한다.")
    @Test
    void testDealerGenerate() {
        GameDeck gameDeck = new GameDeck();
        Card card = gameDeck.generate();

        System.out.println(card);
        assertThat(card).isNotNull();
    }

}
