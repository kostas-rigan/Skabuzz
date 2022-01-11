package gr.aueb.dmst.jabuzzz.entities;

/**
 * Class Score changes the team score and checks if the top or bottom target has
 * been reached.
 * 
 * @version 1.0 25/11/2021
 */
public class Score {
    /*
     * teamsScore stores the current teams score.
     */
    private int teamsScore;
    /*
     * Class constructor specifying teams target.
     * 
     * @param target
     */
    public Score() {
        teamsScore = 0;
    }

    /*
     * correctAnswer is called when a team has given a correct answer and increases
     * the teams score by one.
     */
    public void correctAnswer() {
        teamsScore += 1;
    }

    /*
     * wrongAnswer is called when a team has given a wrong answer and decreases the
     * teams score by one.
     */
    public void wrongAnswer() {
        teamsScore -= 1;
    }

    /**
     * @return teamScore value.
     */
    public int getTeamsScore() {
        return teamsScore;
    }

    @Override
    public String toString() {
        return "Score:" + teamsScore;
    }
}
