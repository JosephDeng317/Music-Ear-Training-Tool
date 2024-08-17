package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import org.json.JSONArray;
import org.json.JSONObject;

import model.TestResult;
import model.TestResultCollection;
import java.util.ArrayList;

public class SQLite {
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:results.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void Create(String tableName) {
        String sql = "CREATE TABLE " + tableName + " (Input TEXT NOT NULL, Answer TEXT NOT NULL)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void Insert(String tableName, List<String> inputs, List<String> answers) {
        String sql = "REPLACE INTO " + tableName + " (Input,Answer) VALUES(?,?)";

        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Loop through the input lists and add each set of parameters to the batch
            for (int i = 0; i < inputs.size(); i++) {
                pstmt.setString(1, inputs.get(i));
                pstmt.setString(2, answers.get(i));
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    public static List<List<String>> Select(String tableName) {
        List<List<String>> ResultList = new ArrayList<List<String>>();
        String query = "SELECT * FROM " + tableName;

        // create the preparedstatement
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            // process the results
            ResultSet rs = pstmt.executeQuery();

            // Stores properties of a ResultSet object, including column count
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            // int rowCount = rs.getInt("recordCount");

            while (rs.next()) {
                ArrayList<String> RowList = new ArrayList<String>();
                int i = 1;
                while (i <= columnCount) {
                    RowList.add(rs.getString(i++));
                }
                ResultList.add(RowList);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.print(se.getMessage());
        }
        return ResultList;
    }

    public static ArrayList<TestResultCollection> readSQL() {
        ArrayList<TestResultCollection> output = new ArrayList<>();

        ArrayList<TestResult> easyTests = new ArrayList<>();
        addTestResults(easyTests, Select("easy"));
        TestResultCollection easyCollection = new TestResultCollection(easyTests, "EASY");

        ArrayList<TestResult> medTests = new ArrayList<>();
        addTestResults(medTests, Select("medium"));
        TestResultCollection medCollection = new TestResultCollection(medTests, "MEDIUM");

        ArrayList<TestResult> hardTests = new ArrayList<>();
        addTestResults(hardTests, Select("hard"));
        TestResultCollection hardCollection = new TestResultCollection(hardTests, "HARD");

        output.add(easyCollection);
        output.add(medCollection);
        output.add(hardCollection);

        return output;
    }

    private static void addTestResults(ArrayList<TestResult> tests, List<List<String>> resultList) {
        for (List<String> pair : resultList) {
            tests.add(new TestResult(pair.get(1), pair.get(0)));
        }
    }

    public static void main(String[] args) {
        connect();
    }

}