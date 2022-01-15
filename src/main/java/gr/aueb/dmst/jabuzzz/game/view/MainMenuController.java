package gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;

import javafx.fxml.FXML;
import gr.aueb.dmst.jabuzzz.game.Main;

public class MainMenuController {

    /**
     * Initiates the game set up window.
     * @throws IOException
     */
    @FXML
    private void play() throws IOException {
        new Main().showGameSetUp();
    }

    /**
     * Initiates the instructions menu.
     * @throws IOException
     */
    @FXML
    private void instructions() throws IOException {
        new Main().showInstructions();
    }

    /**
     * Exits the game.
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

}
