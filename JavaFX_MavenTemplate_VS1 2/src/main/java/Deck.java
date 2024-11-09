import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {

    // Constructor
    public Deck() {
        // Call newDeck to get the deck with 52 cards
        newDeck();
    }

    // Create a new deck of 52 cards in random order
    public void newDeck() {
        // Clear existing cards
        clear();

        // Create all 52 cards
        char[] suits = {'C', 'D', 'H', 'S'};

        // For each suit
        for (char suit : suits) {
            // Create cards with values 2-14 (2-10, J=11, Q=12, K=13, A=14)
            for (int value = 2; value <= 14; value++) {
                add(new Card(suit, value));
            }
        }

        // Shuffle the deck
        Collections.shuffle(this);
    }

    // Draw a card from the top of the deck
    public Card drawCard() {
        if (isEmpty()) {
            return null;
        }
        return remove(size() - 1);
    }

    // Get remaining cards count
    public int getRemainingCards() {
        return size();
    }

    // Check if deck is empty
    public boolean isEmpty() {
        return super.isEmpty();
    }

}