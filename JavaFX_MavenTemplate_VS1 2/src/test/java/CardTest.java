
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CardTest {

	@Test
	void testValidCard() {
		Card card = new Card('H', 10);
		assertEquals('H', card.suit);
		assertEquals(10, card.value);
	}

	@Test
	void testInvalidSuit() {
		Card card = new Card('X', 10);
		assertEquals('X', card.suit);
		assertEquals(-1, card.value);
	}

	@Test
	void testInvalidValue() {
		Card card = new Card('H', 20);
		assertEquals('X', card.suit);
		assertEquals(-1, card.value);
	}

	@Test
	void testInvalidSuitAndValue() {
		Card card = new Card('Z', 1);
		assertEquals('X', card.suit);
		assertEquals(-1, card.value);
	}

}

