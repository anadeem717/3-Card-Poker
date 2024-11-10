import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    void testInitialization() {
        assertNotNull(dealer.getDealerHand());
        assertEquals(0, dealer.getDealerHand().size());
        assertEquals(52, dealer.getRemainingCards());
    }

    @Test
    void testDealHand() {
        ArrayList<Card> hand = dealer.dealHand();

        assertNotNull(hand);
        assertEquals(3, hand.size());
        assertEquals(49, dealer.getRemainingCards());

        for (Card card : hand) {
            assertNotNull(card);
            assertTrue(card.value >= 2 && card.value <= 14);
            assertTrue("CDHS".indexOf(card.suit) != -1);
        }
    }

    @Test
    void testDealDealerHand() {
        dealer.dealDealerHand();
        ArrayList<Card> dealerHand = dealer.getDealerHand();

        assertNotNull(dealerHand);
        assertEquals(3, dealerHand.size());
        assertEquals(49, dealer.getRemainingCards());

        dealer.dealDealerHand();
        ArrayList<Card> newDealerHand = dealer.getDealerHand();

        assertEquals(3, newDealerHand.size());
        assertEquals(46, dealer.getRemainingCards());
    }

    @Test
    void testDeckReshuffle() {
        int initialHands = 6;
        for (int i = 0; i < initialHands; i++) {
            dealer.dealHand();
        }

        assertEquals(34, dealer.getRemainingCards());

        ArrayList<Card> hand = dealer.dealHand();

        assertEquals(49, dealer.getRemainingCards());
        assertNotNull(hand);
        assertEquals(3, hand.size());
    }

    @Test
    void testForceNewDeck() {
        dealer.dealHand();
        dealer.dealHand();

        dealer.shuffleNewDeck();

        assertEquals(52, dealer.getRemainingCards());
    }

}