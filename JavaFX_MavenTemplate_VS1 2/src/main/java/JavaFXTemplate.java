// todo header



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;


public class JavaFXTemplate extends Application {


	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/FXML/WelcomePage.fxml"));
		Scene scene = new Scene(root, 1000, 700);
		primaryStage.setTitle("3 Card Poker");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
