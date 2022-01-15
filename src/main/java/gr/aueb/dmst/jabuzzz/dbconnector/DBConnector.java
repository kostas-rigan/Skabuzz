package gr.aueb.dmst.jabuzzz.dbconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Responsible for the connection to the database and the question loading
 *  to main game.
 */
public class DBConnector {
    /**
     * Stores the connection to the DataBase.
     */
    private Connection conn;

    /**
     * @return DataBase Connection Object
     */
    public Connection getConnection() {
        return this.conn;
    }

    /**
     * Makes the connection to the database.
     * This method when called is connecting the game to the
     * SQLite DataBase.
     */
    public void connect() {

        String url = "jdbc:sqlite:classes/database/DATA_BASE.sqlite";

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * This method fetches a question from the database given
     * a question id number.
     *
     * @param id an integer that represents a question wanted from DB
     * @return an array of 6 elements containing the question description,
     * and its five answers.
     */
    public String[] selectQuestion(final int id) {
        String sql = String.format(
                "SELECT Description, Right_answer,"
                + " Wrong_answer1, Wrong_answer2, Wrong_answer3,"
                + " Wrong_answer4 FROM Questions WHERE Question_id = %d", id);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            final int arraySize = 6;
            String[] question = new String[arraySize];
            // loop through the result set
            while (rs.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    question[i - 1] = (String) rs.getObject(i);
                }

            }
            return question;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Returns every question id that corresponds to a certain category.
     * @param category the category from which to fetch the ids.
     * @return an integer ArrayList containing every question id of given
     *  category.
     */
    public ArrayList<Integer> selectQuestionId(final String category) {
        ArrayList<Integer> ids = new ArrayList<Integer>();

        String sql = String.format("SELECT Question_id FROM Questions "
                + "WHERE Category = '%s'", category);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // loop through the result set
            while (rs.next()) {
                ids.add((Integer) rs.getObject(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return ids;
    }
}
