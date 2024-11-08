

public class Card {
    char suit;
    int value;

    // Constructor
    public Card (char suit, int value) {

        // check if given suit and val is valid
        if (!checkSuit(suit) || !checkValue(value)) {
            this.suit = 'X';
            this.value = -1;
        }

        // valid suit and value
        else {
            this.suit = suit;
            this.value = value;
        }
    }

    // Method to validate the suit
    private boolean checkSuit(char suit) {
        if (suit == 'C' || suit == 'D' || suit == 'S' || suit == 'H') return true;
        else return false;
    }

    // Method to validate the value
    private boolean checkValue(int value) {
        if (value >= 2 && value <= 14) return true;
        else return false;
    }

}
