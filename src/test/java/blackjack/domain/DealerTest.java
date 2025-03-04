package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드의 합이 16 이하이면 카드를 한장 더 받아야한다.")
    @Test
    void testDealerGenerate() {
        // given
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        playDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer dealer = new Dealer(playDeck, gameDeck);

        // when
        boolean takenExtraCard = dealer.hasTakenExtraCard();

        assertThat(takenExtraCard).isTrue();
    }

    @DisplayName("딜러는 카드의 합이 17 이상이면 카드를 더 받지 않는다.")
    @Test
    void testDealerGenerate2() {
        // given
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        playDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.JACK));

        Dealer dealer = new Dealer(playDeck, gameDeck);

        // when
        boolean takenExtraCard = dealer.hasTakenExtraCard();

        assertThat(takenExtraCard).isFalse();
    }

    @DisplayName("딜러는 자신이 가진 카드 덱의 합을 계산할 수 있다")
    @Test
    void testDealerTotalCardSum() {
        // given
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        playDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.EIGHT));

        Dealer dealer = new Dealer(playDeck, gameDeck);

        // when
        int totalScore = dealer.calculateScore();
        assertThat(totalScore).isEqualTo(17);
    }

    @DisplayName("딜러의 점수가 21 초과면 버스트다.")
    @Test
    void testBust_False() {
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        playDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.EIGHT));

        Dealer dealer = new Dealer(playDeck, gameDeck);

        boolean bust = dealer.isBust();

        assertThat(bust).isFalse();
    }

    @DisplayName("딜러의 점수가 21 이하이면 버스트가 아니다.")
    @Test
    void testBust_True() {
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        playDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.EIGHT));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.FIVE));

        Dealer dealer = new Dealer(playDeck, gameDeck);

        boolean bust = dealer.isBust();

        assertThat(bust).isTrue();
    }
}
