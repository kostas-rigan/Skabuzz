package gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import gr.aueb.dmst.jabuzzz.game.Main;

public class EndOfGameController implements Initializable {

    /**
     * The Label objetc that will display which team won.
     */
    @FXML
    private Label winnerName;

    /**
     * Button used when teams want to play again.
     */
    @FXML
    private Button playAgain;

    /**
     * Will exit from the game completely.
     */
    @FXML
    private Button exit;

    /**
     * Exits the game.
     */
    @FXML
    public void exitGame() {
        System.exit(0);
    }

    /**
     * Returns to the game set up menu.
     * @throws IOException
     */
    @FXML
    public void goToGameSetUp() throws IOException {
        new Main().showGameSetUp();
    }

    /**
     * Initializes the name of the team that won.
     */
    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {
        winnerName.setText(MainViewController.getWinnerTeam());
    }
}
