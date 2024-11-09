import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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

    @Test
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();

        int differentPositions = 0;
        for (int i = 0; i < 52; i++) {
            if (!deck1.get(i).suit.equals(deck2.get(i).suit) ||
                    deck1.get(i).value != deck2.get(i).value) {
                differentPositions++;
            }
        }
        assertTrue(differentPositions > 0, "Decks should be shuffled differently");
    }

    @Test
    void testValidCardValues() {
        for (Card card : deck) {
            assertTrue(card.value >= 2 && card.value <= 14,
                    "Card value should be between 2 and 14, found: " + card.value);
        }
    }

    @Test
    void testValidCardSuits() {
        String validSuits = "CDHS";
        for (Card card : deck) {
            assertTrue(validSuits.indexOf(card.suit) != -1,
                    "Card suit should be one of CDHS, found: " + card.suit);
        }
    }

}