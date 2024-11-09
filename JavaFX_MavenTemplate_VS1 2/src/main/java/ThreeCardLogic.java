import java.util.ArrayList;
import java.util.Collections;

public class ThreeCardLogic {

    // Method that evaluates the given hand's value
    public static int evalHand(ArrayList<Card> hand) {
        // Check straight flush (1)
        if (isStraight(hand) && isFlush(hand)) return 1;

        // Check three of a kind (2)
        if (isThreeOfAKind(hand)) return 2;

        // Check straight (3)
        if (isStraight(hand)) return 3;

        // Check flush (4)
        if (isFlush(hand)) return 4;

        // Check pair (5)
        if (isPair(hand)) return 5;

        // High card (0)
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
        int dealerVal = evalHand(dealer);
        int playerVal = evalHand(player);

        if (dealerVal == playerVal) return 0; // tie
        else if (dealerVal > playerVal) return 1;  // dealer wins
        else return 2;  // player wins
    }

    // Method that checks to see if the hand is a 'Straight'
    public static boolean isStraight(ArrayList<Card> hand) {
        // Create a array of card values
        ArrayList<Integer> values = new ArrayList<>();
        for (Card card : hand) {
            values.add(card.value); // get the value of each card
        }

        // sort the card values
        Collections.sort(values);

        // check if card values are consecutive
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i) != values.get(i + 1) - 1) {
                return false; // not a straight
            }
        }

        return true; // straight
    }


    // Method that checks to see if the hand is a 'Flush'
    public static boolean isFlush(ArrayList<Card> hand) {
        char suit = hand.get(0).suit; // card suit to compare to

        // check if all cards have the same suit
        for (Card card : hand) {
            if (card.suit != suit) {
                return false; // not a flush
            }
        }

        return true; // flush
    }


    // Method that checks to see if the hand is a 'Pair'
    public static boolean isPair(ArrayList<Card> hand) {
        // create an array of card values
        ArrayList<Integer> values = new ArrayList<>();
        for (Card card : hand) {
            values.add(card.value); // get the value of each card
        }

        // iterate through values and check for a pair
        for (int i = 0; i < values.size(); i++) {
            for (int j = i + 1; j < values.size(); j++) {
                if (values.get(i).equals(values.get(j))) {
                    return true; // pair found
                }
            }
        }

        return false; // no pair found
    }


    // Method that checks to see if the hand is 'Three of a Kind'
    public static boolean isThreeOfAKind(ArrayList<Card> hand) {
        int value = hand.get(0).value; // card value to compare to

        // check if all cards have the same value
        for (Card card : hand) {
            if (card.value != value) {
                return false; // not a three of a kind
            }
        }

        return true; // three of a kind
    }


}