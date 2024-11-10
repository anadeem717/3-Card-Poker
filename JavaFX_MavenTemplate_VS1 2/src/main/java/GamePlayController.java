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

    @FXML
    private TextField p1BetAmountTextField;
    @FXML
    private TextField p2BetAmountTextField;

    @FXML
    private Button p1ConfirmBetButton;
    @FXML
    private Button p2ConfirmBetButton;

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
        p1BetAmountTextField.setVisible(true);
        p1ConfirmBetButton.setVisible(true);
    }

    // Event handler for Player 2's Ante Bet button click
    @FXML
    private void handleP2AnteBet(ActionEvent event) {
        p2BetAmountTextField.setVisible(true);
        p2ConfirmBetButton.setVisible(true);
    }

    // Event handler for Player 1's Pair Plus Bet button click
    @FXML
    private void handleP1PPBet(ActionEvent event) {
        p1BetAmountTextField.setVisible(true);
        p1ConfirmBetButton.setVisible(true);
    }

    // Event handler for Player 2's Pair Plus Bet button click
    @FXML
    private void handleP2PPBet(ActionEvent event) {
        p2BetAmountTextField.setVisible(true);
        p2ConfirmBetButton.setVisible(true);
    }

    // Confirm Player 1's Bet
    @FXML
    private void confirmP1Bet(ActionEvent event) {
        int betAmount = Integer.parseInt(p1BetAmountTextField.getText());
        if (p1AnteBetButton.isFocused()) {
            playerOne.placeBet(betAmount, "ante");
        }
        else if (p1PPButton.isFocused()) {
            playerOne.placeBet(betAmount, "pp");
        }
        p1BetAmountTextField.setVisible(false);
        p1ConfirmBetButton.setVisible(false);
    }

    // Confirm Player 2's Bet
    @FXML
    private void confirmP2Bet(ActionEvent event) {
        int betAmount = Integer.parseInt(p2BetAmountTextField.getText());
        if (p2AnteBetButton.isFocused()) {
            playerTwo.placeBet(betAmount, "ante");
        }
        else if (p2PPButton.isFocused()) {
            playerTwo.placeBet(betAmount, "pp");
        }
        p2BetAmountTextField.setVisible(false);
        p2ConfirmBetButton.setVisible(false);
    }
}
