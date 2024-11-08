import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand; // player's hand
    private int anteBet;          // ante wager
    private int playBet;          // play wager
    private int pairPlusBet;      // pair plus wager
    private int totalWinnings;    // total winnings across games

    // No-Argument Constructor
    public Player() {
        this.hand = new ArrayList<>();
        this.anteBet = 0;
        this.playBet = 0;
        this.pairPlusBet = 0;
        this.totalWinnings = 0;
    }

    // Method places a bet, either ante bet or pair-plus
    public boolean placeBet(int amount, String betType) {

        // convert betType to lowercase
        betType = betType.toLowerCase();

        // validate the amount
        if (amount < 5 || amount > 25) {
            System.out.println("Amount must be between $5 and $25.");
            return false;
        }

        // Check the bet type and assign the bet
        if (betType.equals("ante")) {
            this.anteBet = amount;
        }

        else if (betType.equals("pp")) {
            this.pairPlusBet = amount;
        }

        else {
            System.out.println("Invalid bet type. Use <ante> or <pp>.");
            return false;
        }

        return true; // bet placed successfully
    }


    // Method that sets the player hand
    public void setHand(ArrayList<Card> newHand) {
        this.hand = newHand;
    }

    // Method that gets the playes hand
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    // Method that reset bets
    // player folds or at the end of a game
    public void resetBets() {
        this.anteBet = 0;
        this.playBet = 0;
        this.pairPlusBet = 0;
    }

    // Method that gets the total winnings
    public int getTotalWinnings() {
        return this.totalWinnings;
    }

    // Method that updates total winnings
    public void updateWinnings(int amount) {
        this.totalWinnings += amount;
    }

    public int getAnteBet() { return anteBet; }

    public int getPairPlusBet() { return pairPlusBet; }

    public int getPlayBet() { return playBet; }
}
