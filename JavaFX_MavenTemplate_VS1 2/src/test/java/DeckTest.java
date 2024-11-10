import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

public class DeckTest {
    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void testInitialDeckSize() {
        assertEquals(52, deck.size());
        assertEquals(52, deck.getRemainingCards());
    }

    @Test
    void testDeckContainsAllCards() {
        int hearts = 0, diamonds = 0, clubs = 0, spades = 0;

        for (Card card : deck) {
            switch (card.suit) {
                case 'H': hearts++; break;
                case 'D': diamonds++; break;
                case 'C': clubs++; break;
                case 'S': spades++; break;
            }
        }

        assertEquals(13, hearts);
        assertEquals(13, diamonds);
        assertEquals(13, clubs);
        assertEquals(13, spades);

        int[] valueCounts = new int[15];
        for (Card card : deck) {
            valueCounts[card.value]++;
        }

        for (int i = 2; i <= 14; i++) {
            assertEquals(4, valueCounts[i], "Should have 4 cards of value " + i);
        }
    }

    @Test
    void testUniqueCards() {
        Set<String> cardSet = new HashSet<>();

        for (Card card : deck) {
            String cardString = card.suit + String.valueOf(card.value);
            assertTrue(cardSet.add(cardString), "Found duplicate card: " + cardString);
        }

        assertEquals(52, cardSet.size());
    }

    @Test
    void testDrawCard() {
        Card card = deck.drawCard();
        assertNotNull(card);
        assertEquals(51, deck.getRemainingCards());

        for (int i = 0; i < 51; i++) {
            assertNotNull(deck.drawCard());
        }

        assertTrue(deck.isEmpty());
        assertEquals(0, deck.getRemainingCards());

        assertNull(deck.drawCard());
    }

    @Test
    void testNewDeck() {
        for (int i = 0; i < 10; i++) {
            deck.drawCard();
        }

        assertEquals(42, deck.getRemainingCards());

        deck.newDeck();

        assertEquals(52, deck.getRemainingCards());
        assertFalse(deck.isEmpty());
    }

}