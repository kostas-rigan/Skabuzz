package gr.aueb.dmst.jabuzzz.utilities;

import javafx.application.Platform;
import javafx.scene.control.Label;
import java.util.TimerTask;

/**
 * Timer class(different from java.lang.Timer) is used to function the timer
 * utility used from the buzz method of a Buzzer object.
 */
public class Timer extends java.util.Timer {
    private boolean notInterrupted;
    private Label timerLabel;
    /**
     * STARTING_SECOND is the initial value of currentSecond field, which is the 5th
     * second.
     */
    private static final int STARTING_SECOND = 5;
    /**
     * currentSecond is time(in seconds) that is left in timer. Used after buzzer is
     * pressed, to track the time the playing team has to answer the question.
     */
    private int currentSecond = STARTING_SECOND;
    /**
     * DELAY is time(in milliseconds) that timer has to wait until it commences its
     * action.
     */
    private static final long DELAY = 1000; // in milliseconds
    /**
     * PERIOD is time(in milliseconds) timer has to wait to continue its actions.
     * 1000 milliseconds = 1 second.
     */
    private static final long PERIOD = 1000; // in milliseconds

    public Timer(Label timerLabel) {
        this.timerLabel = timerLabel;
        notInterrupted = true;
    }


    /**
     * stopTimer method interupts the procedure in startTimer method before the
     * timer ends.
     */
    public void stopTimer() {
        notInterrupted = false;
    }

    public void resetNotInterrupted() {
        notInterrupted = true;
    }

    public void resetInitialSecond() {
        currentSecond = STARTING_SECOND;
    }
}
