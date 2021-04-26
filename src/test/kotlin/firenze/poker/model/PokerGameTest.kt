package firenze.poker.model

import firenze.poker.fixture.PokerGameFixture
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

internal class PokerGameTest {

    private val pokerGame = PokerGameFixture.pokerGame()

    @Test
    fun `should init poker game when create a poker game object`() {
        val play = PokerGameFixture.plays()
        val pokerGame = PokerGame(plays = play)
        assertEquals(0, pokerGame.pot.amounts)
        assertEquals("Pre-flop", pokerGame.round.name)
        assertEquals(0, pokerGame.communityCards.size)
        assertEquals(0, pokerGame.buttonPosition)
        assertEquals(1, pokerGame.smallBlindPosition)
        assertEquals(2, pokerGame.bigBlindPosition)

    }

    @Test
    fun `should each play have two cards when button deal cards`() {
        // given
        assertEquals(0, pokerGame.plays[0].cards.size)

        // when
        pokerGame.dealCards()

        // then
        assertEquals(2, pokerGame.plays[0].cards.size)
    }

    @Test
    fun `should each player take actions in turn when start the round`() {
        // given
        assertEquals(0, pokerGame.pot.amounts)
        assertEquals(emptyList(), pokerGame.hasDoneActionPlays)

        // when
        pokerGame.startRound()

        // then
        assertEquals(emptyList(), pokerGame.waitingForActionPlays)
        assertEquals(30, pokerGame.pot.amounts)
    }
}