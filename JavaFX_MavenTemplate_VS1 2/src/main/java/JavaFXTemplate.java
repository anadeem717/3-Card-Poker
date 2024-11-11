import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class JavaFXTemplate extends Application {

	private Player playerOne;
	private Player playerTwo;
	private Dealer theDealer;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Initialize players and dealer
		playerOne = new Player();
		playerTwo = new Player();
		theDealer = new Dealer();

		// Load FXML and set controller
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/WelcomePage.fxml"));
		Parent root = loader.load();

		// Get the WelcomePageController instance
		WelcomePageController controller = loader.getController();

		// Pass player and dealer instances to WelcomePageController
		controller.setPlayersAndDealer(playerOne, playerTwo, theDealer);

		// Set the scene
		Scene scene = new Scene(root, 1000, 700);
		primaryStage.setTitle("3 Card Poker");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
