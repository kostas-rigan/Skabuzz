package gr.aueb.dmst.jabuzzz.game.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import gr.aueb.dmst.jabuzzz.game.Main;

public class InstructionsController {

    /**
     * Button to go immediately to the game set up menu.
     */
    @FXML
    private Button play;

    /**
     * Button to go back to the main menu.
     */
    @FXML
    private Button back;

    /**
     * Return to the main menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void goBack(final ActionEvent event) throws IOException {
        new Main().showMainMenu();
    }

    /**
     * Go to game setup.
     * @param event
     * @throws IOException
     */
    @FXML
    void play(final ActionEvent event) throws IOException {
        new Main().showGameSetUp();
    }

}
