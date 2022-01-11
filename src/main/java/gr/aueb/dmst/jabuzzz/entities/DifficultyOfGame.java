package gr.aueb.dmst.jabuzzz.entities;

public class DifficultyOfGame {
    // number of answer
    private static int numberOfAnswers;

    public static enum Difficulty {
        EASY, NORMAL, HARD
    }

    static Difficulty difficulty;

    // constructor to select difficulty of game(Difficulty obj1 = Difficulty.NORMAL)
    public DifficultyOfGame(Difficulty diff) {
        difficulty = diff;
    }

    public static void handleDifficulty() {
        switch (difficulty) {
        case EASY:
            numberOfAnswers = 3;
            break;
        case NORMAL:
            numberOfAnswers = 4;
            break;
        case HARD:
            numberOfAnswers = 5;
            break;
        }

    }
}
