import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;



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

    @FXML private Button dealCardsButton;

    private boolean cardsDealt = false;

    @FXML public Button p1AnteBetButton;
    @FXML public Button p1PPButton;
    @FXML public Button p2AnteBetButton;
    @FXML public Button p2PPButton;

    @FXML public TextField p1AnteBetAmount;
    @FXML public TextField p2AnteBetAmount;
    @FXML public TextField p1PPBetAmount;
    @FXML public TextField p2PPBetAmount;
    @FXML public Text P1anteBetText;
    @FXML public Text P1PPBetText;
    @FXML public Text P1PlayBetText;
    @FXML public Text P2anteBetText;
    @FXML public Text P2PPBetText;
    @FXML public Text P2PlayBetText;

    @FXML public Text p1Winnings;
    @FXML public Text p2Winnings;

    private boolean p1AnteBetPlaced = false;
    private boolean p2AnteBetPlaced = false;

    @FXML Button p1Play;
    @FXML Button p1Fold;
    @FXML Button p2Play;
    @FXML Button p2Fold;

    boolean p1Folded = false;
    boolean p2Folded = false;

    @FXML VBox p1BetsVBox;
    @FXML VBox p2BetsVBox;

    private boolean p1Selected = false;
    private boolean p2Selected = false;

    @FXML Button revealButton;
    @FXML Button continueButton;

    private boolean dealerHandQualifies = false;

    @FXML public TextArea gameInfoArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        theDealer = new Dealer();
        playerOne = new Player();
        playerTwo = new Player();

        // Initial card setup
        theDealer.dealDealerHand();
        playerOne.setHand(theDealer.dealHand());
        playerTwo.setHand(theDealer.dealHand());
        findCardImages(theDealer.getDealerHand(), playerOne.getHand(), playerTwo.getHand());

        // Disable Deal Cards button and betting buttons initially
        dealCardsButton.setDisable(true);
        p1AnteBetButton.setDisable(false);
        p2AnteBetButton.setDisable(false);
        p1PPButton.setDisable(false);
        p2PPButton.setDisable(false);
    }


    private void findCardImages(ArrayList<Card> dealerHand, ArrayList<Card> playerOneHand, ArrayList<Card> playerTwoHand) {


        if (cardsDealt) {

            // Show Player 1's and Player 2's cards after dealing
            playerOneCard1.setImage(new Image(getCardImagePath(playerOneHand.get(0))));
            playerOneCard2.setImage(new Image(getCardImagePath(playerOneHand.get(1))));
            playerOneCard3.setImage(new Image(getCardImagePath(playerOneHand.get(2))));

            playerTwoCard1.setImage(new Image(getCardImagePath(playerTwoHand.get(0))));
            playerTwoCard2.setImage(new Image(getCardImagePath(playerTwoHand.get(1))));
            playerTwoCard3.setImage(new Image(getCardImagePath(playerTwoHand.get(2))));

        } else {
            // Initially show only back of cards
            dealerCard1.setImage(new Image("/CardImages/back.png"));
            dealerCard2.setImage(new Image("/CardImages/back.png"));
            dealerCard3.setImage(new Image("/CardImages/back.png"));

            playerOneCard1.setImage(new Image("/CardImages/back.png"));
            playerOneCard2.setImage(new Image("/CardImages/back.png"));
            playerOneCard3.setImage(new Image("/CardImages/back.png"));

            playerTwoCard1.setImage(new Image("/CardImages/back.png"));
            playerTwoCard2.setImage(new Image("/CardImages/back.png"));
            playerTwoCard3.setImage(new Image("/CardImages/back.png"));
        }
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
        if (!playerOne.placeBet(anteBet, "ante")) {
            showWarning("Ante Bet must be between $5 and $25.");
            return;
        }

        P1anteBetText.setText("Ante Bet: $" + Integer.toString(playerOne.getAnteBet()));
        p1AnteBetButton.setDisable(true);
        p1AnteBetPlaced = true;
        p1AnteBetAmount.clear();

        checkAnteBets();
    }

    // Event handler for Player 2's Ante Bet button click
    @FXML
    private void handleP2AnteBet(ActionEvent event) {
        int anteBet = Integer.parseInt(p2AnteBetAmount.getText());

        if (!playerTwo.placeBet(anteBet, "ante")) {
            showWarning("Ante Bet must be between $5 and $25.");
            return;
        }

        P2anteBetText.setText("Ante Bet: $" + Integer.toString(playerTwo.getAnteBet()));
        p2AnteBetButton.setDisable(true);
        p2AnteBetPlaced = true;
        p2AnteBetAmount.clear();

        checkAnteBets();
    }

    private void checkAnteBets() {
        // Enable the Deal Cards button only if both Ante bets are placed
        if (p1AnteBetPlaced && p2AnteBetPlaced) {
            dealCardsButton.setDisable(false);
            dealCardsButton.setVisible(true);
        }
    }

    // Event handler for Player 1's Pair Plus Bet button click
    @FXML
    private void handleP1PPBet(ActionEvent event) {
        int ppBet = Integer.parseInt(p1PPBetAmount.getText());

        if (!playerOne.placeBet(ppBet, "pp")) {
            showWarning("Pair Plus Bet must be between $5 and $25.");
            return;
        }

        P1PPBetText.setText("PairPlus Bet: $" + Integer.toString(playerOne.getPairPlusBet()));
        p1PPButton.setDisable(true);
        p1PPBetAmount.clear();
    }

    // Event handler for Player 2's Pair Plus Bet button click
    @FXML
    private void handleP2PPBet(ActionEvent event) {
        int ppBet = Integer.parseInt(p2PPBetAmount.getText());

        if (!playerTwo.placeBet(ppBet, "pp")) {
            showWarning("Pair Plus Bet must be between $5 and $25.");
            return;
        }

        P2PPBetText.setText("PairPlus Bet: $" + Integer.toString(playerTwo.getPairPlusBet()));
        p2PPButton.setDisable(true);
        p2PPBetAmount.clear();
    }

    // Helper method to show a warning alert
    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Bet");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleDealCards(ActionEvent event) {

        // Proceed with dealing cards
        cardsDealt = true;
        findCardImages(theDealer.getDealerHand(), playerOne.getHand(), playerTwo.getHand());

        // Disable the Deal Cards button to prevent re-dealing
        dealCardsButton.setDisable(true);
        dealCardsButton.setVisible(false);

        // disable pp bets
        p1PPButton.setDisable(true);
        p2PPButton.setDisable(true);

        p1BetsVBox.setVisible(false);
        p2BetsVBox.setVisible(false);

        // enable the play/fold buttons
        p1Play.setVisible(true);
        p1Play.setDisable(false);
        p2Play.setVisible(true);
        p2Play.setDisable(false);
        p1Fold.setVisible(true);
        p1Fold.setDisable(false);
        p2Fold.setVisible(true);
        p2Fold.setDisable(false);

    }

    @FXML private void handleP1Play(ActionEvent event) {
        playerOne.setPlayBet(playerOne.getAnteBet());
        P1PlayBetText.setText("Play Wager: $" + Integer.toString(playerOne.getPlayBet()));
        p1Play.setDisable(true);
        p1Fold.setDisable(true);

        p1Selected = true;
        p1Folded = false;

        checkSelections();

    }

    @FXML private void handleP2Play(ActionEvent event) {
        playerTwo.setPlayBet(playerTwo.getAnteBet());
        P2PlayBetText.setText("Play Wager: $" + Integer.toString(playerTwo.getPlayBet()));
        p2Play.setDisable(true);
        p2Fold.setDisable(true);


        p2Selected = true;
        p2Folded = false;

        checkSelections();
    }

    @FXML private void handleP1Fold(ActionEvent event) {
        playerOne.updateWinnings(-(playerOne.getAnteBet() + playerOne.getPairPlusBet()));
        playerOne.setAnteBet(0);
        P1anteBetText.setText("Ante Bet: $" + Integer.toString(playerOne.getAnteBet()));
        p1Winnings.setText("Total Winnings: $" + Integer.toString(playerOne.getTotalWinnings()));

        p1Play.setDisable(true);
        p1Fold.setDisable(true);

        p1Selected = true;
        p1Folded = true;

        checkSelections();

    }

    @FXML private void handleP2Fold(ActionEvent event) {
        playerTwo.updateWinnings(-(playerTwo.getAnteBet() + playerTwo.getPairPlusBet()));
        playerTwo.setAnteBet(0);
        P2anteBetText.setText("Ante Bet: $" + Integer.toString(playerTwo.getAnteBet()));
        p2Winnings.setText("Total Winnings: $" + Integer.toString(playerTwo.getTotalWinnings()));

        p2Play.setDisable(true);
        p2Fold.setDisable(true);

        p2Selected = true;
        p2Folded = true;

        checkSelections();
    }

    private void checkSelections() {
        if (p1Selected && p2Selected) {
            revealButton.setVisible(true);
            revealButton.setDisable(false);

            p1Selected = false;
            p2Selected = false;
        }
    }

    @FXML void handleRevealDealer(ActionEvent event) throws IOException {

        revealButton.setVisible(false);
        revealButton.setDisable(true);

        dealerCard1.setImage(new Image(getCardImagePath(theDealer.getDealerHand().get(0))));
        dealerCard2.setImage(new Image(getCardImagePath(theDealer.getDealerHand().get(1))));
        dealerCard3.setImage(new Image(getCardImagePath(theDealer.getDealerHand().get(2))));

        evaluateWin();
    }

    private void evaluatePairPlus() {
        // Evaluate Player 1's Pair Plus winnings
        if (playerOne.getPairPlusBet() > 0) { // Check if Player 1 placed a Pair Plus bet
            int p1PPWinnings = ThreeCardLogic.evalPPWinnings(playerOne.getHand(), playerOne.getPairPlusBet());

            if (p1PPWinnings > 0) {
                appendGameInfo("- Player 1 wins Pair Plus: $" + p1PPWinnings);
                playerOne.updateWinnings(p1PPWinnings);
            } else {
                appendGameInfo("- Player 1 loses Pair Plus");
                playerOne.updateWinnings(-playerOne.getPairPlusBet());
            }

            p1Winnings.setText("Total Winnings: $" + Integer.toString(playerOne.getTotalWinnings()));
        }

        // Evaluate Player 2's Pair Plus winnings
        if (playerTwo.getPairPlusBet() > 0) { // Check if Player 2 placed a Pair Plus bet
            int p2PPWinnings = ThreeCardLogic.evalPPWinnings(playerTwo.getHand(), playerTwo.getPairPlusBet());

            if (p2PPWinnings > 0) {
                appendGameInfo("- Player 2 wins Pair Plus: $" + p2PPWinnings);
                playerTwo.updateWinnings(p2PPWinnings);
            } else {
                appendGameInfo("- Player 2 loses Pair Plus");
                playerOne.updateWinnings(-playerTwo.getPairPlusBet());
            }

            p2Winnings.setText("Total Winnings: $" + Integer.toString(playerTwo.getTotalWinnings()));
        }
    }


    // checks who won
    private void evaluateWin() throws IOException {

        evaluatePairPlus();
        ArrayList<Card> dealerHand = theDealer.getDealerHand();
        if (ThreeCardLogic.handQualifies(dealerHand)) {
            dealerHandQualifies = true;
            int p1Res = ThreeCardLogic.compareHands(dealerHand, playerOne.getHand());
            int p2Res = ThreeCardLogic.compareHands(dealerHand, playerTwo.getHand());

            if (p1Res == 1) { // dealer wins
                playerOne.updateWinnings(-(playerOne.getAnteBet()*2));
                appendGameInfo("- Player 1 loses to dealer");
            }

            if (p2Res == 1) {
                playerTwo.updateWinnings(-(playerTwo.getAnteBet()*2));
                appendGameInfo("- Player 2 loses to dealer");
            }

            if (p1Res == 2) { // player wins
                playerOne.updateWinnings((playerOne.getAnteBet()));
                appendGameInfo("- Player 1 beats dealer");
            }

            if (p2Res == 2) {
                playerTwo.updateWinnings((playerTwo.getAnteBet()));
                appendGameInfo("- Player 2 beats dealer");
            }

            if (p1Res == 0) {
                // p1 tie
                appendGameInfo("- Player 1 ties with dealer");
            }

            if (p2Res == 0) {
                // p2 tie
                appendGameInfo("- Player 2 ties with dealer");
            }

            p1Winnings.setText("Total Winnings: $" + Integer.toString(playerOne.getTotalWinnings()));
            p2Winnings.setText("Total Winnings: $" + Integer.toString(playerTwo.getTotalWinnings()));

        }
        else {
            appendGameInfo("- Dealer does not have at least Queen high; ante wager is pushed");
            playerOne.setPlayBet(0);
            playerTwo.setPlayBet(0);
        }

        continueButton.setDisable(false);
        continueButton.setVisible(true);
        continueButton.setOnAction(event -> {
            try {
                continueButton.setDisable(true);
                continueButton.setVisible(false);
                nextHand();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void nextHand() throws IOException {
        // Check if players folded or not, if they did not fold, push their ante bet to the next hand
        int p1AnteBet = 0;
        if (!p1Folded && !dealerHandQualifies) { p1AnteBet = playerOne.getAnteBet(); }

        int p2AnteBet = 0;
        if (!p2Folded && !dealerHandQualifies) { p2AnteBet = playerTwo.getAnteBet(); }

        playerOne.setPlayBet(0);
        playerTwo.setPlayBet(0);

        P1PPBetText.setText("PairPlus Bet: $0");
        P2PPBetText.setText("PairPlus Bet: $0");
        playerOne.setPairPlusBet(0);
        playerTwo.setPairPlusBet(0);

        // Initial card setup
        theDealer.dealDealerHand();
        playerOne.setHand(theDealer.dealHand());
        playerTwo.setHand(theDealer.dealHand());
        cardsDealt = false;
        findCardImages(theDealer.getDealerHand(), playerOne.getHand(), playerTwo.getHand());

        p1Play.setDisable(true);
        p2Play.setDisable(true);
        p1Play.setVisible(false);
        p2Play.setVisible(false);
        p1Selected = false;
        p2Selected = false;

        p1Fold.setDisable(true);
        p1Fold.setVisible(false);
        p2Fold.setDisable(true);
        p2Fold.setVisible(false);

        // Disable Deal Cards button and betting buttons initially
        dealCardsButton.setDisable(true);

        if (!dealerHandQualifies) {
            if (!p1Folded && !p2Folded) {
                dealCardsButton.setDisable(false);
                dealCardsButton.setVisible(true);
            } else if (!p1Folded && p2Folded) {
                p1AnteBetButton.setDisable(true);
                p1AnteBetPlaced = true;
                p2AnteBetButton.setDisable(false);
                p2AnteBetPlaced = false;
            } else if (!p2Folded && p1Folded) {
                p2AnteBetButton.setDisable(true);
                p2AnteBetPlaced = true;
                p1AnteBetButton.setDisable(false);
                p1AnteBetPlaced = false;
            } else {
                p1AnteBetButton.setDisable(false);
                p1AnteBetPlaced = false;
                p2AnteBetButton.setDisable(false);
                p2AnteBetPlaced = false;
            }
        }
        else {
            p1AnteBetButton.setDisable(false);
            p1AnteBetPlaced = false;
            p2AnteBetButton.setDisable(false);
            p2AnteBetPlaced = false;
        }

        dealerHandQualifies = false;

        p1PPButton.setDisable(false);
        p2PPButton.setDisable(false);

        p1BetsVBox.setVisible(true);
        p2BetsVBox.setVisible(true);

        P1anteBetText.setText("Ante Bet: $" + Integer.toString(p1AnteBet));
        P2anteBetText.setText("Ante Bet: $" + Integer.toString(p2AnteBet));
        P1PlayBetText.setText("Play Wager: $0");
        P2PlayBetText.setText("Play Wager: $0");
        p1Winnings.setText("Total Winnings: $" + Integer.toString(playerOne.getTotalWinnings()));
        p2Winnings.setText("Total Winnings: $" + Integer.toString(playerTwo.getTotalWinnings()));
        gameInfoArea.clear();

    }

    public void appendGameInfo(String message) {
        gameInfoArea.appendText(message + "\n");
    }



    // Handle "Exit" menu item action
    @FXML
    private void handleExit() {
        // Create a confirmation alert for exiting
        Alert exitConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        exitConfirmation.setTitle("Exit");
        exitConfirmation.setHeaderText(null);
        exitConfirmation.setContentText("Are you sure you want to quit?");

        // Create custom buttons for the alert
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        // Set the buttons in the alert
        exitConfirmation.getButtonTypes().setAll(yesButton, noButton);

        // Show the alert and wait for the user's response
        exitConfirmation.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                // If 'Yes' is clicked, exit the application
                System.exit(0);
            } else {
                // If 'No' is clicked, do nothing and close the alert
                exitConfirmation.close();
            }
        });
    }

    @FXML
    private void handleFreshStart() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GamePlay.fxml"));
        Parent NewGamePlayRoot = loader.load();

        GamePlayRoot.getScene().setRoot(NewGamePlayRoot);
    }

    // Handle "NewLook" menu item action
    @FXML
    private void handleNewLook() {
        // Clear any existing stylesheets
        GamePlayRoot.getStylesheets().clear();
        GamePlayRoot.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
    }

    // Reset player winnings without modifying the Player class
    private void resetPlayerWinnings() {
        playerOne.updateWinnings(-playerOne.getTotalWinnings());
        playerTwo.updateWinnings(-playerTwo.getTotalWinnings());
    }


}