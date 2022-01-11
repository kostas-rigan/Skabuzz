package gr.aueb.dmst.jabuzzz.game.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import gr.aueb.dmst.jabuzzz.game.Main;

public class InstructionsController {

    @FXML
    private Button play;

    @FXML
    private Button back;

    @FXML
    void GoBack(ActionEvent event) throws IOException {
        new Main().showMainMenu();
    }

    @FXML
    void Play(ActionEvent event) throws IOException {
        new Main().showGameSetUp();
    }

}
