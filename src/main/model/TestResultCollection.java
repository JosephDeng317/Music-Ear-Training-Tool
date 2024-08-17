package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Contains all the test results in an entire quiz
public class TestResultCollection {
    String difficulty;
    ArrayList<TestResult> collection;
    boolean visible;

    // Constructs an instance of TestResultCollection which acts as a container for
    // a list of Test Results
    public TestResultCollection(ArrayList<TestResult> collection, String difficulty) {
        // EventLog.getInstance().logEvent(new Event("New Quiz Started"));
        this.difficulty = difficulty;
        this.collection = collection;
        visible = true;
    }

    public void setVisibleTrue() {
        EventLog.getInstance().logEvent(new Event(difficulty + " Collection is now visible"));
        visible = true;
    }

    public void setVisibleFalse() {
        EventLog.getInstance().logEvent(new Event(difficulty + " Collection is now hidden"));
        visible = false;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String d) {
        EventLog.getInstance().logEvent(new Event("Difficulty was selected as " + d));
        this.difficulty = d;
    }

    // MODIFIES: this
    // EFFECTS: adds a test result to the collection
    public void addResultToCollection(TestResult t) {
        collection.add(t);
        EventLog.getInstance().logEvent(new Event("Added singular test result to the history of the quiz instance"));
    }

    public ArrayList<TestResult> getCollection() {
        return collection;
    }

    // EFFECTS: returns the collection of testResults contained in collection as a
    // JSON array
    public JSONArray testResultsToJson() {
        EventLog.getInstance().logEvent(new Event("Saved Result of a quiz to JSON file"));
        JSONArray jsonArray = new JSONArray();

        for (TestResult t : collection) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    public ArrayList<String> getInputList() {
        ArrayList<String> inputList = new ArrayList<>();

        for (TestResult t : collection) {
            inputList.add(t.getInputtedAnswer());
        }

        return inputList;
    }

    public ArrayList<String> getAnswerList() {
        ArrayList<String> inputList = new ArrayList<>();

        for (TestResult t : collection) {
            inputList.add(t.getCorrectAnswer());
        }

        return inputList;
    }
}
