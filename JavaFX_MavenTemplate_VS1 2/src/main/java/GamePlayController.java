import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class GamePlayController implements Initializable {

    @FXML
    private Pane GamePlayRoot;

    Player playerOne;
    Player playerTwo;
    Dealer theDealer;

    @FXML
    private ImageView dealerCard1;
    @FXML
    private ImageView dealerCard2;
    @FXML
    private ImageView dealerCard3;

    @FXML
    private ImageView playerOneCard1;
    @FXML
    private ImageView playerOneCard2;
    @FXML
    private ImageView playerOneCard3;

    @FXML
    private ImageView playerTwoCard1;
    @FXML
    private ImageView playerTwoCard2;
    @FXML
    private ImageView playerTwoCard3;

    @FXML
    private Button p1AnteBetButton;
    @FXML
    private Button p2AnteBetButton;
    @FXML
    private Button p1PPButton;
    @FXML
    private Button p2PPButton;

    @FXML public TextField p1AnteBetAmount;
    @FXML public TextField p2AnteBetAmount;
    @FXML public TextField p1PPBetAmount;
    @FXML public TextField p2PPBetAmount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        theDealer = new Dealer();
        playerOne = new Player();
        playerTwo = new Player();

        theDealer.dealDealerHand();
        playerOne.setHand(theDealer.dealHand());
        playerTwo.setHand(theDealer.dealHand());

        findCardImages(theDealer.getDealerHand(), playerOne.getHand(), playerTwo.getHand());
    }

    private void findCardImages(ArrayList<Card> dealerHand, ArrayList<Card> playerOneHand, ArrayList<Card> playerTwoHand) {
        dealerCard1.setImage(new Image("/CardImages/back.png"));
        dealerCard2.setImage(new Image("/CardImages/back.png"));
        dealerCard3.setImage(new Image("/CardImages/back.png"));

        playerOneCard1.setImage(new Image(getCardImagePath(playerOneHand.get(0))));
        playerOneCard2.setImage(new Image(getCardImagePath(playerOneHand.get(1))));
        playerOneCard3.setImage(new Image(getCardImagePath(playerOneHand.get(2))));

        playerTwoCard1.setImage(new Image(getCardImagePath(playerTwoHand.get(0))));
        playerTwoCard2.setImage(new Image(getCardImagePath(playerTwoHand.get(1))));
        playerTwoCard3.setImage(new Image(getCardImagePath(playerTwoHand.get(2))));
    }

    private String getCardImagePath(Card card) {
        String valueString = getCardValueString(card.value);
        char suit = card.suit;

        String suitString = "";
        if (suit == 'C') {
            suitString = "clubs";
        } else if (suit == 'D') {
            suitString = "diamonds";
        } else if (suit == 'H') {
            suitString = "hearts";
        } else if (suit == 'S') {
            suitString = "spades";
        }

        return "/CardImages/" + valueString + "_of_" + suitString + ".png";
    }

    private String getCardValueString(int value) {
        if (value == 11) return "jack";
        else if (value == 12) return "queen";
        else if (value == 13) return "king";
        else if (value == 14) return "ace";
        else return String.valueOf(value);
    }

    // Event handler for Player 1's Ante Bet button click
    @FXML
    private void handleP1AnteBet(ActionEvent event) {
        int anteBet = Integer.parseInt(p1AnteBetAmount.getText());
        playerOne.placeBet(anteBet, "ante");
    }

    // Event handler for Player 2's Ante Bet button click
    @FXML
    private void handleP2AnteBet(ActionEvent event) {
        int anteBet = Integer.parseInt(p2AnteBetAmount.getText());
        playerTwo.placeBet(anteBet, "ante");
    }

    // Event handler for Player 1's Pair Plus Bet button click
    @FXML
    private void handleP1PPBet(ActionEvent event) {
        int ppBet = Integer.parseInt(p1PPBetAmount.getText());
        playerOne.placeBet(ppBet, "pp");
    }

    // Event handler for Player 2's Pair Plus Bet button click
    @FXML
    private void handleP2PPBet(ActionEvent event) {
        int ppBet = Integer.parseInt(p2PPBetAmount.getText());
        playerTwo.placeBet(ppBet, "pp");
    }

}
