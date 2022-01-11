package gr.aueb.dmst.jabuzzz.dbconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnector {
    private Connection conn;

    public void connect() {

        String url = "jdbc:sqlite:src/main/resources/DATA_BASE.sqlite";

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public String[] selectQuestion(int id) {
        String sql = String.format("SELECT Description, Right_answer, Wrong_answer1, Wrong_answer2, Wrong_answer3, Wrong_answer4 FROM Questions WHERE Question_id = %d", id);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            String[] Question = new String[6];
            // loop through the result set
            while (rs.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    Question[i-1]= (String) rs.getObject(i);
                }
                
            }
            return Question;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ArrayList<Integer> selectQuestionId(String category){
        ArrayList<Integer> ids = new ArrayList<Integer>();

        String sql = String.format("SELECT Question_id FROM Questions WHERE Category = '%s'",category);

        try(
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

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
