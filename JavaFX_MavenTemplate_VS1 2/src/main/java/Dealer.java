import java.util.ArrayList;

public class Dealer {
    private Deck theDeck;
    private ArrayList<Card> dealersHand;

    // Constructor
    public Dealer() {
        // Initialize the deck
        theDeck = new Deck();
        // Initialize dealer's hand
        dealersHand = new ArrayList<Card>();
    }

    // Method to deal a hand of three cards
    public ArrayList<Card> dealHand() {
        // Check if deck needs to be reshuffled (less than 34 cards remaining)
        if (theDeck.getRemainingCards() < 34) {
            theDeck.newDeck();
        }

        // Create new ArrayList for the hand
        ArrayList<Card> hand = new ArrayList<>();

        // Deal 3 cards
        for (int i = 0; i < 3; i++) {
            hand.add(theDeck.drawCard());
        }

        return hand;
    }

    // Deal dealer's own hand
    public void dealDealerHand() {
        // Clear any existing cards in hand
        dealersHand.clear();

        dealersHand = dealHand();
    }

    // Getter for dealer's hand
    public ArrayList<Card> getDealerHand() {
        return dealersHand;
    }

    // Method to check remaining cards in deck
    public int getRemainingCards() {
        return theDeck.getRemainingCards();
    }

    // Method to force a new deck shuffle
    public void shuffleNewDeck() {
        theDeck.newDeck();
    }
}
