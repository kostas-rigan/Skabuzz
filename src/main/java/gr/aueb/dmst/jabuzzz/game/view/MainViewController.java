package gr.aueb.dmst.jabuzzz.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import gr.aueb.dmst.jabuzzz.dbconnector.DBConnector;
import gr.aueb.dmst.jabuzzz.entities.Question;
import gr.aueb.dmst.jabuzzz.entities.Score;
import gr.aueb.dmst.jabuzzz.entities.Team;
import gr.aueb.dmst.jabuzzz.game.Main;
import java.util.Timer;

public class MainViewController implements Initializable {

    /**
     * The starting second from which the timer is initiated.
     */
    private static final int INITIAL_SECOND = 5;
    /**
     * Current question in the game.
     */
    private int quest;
    /**
     * Object used to make the connection to the database and fetch
     * the questions.
     */
    private DBConnector dbconnector = new DBConnector();
    /**
     * Represents the score team A has.
     */
    private Score scoreA;
    /**
     * Represents the score team B has.
     */
    private Score scoreB;
    /**
     * Represents the score the current playing team has.
     */
    private Score playingTeamScore;
    /**
     * A Label object that stores the label of current playing team.
     */
    private Label playingTeamScoreArea;
    /**
     * A String representation of currently playing team.
     */
    private String playingTeam;
    /**
     * Used when checking if the playing team has reached the time limit.
     */
    private Timer timer;
    /**
     * Stores the current second in timer functionality.
     */
    private int currentSecond = INITIAL_SECOND;
    /**
     * Wrapper for the seconds remaining to answer.
     */
    private IntegerProperty timeSeconds =
            new SimpleIntegerProperty(INITIAL_SECOND);
    /**
     * Used to implement the on-screen timer in-game.
     */
    private Timeline timeline;
    /**
     * Player-defined points to win.
     * If a team reaches these points, game is over.
     */
    private int pointsToFinish;
    /**
     * Depending on difficulty chosen, there are different
     * number of answers displayed.
     */
    private int numberOfAnswers;
    /**
     * A String representation of the team that won.
     */
    private static String winnerTeam;

    /**
     * It groups all the answer radio buttons together for quicker access.
     */
    @FXML
    private ToggleGroup options;

    /**
     * Button pressed to return to the main menu.
     */
    @FXML
    private Button exitButton;

    /**
     * Shows up in the end of every round to access next question.
     */
    @FXML
    private Button nextButton;

    /**
     * Enabled when a new question shows up, and is responsible for
     * the playing team handling.
     */
    @FXML
    private Button buzzerButton;

    /**
     * A Label where team A's name is displayed on screen.
     */
    @FXML
    private Label teamAArea;

    /**
     * A Label where team B's name is displayed on screen.
     */
    @FXML
    private Label teamBArea;

    /**
     * A Label where team A's score is displayed on screen.
     */
    @FXML
    private Label scoreAArea;

    /**
     * A Label where team B's score is displayed on screen.
     */
    @FXML
    private Label scoreBArea;

    /**
     * Label where the question's description is displayed on screen.
     */
    @FXML
    private Label questionArea;

    /**
     * Radio button for the first answer in order.
     */
    @FXML
    private RadioButton answerA;

    /**
     * Radio button for the second answer in order.
     */
    @FXML
    private RadioButton answerB;

    /**
     * Radio button for the third answer in order.
     */
    @FXML
    private RadioButton answerC;

    /**
     * Radio button for the fourth answer in order.
     */
    @FXML
    private RadioButton answerD;

    /**
     * Radio button for the fifth answer in order.
     */
    @FXML
    private RadioButton answerE;

    /**
     * Shows time left when buzzer is pressed.
     */
    @FXML
    private Label timerLabel;

    /**
     * Displays the team that pressed buzzer button faster.
     */
    @FXML
    private Label plays;

    /**
     * Initializes needed objects for the main game.
     */
    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {
        quest = 0;
        changeTraversability();
        disableButtons();
        numberOfAnswers = GameSetUpController.getNumberOfAnswers();
        editToggleGroup(numberOfAnswers);
        dbconnector.connect();
        loadQuestions();
        Question.shuffleQuestion();
        setNewQA();
        Team teamA = new Team(GameSetUpController.getNameA());
        Team teamB = new Team(GameSetUpController.getNameB());

        scoreA = new Score();
        scoreB = new Score();

        teamAArea.setText(teamA.getTeamName());
        teamBArea.setText(teamB.getTeamName());

        scoreAArea.setText(scoreA.toString());
        scoreBArea.setText(scoreB.toString());

        timerLabel.textProperty().bind(timeSeconds.asString());
        timer = new Timer();

        pointsToFinish = GameSetUpController.getFinishPoints();
    }

    private void editToggleGroup(final int numbOfAnswers) {
        final int answersForEasy = 3;
        final int answersForNormal = 4;
        if (numbOfAnswers == answersForEasy) {
            answerD.setToggleGroup(null);
            answerD.setOpacity(0);
            answerE.setToggleGroup(null);
            answerE.setOpacity(0);
        } else if (numbOfAnswers == answersForNormal) {
            answerE.setToggleGroup(null);
            answerE.setOpacity(0);
        }

    }

    /**
     * handleBuzzer method initiates when a team presses their key. It
     * controls the flow of the game regarding to the question answered.
     *
     * @param keyEvent the first key pressed by either team.
     */
    @FXML
    public void handleBuzzer(final KeyEvent keyEvent)
            throws InterruptedException {
        if (keyEvent.getCode() == KeyCode.A
                || keyEvent.getCode() == KeyCode.L) {
            exitButton.requestFocus();
            if (keyEvent.getCode() == KeyCode.A) {
                playingTeamScore = scoreA;
                playingTeamScoreArea = scoreAArea;
                playingTeam = teamAArea.getText();
            } else {
                playingTeamScore = scoreB;
                playingTeamScoreArea = scoreBArea;
                playingTeam = teamBArea.getText();
            }

            plays.setText("Παίξε " + playingTeam);
            plays.setOpacity(1);
            Label[] labels = {teamAArea, teamBArea};
            timer = new Timer();
            initiateTimer();
            enableButtons();
            controlTimeUp();
        }
    }

    /**
     * Initiates actions when the playing team answers the question.
     *
     * Stops the timer and disables the answer buttons, checks if
     * response is correct or wrong and evaluates if the team won
     * (reached maximum points) or lost(reached -5), giving the win
     * to the other team.
     *
     * @throws InterruptedException
     */
    @FXML
    public void onAnswerGiven() throws InterruptedException {
        disableButtons();
        stopTimer();
        checkAnswer();
        showCorrectAnswer();
        nextButton.setOpacity(1);
        nextButton.setDisable(false);
        checkGameOver();
    }

    @FXML
    private void returnToMainMenu() throws IOException {
        new Main().showMainMenu();
    }

    /**
     * Changes to the next question after a round is finished.
     *
     * @param event the button pressed to advance to the next round
     */
    @FXML
    void setNextQuestion(final ActionEvent event) {
        timeSeconds.set(INITIAL_SECOND);
        quest++;
        if (quest == Question.getNumberOfQuestions()) {
            quest = 0;
            Question.shuffleQuestion();
        }
        setNewQA();
        nextButton.setOpacity(0);
        nextButton.setDisable(true);
        plays.setOpacity(0);
        unselectButton();
        resetRadioButtonBGColour();
    }

    /**
     * @return a String representation of the winner team.
     */
    public static String getWinnerTeam() {
        return winnerTeam;
    }

    /*
     * Initiates count down when either team has pressed their
     *  respective key to answer the question.
     */
    private void initiateTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        timeSeconds.set(INITIAL_SECOND);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration
                .seconds(INITIAL_SECOND + 1), new KeyValue(timeSeconds, 0)));
        timeline.playFromStart();
    }

    /*
     * changeTraversability disables focus that was initially on the
     * wrong buttons, and initilises it to the buzzerButton object.
     */
    private void changeTraversability() {
        exitButton.setFocusTraversable(false);
        for (Iterator<Toggle> iterator = options.getToggles().iterator();
                iterator.hasNext();) {
            RadioButton radioButton = (RadioButton) iterator.next();
            radioButton.setFocusTraversable(false);
        }
        buzzerButton.setFocusTraversable(true);
    }

    // disables radio buttons
    private void disableButtons() {
        changeButtonStatus(true);
    }

    // enable radio buttons
    private void enableButtons() {
        changeButtonStatus(false);

    }

    /*
     * Depending on the boolean value, it disables(true) or enables(false)
     * the radio buttons on screen.
     */
    private void changeButtonStatus(final boolean a) {
        for (Iterator<Toggle> iterator = options.getToggles().iterator();
                iterator.hasNext();) {
            RadioButton radioButton = (RadioButton) iterator.next();
            radioButton.setDisable(a);
        }
    }

    /*
     * Controls actions used when timer's count down is over.
     * Makes the proper changes to the interface when the playing team
     * hasn't answered and checks if there is a game over condition.
     */
    private void timeIsUp() {
        disableButtons();
        showCorrectAnswer();
        currentSecond = INITIAL_SECOND;
        playingTeamScore.wrongAnswer();
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                playingTeamScoreArea.setText(playingTeamScore.toString());
            }
        });
        nextButton.setOpacity(1);
        nextButton.setDisable(false);
        checkGameOver();
    }

    /*
     * When the team answers a question, their answer is checked to see if
     * it was correct or wrong and alters their score accordingly.
     */
    private void checkAnswer() {
        RadioButton button = (RadioButton) options.getSelectedToggle();
        String correctAnswer = Question.getCorrectAnswer(quest);
        if (button.getText().equals(correctAnswer)) {
            playingTeamScore.correctAnswer();
        } else {
            playingTeamScore.wrongAnswer();
            changeBackgroundColor("#f17b8f", button);
        }
        playingTeamScoreArea.setText(playingTeamScore.toString());
    }

    /*
     * At the beginning of each turn loads a question
     * from the list and displays it on screen.
     */
    private void setNewQA() {
        int answer = 0;
        for (Iterator<Toggle> iterator = options.getToggles().iterator();
                iterator.hasNext();) {
            RadioButton radioButton = (RadioButton) iterator.next();
            radioButton.setText(Question.getAnswer(quest, answer));
            answer++;
        }
        questionArea.setText(Question.getQuestions(quest));
    }

    /*
     * Cancels current timeline object count down
     * if the playing team has answered their question.
     */
    private void stopTimer() {
        if (options.getSelectedToggle() != null) {
            timeline.stop();
        }
        currentSecond = INITIAL_SECOND;
    }

    /*
     * Shows to both teams which was the correct answer either responded or not.
     * On screen it is highlighted with a green colour.
     * #b3f17b -> in hexadecimal system it is a greenish colour.
     */
    private void showCorrectAnswer() {
        for (Iterator<Toggle> iterator = options.getToggles().iterator();
                iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            if (button.getText().equals(Question.getCorrectAnswer(quest))) {
                changeBackgroundColor("#b3f17b", button);
            }
        }
    }

    /*
     * Given a colour and a radio button,
     * it highlights the background colour of the button,
     * sharpening a bit the corners giving a more round look.
     */
    private void changeBackgroundColor(final String color,
            final RadioButton button) {
        final double cornerRadius = 0.1;
        Background background = new Background(
                new BackgroundFill(Paint.valueOf(color),
                        new CornerRadii(cornerRadius, true), null));
        button.setBackground(background);
    }

    /*
     * Loads questions from database,
     * depending on categories given in game setup.
     */
    private void loadQuestions() {
        ArrayList<String> categoryNames = GameSetUpController.categoryNames();
        ArrayList<Integer> questIds = new ArrayList<Integer>();
        for (String category : categoryNames) {
            questIds.addAll(dbconnector.selectQuestionId(category));
        }

        for (int i = 0; i < questIds.size(); i++) {
            /*
             * Check if correct answer is in first numberOfAnswers array cells.
             * If it is then it will continue.
             * Otherwise it will remove the question and re add it.
             */
             while (true) {

                new Question(dbconnector.selectQuestion(questIds.get(i)));
                String currentCorrectAnswer = Question.getCorrectAnswer(i);
                ArrayList<String> currentQuestionAnswers =
                        Question.getAnswers(i);

                int index = currentQuestionAnswers
                        .indexOf(currentCorrectAnswer);

                if (index < numberOfAnswers) {
                    break;
                }

                Question.popQuestion();
             }
        }
    }

    /*
     * Handles the event of timer finishing its count down without a question
     * being answered.
     */
    private void controlTimeUp() {
        final long delay = 500;
        final long period = 1000;
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                if (currentSecond > 0) {
                    currentSecond--;
                } else {
                    if (timerLabel.getText().equals("0")) {
                        timeIsUp();
                    }
                    timer.cancel();
                }
            }
        }, delay, period);
    }

    /**
     * After an answer is given, it clears the selection of every button.
     */
    private void unselectButton() {
        for (Iterator<Toggle> iterator = options.getToggles().iterator();
                iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            button.setSelected(false);
        }
    }

    /*
     * After each turn has passed,
     * it clears the background colour of every radio button.
     * Loop is used because when a wrong answer is given,
     * there are two radio buttons with a background colour.
     */
    private void resetRadioButtonBGColour() {
        for (Iterator<Toggle> iterator = options.getToggles().iterator();
                iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            Background background =
                    new Background(new BackgroundFill(null, null, null));
            button.setBackground(background);
        }
    }

    /*
     * Check if the playing team has reached maximum points or lowest points
     * and determine the winner team.
     */
    private void checkGameOver() {
        final int losingPoints = -5;
        if (playingTeamScore.getTeamsScore() == pointsToFinish) {
            hideAndDisableInWinner();
            inititateEndOfGame(playingTeam);
        } else if (playingTeamScore.getTeamsScore() == losingPoints) {
            hideAndDisableInWinner();
            if (playingTeam.equals(teamAArea.getText())) {
                inititateEndOfGame(teamBArea.getText());
            } else {
                inititateEndOfGame(teamAArea.getText());
            }
        }
    }

    /*
     * Show the winning team, which is given by a String representation.
     */
    private void inititateEndOfGame(final String winTeam) {
        winnerTeam = winTeam;
        Question.cleanQuestions();
        try {
            new Main().showEndOfGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Hide every unnecessary object when a winning team has been determined.
     */
    private void hideAndDisableInWinner() {
        for (Iterator<Toggle> iterator = options.getToggles().iterator();
                iterator.hasNext();) {
            RadioButton button = (RadioButton) iterator.next();
            button.setOpacity(0);
        }
        scoreAArea.setOpacity(0);
        scoreBArea.setOpacity(0);
        teamAArea.setOpacity(0);
        teamBArea.setOpacity(0);
        timerLabel.setOpacity(0);
        nextButton.setDisable(true);
        nextButton.setOpacity(0);
    }
}
