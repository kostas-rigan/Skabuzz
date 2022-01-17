package testClasses;

import gr.aueb.dmst.jabuzzz.entities.Score;
import org.junit.Assert;
import org.junit.Test;

/*
 * The Class testScore is created to test the functions of the class Score.
 * The class checks the initialization and the teams score going up and down by one point.
 */
public class testScore {
  Score score = new Score();
  
  /*
 * Class testScoreInitialization checks if the initialization of the teams 
 * score is correct in the constructor, if not returns false message.
 */
  @Test
  public void testScoreInitialization() {
    Assert.assertEquals("failure - wrong initialization of score", score.getTeamsScore(), 0);
  }
  
  /*
 * Class testPointsChanging checks if the method 
 * correctAnswer is changing the score correctly by +1
 * and the method wrongAnswer is changing the score 
 * correctly by -1, if not returns false message.
 */
  @Test
  public void testPointsChanging() {
    score.correctAnswer();
    Assert.assertEquals("failure - wrong in method correctAnswer"
        + " wrong Score", score.getTeamsScore(), 1);
    score.wrongAnswer();
    Assert.assertEquals("failure - wrong in method "
        + "wrongAnswer wrong Score", score.getTeamsScore(), 0);
  }
}
