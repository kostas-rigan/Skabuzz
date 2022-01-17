package testClasses;

import gr.aueb.dmst.jabuzzz.entities.Question;
import org.junit.Assert;
import org.junit.Test;

/*
 * this Class testQuestion is created to test the functions of the class Question.
 * First the questions are initialized and then the methods control the functions.
 */
public class testQuestion {
  String[] a = new String[6];
  
  {
    a[0] = "Ερωτηση";
    a[1] = "Σωστο";
    a[2] = "Λαθος1";
    a[3] = "Λαθος2";
    a[4] = "Λαθος3";
    a[5] = "Λαθος4";
  }
 
  Question q = new Question(a);

  /*
 * Class testGetCorrectAnswer checks if method getCorrectAnswer returns
 *  the correct answer, if not returns false message.
 */
  @Test
public void testGetCorrectAnswer() {
    Assert.assertEquals("failure - wrong in method"
        + " getCorrectAnswer returns false Answer", q.getCorrectAnswer(0),
             "Σωστο");
  }

  /*
  * Class testGetQuestions checks if method getQuestions returns
  *  the correct answer, if not returns false message.
  */
  @Test
public void testGetQuestions() {
    Assert.assertEquals("failure - wrong in method "
        + "getQuestions returns false question", q.getQuestions(0),
            "Ερωτηση");
  }
  
  /*
  * Class testLengthOfLists checks if the size 
  * of the lists is correct, if not returns false message.
  */
  @Test
  public void testLengthOfLists() {
    Assert.assertEquals("failure - wrong in List questions doesnt "
        + "have the correct size", Question.questions.size(), 1);
    Assert.assertEquals("failure - wrong in List answer doesnt"
        + " have the correct size", Question.answer.size(), 1);
    Assert.assertEquals("failure - wrong in List answer doesnt have the correct size",
        Question.answer.get(0).size(), 5);
    Assert.assertEquals("failure - wrong in List correctAnswer doesnt have the correct size",
        Question.correctAnswer.size(), 1);
  }

  /*
  * Class testPopQuestion checks if method popQuestion removes correctly
  * a question from list question, if not returns false message.
  */
  @Test
  public void testPopQuestion() {
    q.popQuestion();
    Assert.assertEquals("failure - wrong in method popQuestion", q.getNumberOfQuestions(), 0);
  }

}