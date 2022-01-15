package gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import gr.aueb.dmst.jabuzzz.entities.Category;
import gr.aueb.dmst.jabuzzz.entities.Difficulty;
import gr.aueb.dmst.jabuzzz.game.Main;

public class GameSetUpController implements Initializable {

    /**
     * The String representation of the difficulty.
     */
    private static String diffLevel;
    /**
     * The total points both teams choose to winning limit.
     */
    private static int goal;
    /**
     * Enum that holds information of the difficulty.
     */
    private static Difficulty level = null;
    /**
     * Name of team A.
     */
    private static String nameA;
    /**
     * Name of team B.
     */
    private static String nameB;
    /**
     * A Category object for geography.
     */
    private static Category geo;
    /**
     * A Category object for history.
     */
    private static Category hist;
    /**
     * A Category object for mythology.
     */
    private static Category myth;

    /**
     * A text field for team A to enter their custom name.
     */
    @FXML
    private TextField teamAField;

    /**
     * A text field for team B to enter their custom name.
     */
    @FXML
    private TextField teamBField;

    /**
     * Check box where the teams decide if they want to play
     * mythology questions.
     */
    @FXML
    private CheckBox mythology;

    /**
     * Check box where the teams decide if they want to play
     * geography questions.
     */
    @FXML
    private CheckBox geography;

    /**
     * Check box where the teams decide if they want to play
     * history questions.
     */
    @FXML
    private CheckBox history;

    /**
     * Slider where players choose in how many points the game finishes.
     */
    @FXML
    private Slider pointsToFinish;

    /**
     * Players can choose the difficulty of the game.
     */
    @FXML
    private ChoiceBox<String> difficulty;

    /**
     * Checks parameters of the game settings and
     * changes to the main game's view.
     * @throws IOException
     */
    @FXML
    public void start() throws IOException {
        nameA = teamAField.getText();
        nameB = teamBField.getText();
        goal = (int) pointsToFinish.getValue();
        myth.setSelected(mythology.isSelected());
        geo.setSelected(geography.isSelected());
        hist.setSelected(history.isSelected());
        diffLevel = difficulty.getValue();
        setDifficultyOfGame();

        if (myth.getSelected() && geo.getSelected() && hist.getSelected()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText(
                    "Επιλέξτε κατηγορία και... Καλή Διασκέδαση!");
            alert.show();
        } else {
            letsGo();
        }

    }

    @FXML
    private void goBack() throws IOException {
        new Main().showMainMenu();
    }

    private void letsGo() throws IOException {
        new Main().showMainView();
    }

    /**
     * @return name of Team A
     */
    public static String getNameA() {
        return nameA;
    }

    /**
     * @return name of Team B
     */
    public static String getNameB() {
        return nameB;
    }

    /**
     * Initialises objects used on this display.
     */
    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {
        difficulty.getItems().addAll("Εύκολο", "Κανονικό", "Δύσκολο");
        difficulty.setValue("Κανονικό");
        teamAField.setText("Ομάδα Α");
        teamBField.setText("Ομάδα Β");
        geo = new Category("Geography");
        hist = new Category("History");
        myth = new Category("Mythology");
    }

    /**
     * method that creates the difficulty object.
     */
    public void setDifficultyOfGame() {
        if (diffLevel.equals("Εύκολο")) {
            level = Difficulty.EASY;
        } else if (diffLevel.equals("Κανονικό")) {
            level = Difficulty.NORMAL;
        } else {
            level = Difficulty.HARD;
        }

    }

    /**
     * @return the points to be reached for a team to win.
     */
    public static int getFinishPoints() {
        return goal;
    }

    /**
     * Examines all of the categories if they are selected, in which case
     * they are added in an array list and are returned to the user.
     * @return an ArrayList of String that contains the names of selected
     * categories.
     */
    public static ArrayList<String> categoryNames() {
        ArrayList<String> catNames = new ArrayList<String>();
        if (geo.getSelected()) {
            catNames.add(geo.getCategoryName());
        }
        if (hist.getSelected()) {
            catNames.add(hist.getCategoryName());
        }
        if (myth.getSelected()) {
            catNames.add(myth.getCategoryName());
        }

        return catNames;
    }

    /**
     * Depending on the difficulty of game changes the number of answers.
     * @return the number of answers to be displayed
     */
    public static int getNumberOfAnswers() {
        final int answersForEasy = 3;
        final int answersForNormal = 4;
        final int answersForHard = 5;
        switch (level) {
        case EASY:
            return answersForEasy;
        case NORMAL:
            return answersForNormal;
        default:
            return answersForHard;
        }
    }
}
