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
        if (value == 5) return bet * 2;      // pair

        return 0; // lost pair plus
    }

    // Method to compare the dealer and player hands
    // returns 0 if tie, 1 if dealer wins, 2 if player wins
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int dealerVal = evalHand(dealer);
        int playerVal = evalHand(player);

        // If both hands are of the same type
        if (dealerVal == playerVal) {
            if (dealerVal == 4 || dealerVal == 0) { // Flush or High Card case
                return compareHighCards(dealer, player); // Compare high cards
            }

            else if (dealerVal == 3 || dealerVal == 1) { // Straight or Straight Flush case
                int dealerHighCard = getHighCardInStraight(dealer);
                int playerHighCard = getHighCardInStraight(player);
                if (dealerHighCard > playerHighCard) return 1; // Dealer wins
                else if (dealerHighCard < playerHighCard) return 2; // Player wins
                else return 0; // Tie
            }

            else if (dealerVal == 2) { // Three of a Kind case
                int dealerValSingle = dealer.get(0).value; // All cards have the same value
                int playerValSingle = player.get(0).value;
                if (dealerValSingle > playerValSingle) return 1; // Dealer wins
                else if (dealerValSingle < playerValSingle) return 2; // Player wins
                else return 0; // Tie
            }

            else if (dealerVal == 5) { // Pair case
                return comparePairs(dealer, player); // Compare pairs and kickers
            }
        }

        // If hands have different types, higher value wins
        if (dealerVal > playerVal) return 1; // Dealer wins
        else return 2; // Player wins
    }

    private static int compareHighCards(ArrayList<Card> dealer, ArrayList<Card> player) {
        ArrayList<Integer> dealerValues = new ArrayList<>();
        ArrayList<Integer> playerValues = new ArrayList<>();

        for (Card card : dealer) dealerValues.add(card.value);
        for (Card card : player) playerValues.add(card.value);

        // sort descending, so highest is first
        Collections.sort(dealerValues, Collections.reverseOrder());
        Collections.sort(playerValues, Collections.reverseOrder());

        for (int i = 0; i < dealerValues.size(); i++) {
            if (dealerValues.get(i) > playerValues.get(i)) return 1; // Dealer wins
            else if (dealerValues.get(i) < playerValues.get(i)) return 2; // Player wins
        }

        return 0; // Tie
    }

    private static int comparePairs(ArrayList<Card> dealer, ArrayList<Card> player) {
        int dealerPairValue = findPairValue(dealer);
        int playerPairValue = findPairValue(player);

        if (dealerPairValue > playerPairValue) return 1; // Dealer's pair is higher
        if (dealerPairValue < playerPairValue) return 2; // Player's pair is higher

        // if pairs same, compare the 3rd card
        return compareHighCards(dealer, player);
    }

    private static int findPairValue(ArrayList<Card> hand) {
        ArrayList<Integer> values = new ArrayList<>();
        for (Card card : hand) values.add(card.value);

        for (int i = 0; i < values.size(); i++) {
            for (int j = i + 1; j < values.size(); j++) {
                if (values.get(i).equals(values.get(j))) {
                    return values.get(i); // Return the pair value
                }
            }
        }
        return -1; // not poss
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

        return true; // it's a straight
    }

    // Method to get the highest card in a straight
    public static int getHighCardInStraight(ArrayList<Card> hand) {
        // Create a array of card values
        ArrayList<Integer> values = new ArrayList<>();
        for (Card card : hand) {
            values.add(card.value); // get the value of each card
        }

        // sort the card values
        Collections.sort(values);

        // return the highest card in straight
        return values.get(values.size() - 1);
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

    // Method to check if dealer's hand qualifies (Queen high or better)
    public static boolean handQualifies(ArrayList<Card> hand) {
        // Find the highest card in the hand
        int highestCardValue = 0;

        // case better than a high card
        if (evalHand(hand) > 0) return true;

        for (Card card : hand) {
            if (card.value > highestCardValue) {
                highestCardValue = card.value;
            }
        }

        // Check if the highest card is Queen (value 12) or better (value 13 or 14)
        return highestCardValue >= 12;
    }

}