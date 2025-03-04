package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("카드가 21이 초과하지 않는다면 카드를 더 뽑을 수 있다.")
    @Test
    void testPlayerCanDrawCard() {
        // given
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        playDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = new Player("user1", playDeck, gameDeck);

        // when
        boolean canTakeExtraCard = player.canTakeExtraCard();

        // then
        assertThat(canTakeExtraCard).isTrue();
    }

    @DisplayName("카드가 21이 초과하지 않는다면 카드를 더 뽑을 수 있다.")
    @Test
    void testPlayerCanDrawCard_false() {
        // given
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        playDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.EIGHT));

        Player player = new Player("user1", playDeck, gameDeck);

        // when
        boolean canTakeExtraCard = player.canTakeExtraCard();

        // then
        assertThat(canTakeExtraCard).isFalse();
    }

    @DisplayName("플레이어의 카드 덱의 최종 점수를 계산한다")
    @Test
    void testPlayerTotalCardScore() {
        // given
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        playDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.EIGHT)); //17

        Player player = new Player("user1", playDeck, gameDeck);

        // when
        int totalScore = player.calculateScore();
        assertThat(totalScore).isEqualTo(17);
    }

    @DisplayName("플레이어의 카드 덱에 에이스가 있고, 버스트가 아닐 때, 최대 점수를 계산한다")
    @Test
    void testPlayerTotalCardScore_hasAce_noBust() {
        // given
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        playDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.ACE)); //11 -> 20

        Player player = new Player("user1", playDeck, gameDeck);

        // when
        int totalScore = player.calculateScore();
        assertThat(totalScore).isEqualTo(20);
    }

    @DisplayName("플레이어의 카드 덱에 에이스가 있고, 버스트일 때, 에이스를 1로 계산한다")
    @Test
    void testPlayerTotalCardScore_hasAce_Bust() {
        // given
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        playDeck.add(new Card(CardSuit.CLUB, CardRank.NINE));
        playDeck.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); //16
        playDeck.add(new Card(CardSuit.CLUB, CardRank.ACE)); //1선택 -> 17

        Player player = new Player("user1", playDeck, gameDeck);

        // when
        int totalScore = player.calculateScore();
        assertThat(totalScore).isEqualTo(17);
    }

    @DisplayName("에이스가 여러개일때 최대 점수를 적절하게 구한다. (버스트되지않는 최대값)")
    @Test
    void testPlayerTotalCardScore_hasAce2() {
        // given
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        playDeck.add(new Card(CardSuit.CLUB, CardRank.NINE)); //9
        playDeck.add(new Card(CardSuit.CLUB, CardRank.ACE)); // 11선택 -> 20
        playDeck.add(new Card(CardSuit.HEART, CardRank.ACE)); //1선택 -> 21

        Player player = new Player("user1", playDeck, gameDeck);

        // when
        int totalScore = player.calculateScore();
        assertThat(totalScore).isEqualTo(21);
    }

    @DisplayName("플레이어는 자신의 카드 덱에 카드를 추가할 수 있다")
    @Test
    void testPlayerAddCard() {
        // given
        PlayDeck playDeck = new PlayDeck();
        GameDeck gameDeck = new GameDeck();
        Player player = new Player("user1", playDeck, gameDeck);

        // when
        player.addCard();

        // then
        assertThat(playDeck.getDeckSize()).isEqualTo(1);
    }
}
