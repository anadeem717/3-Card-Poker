import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

public class WelcomePageController implements Initializable {

    @FXML
    private Pane WelcomeRoot;

    @FXML
    private Pane GamePlayRoot;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    private void handlePlayGame(ActionEvent event) {
        // Transition to the gameplay screen
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GamePlay.fxml"));
            Parent GamePlayRoot = loader.load();

            WelcomeRoot.getScene().setRoot(GamePlayRoot);

        }

        catch (Exception e) {
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
