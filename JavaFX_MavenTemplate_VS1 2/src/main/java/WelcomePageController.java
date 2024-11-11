import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class WelcomePageController implements Initializable {

    @FXML
    private Pane WelcomeRoot;

    @FXML
    private Pane GamePlayRoot;

    // Player and Dealer objects (adjust these to match your actual classes)
    private Player playerOne;
    private Player playerTwo;
    private Dealer theDealer;

    public void setPlayersAndDealer(Player playerOne, Player playerTwo, Dealer theDealer) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.theDealer = theDealer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void handlePlayGame(ActionEvent event) {
        // Transition to the gameplay screen
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GamePlay.fxml"));
            Parent GamePlayRoot = loader.load();

            // Get the GamePlayController instance
            GamePlayController controller = loader.getController();

            // Pass players and dealer to the GamePlayController
            controller.setPlayersAndDealer(playerOne, playerTwo, theDealer);

            // Switch to the GamePlayRoot
            WelcomeRoot.getScene().setRoot(GamePlayRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        // Close the application
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
