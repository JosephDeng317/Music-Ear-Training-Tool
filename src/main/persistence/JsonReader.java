// Referenced JSONSerializationDemo

package persistence;

import model.TestResult;
import model.TestResultCollection;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<TestResultCollection> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCompleteHistory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses test history from JSON object and returns it
    private ArrayList<TestResultCollection> parseCompleteHistory(JSONObject jsonObject) {
        ArrayList<TestResultCollection> history = new ArrayList<>();
        JSONArray easyTests = jsonObject.getJSONArray("EASY");
        JSONArray mediumTests = jsonObject.getJSONArray("MEDIUM");
        JSONArray hardTests = jsonObject.getJSONArray("HARD");
        history.addAll(parseResultsOfDifficulty(easyTests, "EASY"));
        history.addAll(parseResultsOfDifficulty(mediumTests, "MEDIUM"));
        history.addAll(parseResultsOfDifficulty(hardTests, "HARD"));
        return history;
    }

    // EFFECTS: parses JSON subarrays (split by difficulty) into an array of TestResultCollection
    private ArrayList<TestResultCollection> parseResultsOfDifficulty(JSONArray jsonArray, String difficulty) {
        ArrayList<TestResultCollection> history = new ArrayList<>();
        for (Object jsonSubArray : jsonArray) {
            JSONArray nextTestResultCollection = (JSONArray) jsonSubArray;
            addResultCollection(history, nextTestResultCollection, difficulty);
        }
        return history;
    }

    // MODIFIES: history
    // EFFECTS: parses single result collection and adds it to the history
    private void addResultCollection(ArrayList<TestResultCollection> history, JSONArray jsonArray, String difficulty) {
        TestResultCollection testResultCollection = new TestResultCollection(new ArrayList<>(), difficulty);
        for (Object json : jsonArray) {
            JSONObject nextTestResult = (JSONObject) json;
            addTestResult(testResultCollection, nextTestResult);
        }
        history.add(testResultCollection);
    }

    // MODIFIES: resultCollection
    // EFFECTS: parses a single test result and adds it to a result collection
    private void addTestResult(TestResultCollection resultCollection, JSONObject jsonObject) {
        String correct = jsonObject.getString("correct");
        String inputted = jsonObject.getString("inputted");
        TestResult result = new TestResult(correct, inputted);
        resultCollection.getCollection().add(result);
    }
}
