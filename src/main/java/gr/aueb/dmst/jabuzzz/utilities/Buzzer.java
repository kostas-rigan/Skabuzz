package gr.aueb.dmst.jabuzzz.utilities;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Buzzer is the class that implements the main game's utility of deciding which
 * team plays.
 * <p>
 * In question scene a Buzzer object is used for teams to press it and be able
 * to answer the question.
 * 
 * @version 1.0 20/11/2021
 */
public class Buzzer {
    /**
     * Checks which one of the teams is going to answer the question.
     * <p>
     * This method will not execute any action if any key other than A or L is
     * pressed. When a correct key is pressed, this method will initiate the next
     * scene of the game, where the playing team is going to answer the question.
     * 
     * @param keyCode
     * @param displays
     * @param primaryStage
     */
    public void buzz(final KeyCode keyCode, final Label[] displays) {
        FontWeight fontWeight = FontWeight.BOLD;
        switch (keyCode) { // inspecting key code
        case A:
            displays[0]
                    .setFont(Font.font(displays[0].getFont().getFamily(), fontWeight, displays[0].getFont().getSize()));
            // sets team A's font weight to bold
            break;
        case L:
            displays[1]
                    .setFont(Font.font(displays[1].getFont().getFamily(), fontWeight, displays[1].getFont().getSize()));
            // sets team B's font weight to bold
            break;
        default:
            break;
        }
    }
}
