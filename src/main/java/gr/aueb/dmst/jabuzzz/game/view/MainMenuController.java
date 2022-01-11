package gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;

import javafx.fxml.FXML;
import gr.aueb.dmst.jabuzzz.game.Main;

public class MainMenuController {

    @FXML
    private void Play() throws IOException {
        new Main().showGameSetUp();
    }

    @FXML
    private void Instructions() throws IOException {
        new Main().showInstructions();
    }

    @FXML
    private void Exit() {
        System.exit(0);
    }

}
