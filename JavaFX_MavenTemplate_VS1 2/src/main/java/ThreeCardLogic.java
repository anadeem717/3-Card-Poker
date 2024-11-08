

public class ThreeCardLogic {

    public static int evalHand(ArrayList<Card> hand) {
        return 0;
    }

    // Method to evaluate pair plus winnings
    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        int value = evalHand(hand);

        if (value == 1) return bet * 40; // straight flush
        if (value == 2) return bet * 30; // 3 of a kind
        if (value == 3) return bet * 6;  // straight
        if (value == 4) return bet * 3;  // flush
        if (value == 5) return bet;      // pair

        return 0; // lost pair plus
    }

    // Method to compare the dealer and player hands
    // returns 0 if tie, 1 if dealer wins, 2 if player wins
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int dealerVal = evalHand(delear);
        int playerVal = evalHand(player);

        if (dealerVal == playerVal) return 0; // tie
        if (dealerVal > playerVal) return 1;  // dealer wins
        if (dealerVal < playerVal) return 2;  // player wins
    }
}