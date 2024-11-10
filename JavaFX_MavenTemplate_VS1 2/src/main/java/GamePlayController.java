import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.image.*;

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
        dealerCard1.setImage(new Image("/CardImages/3_of_spades.png"));
    }

}
