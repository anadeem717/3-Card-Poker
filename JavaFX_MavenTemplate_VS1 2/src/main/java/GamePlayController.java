
import javafx.fxml.FXML;
import javafx.scene.image.*;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class GamePlayController implements Initializable {

    @FXML
    private Pane GamePlayRoot;

    Player playerOne;
    Player playerTwo;
    Dealer theDealer;

    @FXML private ImageView dealerCard1;
    @FXML private ImageView dealerCard2;
    @FXML private ImageView dealerCard3;

    @FXML private ImageView playerOneCard1;
    @FXML private ImageView playerOneCard2;
    @FXML private ImageView playerOneCard3;

    @FXML private ImageView playerTwoCard1;
    @FXML private ImageView playerTwoCard2;
    @FXML private ImageView playerTwoCard3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the dealer and players
        theDealer = new Dealer();
        playerOne = new Player();
        playerTwo = new Player();

        // Deal hands to players and dealer
        theDealer.dealDealerHand();
        playerOne.setHand((theDealer.dealHand()));
        playerTwo.setHand((theDealer.dealHand()));

        // Debugging: Check if the hands are populated
        System.out.println("Dealer Hand: " + theDealer.getDealerHand().size());
        System.out.println("Player One Hand: " + playerOne.getHand().size());
        System.out.println("Player Two Hand: " + playerTwo.getHand().size());

        for (int i = 0; i < 3; i++) {
            System.out.println(theDealer.getDealerHand().get(i).suit);
            System.out.println(playerOne.getHand().get(i).suit);
            System.out.println(playerTwo.getHand().get(i).suit);
        }

        // Load the card images for each player's hand and the dealer's hand
        loadCardImages(theDealer.getDealerHand(), playerOne.getHand(), playerTwo.getHand());
    }

    private void loadCardImages(ArrayList<Card> dealerHand, ArrayList<Card> playerOneHand, ArrayList<Card> playerTwoHand) {
        // For dealer's hand
        dealerCard1.setImage(new Image("/CardImages/back.png"));
        dealerCard2.setImage(new Image("/CardImages/back.png"));
        dealerCard3.setImage(new Image("/CardImages/back.png"));

        // For player one's hand
        playerOneCard1.setImage(new Image(getCardImagePath(playerOneHand.get(0))));
        playerOneCard2.setImage(new Image(getCardImagePath(playerOneHand.get(1))));
        playerOneCard3.setImage(new Image(getCardImagePath(playerOneHand.get(2))));

        // For player two's hand
        playerTwoCard1.setImage(new Image(getCardImagePath(playerTwoHand.get(0))));
        playerTwoCard2.setImage(new Image(getCardImagePath(playerTwoHand.get(1))));
        playerTwoCard3.setImage(new Image(getCardImagePath(playerTwoHand.get(2))));
    }

    private String getCardImagePath(Card card) {
        // Create the file name for the image based on the card's suit and value
        String valueString = getValueString(card.value);
        char suit = card.suit;

        // Map the suit to its string representation using if-else
        String suitString = "";
        if (suit == 'C') {
            suitString = "clubs";
        }
        else if (suit == 'D') {
            suitString = "diamonds";
        }
        else if (suit == 'H') {
            suitString = "hearts";
        }
        else if (suit == 'S') {
            suitString = "spades";
        }

        // Return the file path for the card image
        System.out.println("/CardImages/" + valueString + "_of_" + suitString + ".png");
        return "/CardImages/" + valueString + "_of_" + suitString + ".png";
    }

    // This helper function converts the card value to its string representation using if-else
    private String getValueString(int value) {
        if (value == 11) {
            return "jack";
        }
        else if (value == 12) {
            return "queen";
        }
        else if (value == 13) {
            return "king";
        }
        else if (value == 14) {
            return "ace";
        }
        else {
            return String.valueOf(value);
        }
    }
}
