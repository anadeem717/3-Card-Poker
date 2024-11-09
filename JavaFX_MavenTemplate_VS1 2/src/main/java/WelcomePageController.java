import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomePageController {

    @FXML
    private void handlePlayGame(ActionEvent event) {
        // Logic to transition to the gameplay screen
        try {
            // Load the gameplay FXML
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("GamePlay.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene gameplayScene = new Scene(loader.load());
            stage.setScene(gameplayScene);
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
