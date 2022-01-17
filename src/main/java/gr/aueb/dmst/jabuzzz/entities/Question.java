package gr.aueb.dmst.jabuzzz.entities;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Question takes an String Array with the question in the first place and
 * the answers to the following positions shuffles it and give back the question
 * and the possible answers.
 *
 * @version 1.0 11/12/2021
 */
public class Question {
  /**
     * An ArrayList of Strings that contains every question's correct answer.
     */
  public static ArrayList<String> correctAnswer = new ArrayList<String>();
  /**
     * An ArrayList of Strings storing question descriptions.
     */
  public static ArrayList<String> questions = new ArrayList<String>();
  /**
     * An ArrayList of ArrayList of Strings containing all possible answers of
     * questions.
     */
  public static ArrayList<ArrayList<String>> answer =
            new ArrayList<ArrayList<String>>();

  /**
     * How many questions are loaded from the database.
     */
  private static int numberOfQuestions = 0;

  /**
     * Class constructor taking as parameter an Array of String specifying the
     * question and the answers.
     * The String parameter has in the first place the question and
     * the remaining places has the answers.Every time the first of
     *  the answers is the correct.
     * * @param questionAndAnswers
     */
  public Question(final String[] questionAndAnswers) {
    questions.add(questionAndAnswers[0]);

    /*
         * 
         * initialization of the Array answer
         */
    ArrayList<String> temp2 = new ArrayList<String>();
    for (int i = 1; i < questionAndAnswers.length; i++) {
      temp2.add(questionAndAnswers[i]);
    }
    Question.answer.add(temp2);

    /*
         * initialization of the correctAnswer
         */
    Question.correctAnswer.add(questionAndAnswers[1]);
    numberOfQuestions++;
    shuffleAnswers();
  }

  /*
     * shuffleArray shuffles the Array of the answers so the correct answer is
     * not always in the first place. uses the Fisher–Yates shuffle algorithm.
     */
  private static void shuffleAnswers() {
    int index;
    String temp;
    Random random = new Random();
    for (int i = answer.get(numberOfQuestions - 1).size() - 1; i > 0; i--) {
      index = random.nextInt(i + 1);
      temp = answer.get(numberOfQuestions - 1).get(index);
      answer.get(numberOfQuestions - 1)
            .set(index, answer.get(numberOfQuestions - 1).get(i));
      answer.get(numberOfQuestions - 1).set(i, temp);
    }
  }

  /**
     * findAnswer is called after you shuffle the array so you can find in witch
     * spot is the correct answer.
     *
     * @return the position of the correct answer.
     */
  public int findAnswer() {
    final int maxAnswers = 5;
    for (int i = 0; i < maxAnswers; i++) {
      if (Question.answer.get(numberOfQuestions - 1).get(i)
                    == correctAnswer.get(numberOfQuestions - 1)) {
        return i;
      }
    }
    return -1;
  }

  /**
     * shuffleArray shuffles the static Array in witch the questions is stored
     * and that affects the array of the answers follows so the analogy
     * isn't lost.
     * uses the Fisher–Yates shuffle algorithm
     */
  public static void shuffleQuestion() {
    int index;
    String temp;

    Random random = new Random();
    for (int i = numberOfQuestions - 1; i > 0; i--) {
      ArrayList<String> temp2 = new ArrayList<String>();
      index = random.nextInt(i + 1);
      temp = questions.get(index);
      questions.set(index, questions.get(i));
      questions.set(i, temp);
      temp2.addAll(answer.get(index));
      answer.set(index, answer.get(i));
      answer.set(i, temp2);
      temp = correctAnswer.get(index);
      correctAnswer.set(index, correctAnswer.get(i));
      correctAnswer.set(i, temp);
    }
  }

  /**
     * getQuestions returns and removes the last question of the list.
     *
     * @param questionIndex the index of the question asked
     * @return r witch is the last question.
     */
  public static String getQuestions(final int questionIndex) {
    String r = questions.get(questionIndex);
    return r;
  }

  /**
   * * @return the number of questions loaded from database.
   */
  public static int getNumberOfQuestions() {
    return numberOfQuestions;
  }

  /**
     * popQuestions removes the latest
     * added question with its answers and correct answer.
     */
  public static void popQuestion() {
    questions.remove(questions.size() - 1);
    answer.remove(answer.size() - 1);
    correctAnswer.remove(correctAnswer.size() - 1);
    numberOfQuestions--;
  }

  /**
     * Returns a specified answer of a certain question.
     *  * @param questionIndex
     *  * @param answerIndex
     *  * @return the answer asked.
     */
  public static String getAnswer(final int questionIndex, final int answerIndex) {
    return answer.get(questionIndex).get(answerIndex);
  }

  /**
     * Returns all answers of a certain question.
     * * @param questionIndex
     * * @return the answer asked.
     */
  public static ArrayList<String> getAnswers(final int questionIndex) {
    return answer.get(questionIndex);
  }
  
  /*
     * This method returns the correct answer of a specified question.
     * * @param questionIndex
     * * @return a String containing the correct answer of a question
     */
  public static String getCorrectAnswer(final int questionIndex) {
    return correctAnswer.get(questionIndex);
  }

  /**
     * When the game ends this method is called to clean the lists,
     * and make them equal to null.
     */
  public static void cleanQuestions() {
    correctAnswer.clear();
    questions.clear();
    answer.clear();
    numberOfQuestions = 0;
  }
}
