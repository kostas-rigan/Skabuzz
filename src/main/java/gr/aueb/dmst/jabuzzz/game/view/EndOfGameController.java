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
	   
		@FXML
	    private Label winnerName;

	    @FXML
	    private Button playAgain;

	    @FXML
	    private Button Exit;

	    @FXML
	    void ExitGame() {
	    	System.exit(0);
	    }

	    @FXML
	    void GoToGameSetUp() throws IOException {
	        new Main().showGameSetUp();
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		    winnerName.setText(MainViewController.getWinnerTeam());
		}
}

