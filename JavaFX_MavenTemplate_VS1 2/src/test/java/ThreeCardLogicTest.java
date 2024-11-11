import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ThreeCardLogicTest {
    // Helper method to create a Card instance for easy testing
    private Card createCard(char suit, int value) {
        return new Card(suit, value);
    }

    @Test
    public void testStraightFlush() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 10));
        hand.add(createCard('H', 11));
        hand.add(createCard('H', 12));

        assertEquals(1, ThreeCardLogic.evalHand(hand)); // straight flush
    }

    @Test
    public void testThreeOfAKind() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 5));
        hand.add(createCard('S', 5));
        hand.add(createCard('D', 5));

        assertEquals(2, ThreeCardLogic.evalHand(hand)); // three of a kind
    }

    @Test
    public void testStraight() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 4));
        hand.add(createCard('D', 5));
        hand.add(createCard('S', 6));

        assertEquals(3, ThreeCardLogic.evalHand(hand)); // straight
    }

    @Test
    public void testFlush() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 2));
        hand.add(createCard('H', 4));
        hand.add(createCard('H', 6));

        assertEquals(4, ThreeCardLogic.evalHand(hand)); // flush
    }

    @Test
    public void testPair() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 8));
        hand.add(createCard('S', 8));
        hand.add(createCard('D', 10));

        assertEquals(5, ThreeCardLogic.evalHand(hand)); // pair
    }

    @Test
    public void testHighCard() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 2));
        hand.add(createCard('S', 6));
        hand.add(createCard('D', 10));

        assertEquals(0, ThreeCardLogic.evalHand(hand)); // high card
    }

    @Test
    public void testEvalPPWinningsStraightFlush() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 10));
        hand.add(createCard('H', 11));
        hand.add(createCard('H', 12));

        assertEquals(120, ThreeCardLogic.evalPPWinnings(hand, 3)); // straight flush * 40
    }

    @Test
    public void testEvalPPWinningsThreeOfAKind() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 5));
        hand.add(createCard('S', 5));
        hand.add(createCard('D', 5));

        assertEquals(90, ThreeCardLogic.evalPPWinnings(hand, 3)); // 3 of a kind * 30
    }

    @Test
    public void testEvalPPWinningsStraight() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 4));
        hand.add(createCard('D', 5));
        hand.add(createCard('S', 6));

        assertEquals(18, ThreeCardLogic.evalPPWinnings(hand, 3)); // straight * 6
    }

    @Test
    public void testEvalPPWinningsFlush() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 2));
        hand.add(createCard('H', 4));
        hand.add(createCard('H', 6));

        assertEquals(9, ThreeCardLogic.evalPPWinnings(hand, 3)); // flush * 3
    }

    @Test
    public void testEvalPPWinningsPair() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 8));
        hand.add(createCard('S', 8));
        hand.add(createCard('D', 10));

        assertEquals(3, ThreeCardLogic.evalPPWinnings(hand, 3)); // pair * 1
    }

    @Test
    public void testEvalPPWinningsHighCard() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 2));
        hand.add(createCard('S', 6));
        hand.add(createCard('D', 10));

        assertEquals(0, ThreeCardLogic.evalPPWinnings(hand, 3)); // high card
    }

    @Test
    public void testCompareHandsDealerWins() {
        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(createCard('H', 10));
        dealerHand.add(createCard('H', 11));
        dealerHand.add(createCard('H', 12));

        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(createCard('S', 5));
        playerHand.add(createCard('S', 6));
        playerHand.add(createCard('S', 7));

        assertEquals(1, ThreeCardLogic.compareHands(dealerHand, playerHand)); // dealer wins
    }

    @Test
    public void testCompareHandsPlayerWins() {
        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(createCard('H', 5));
        dealerHand.add(createCard('H', 6));
        dealerHand.add(createCard('H', 7));

        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(createCard('S', 10));
        playerHand.add(createCard('S', 11));
        playerHand.add(createCard('S', 12));

        assertEquals(2, ThreeCardLogic.compareHands(dealerHand, playerHand)); // player wins
    }

    @Test
    public void testCompareHandsTie() {
        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(createCard('H', 10));
        dealerHand.add(createCard('H', 11));
        dealerHand.add(createCard('H', 12));

        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(createCard('S', 10));
        playerHand.add(createCard('S', 11));
        playerHand.add(createCard('S', 12));

        assertEquals(0, ThreeCardLogic.compareHands(dealerHand, playerHand)); // tie
    }

    @Test
    public void testHandQualifiesTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 12)); // Queen
        hand.add(createCard('S', 9));
        hand.add(createCard('D', 11));

        assertTrue(ThreeCardLogic.handQualifies(hand)); // hand qualifies
    }

    @Test
    public void testHandQualifiesFalse() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 8));
        hand.add(createCard('S', 6));
        hand.add(createCard('D', 10));

        assertFalse(ThreeCardLogic.handQualifies(hand)); // hand does not qualify
    }

    @Test
    public void testIsStraightTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 4));
        hand.add(createCard('S', 5));
        hand.add(createCard('D', 6));

        assertTrue(ThreeCardLogic.isStraight(hand)); // straight
    }

    @Test
    public void testIsStraightFalse() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 4));
        hand.add(createCard('S', 6));
        hand.add(createCard('D', 10));

        assertFalse(ThreeCardLogic.isStraight(hand)); // not a straight
    }

    @Test
    public void testIsFlushTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 2));
        hand.add(createCard('H', 4));
        hand.add(createCard('H', 6));

        assertTrue(ThreeCardLogic.isFlush(hand)); // flush
    }

    @Test
    public void testIsFlushFalse() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 2));
        hand.add(createCard('S', 4));
        hand.add(createCard('H', 6));

        assertFalse(ThreeCardLogic.isFlush(hand)); // not a flush
    }

    @Test
    public void testIsPairTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 8));
        hand.add(createCard('S', 8));
        hand.add(createCard('D', 10));

        assertTrue(ThreeCardLogic.isPair(hand)); // pair
    }

    @Test
    public void testIsPairFalse() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 2));
        hand.add(createCard('S', 5));
        hand.add(createCard('D', 10));

        assertFalse(ThreeCardLogic.isPair(hand)); // no pair
    }

    @Test
    public void testIsThreeOfAKindTrue() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 7));
        hand.add(createCard('S', 7));
        hand.add(createCard('D', 7));

        assertTrue(ThreeCardLogic.isThreeOfAKind(hand)); // three of a kind
    }

    @Test
    public void testIsThreeOfAKindFalse() {
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(createCard('H', 5));
        hand.add(createCard('S', 6));
        hand.add(createCard('D', 7));

        assertFalse(ThreeCardLogic.isThreeOfAKind(hand)); // not three of a kind
    }
}