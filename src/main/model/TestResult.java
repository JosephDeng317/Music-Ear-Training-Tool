package model;

import org.json.JSONObject;
import persistence.Writable;

// Result of a singular question, contains the inputted answer and the correct answer
public class TestResult implements Writable {
    private String correctAnswer;
    private String inputtedAnswer;

    // MODIFIES: this
    // EFFECTS: creates an instance of TestResult
    public TestResult(String answer, String input) {
        this.correctAnswer = answer;
        this.inputtedAnswer = input;
        EventLog.getInstance().logEvent(new Event("Quiz question answered."));
    }

    // EFFECTS: returns true if the inputted answer is correct
    public boolean isCorrect() {
        EventLog.getInstance().logEvent(new Event("Viewing Past Result"));
        return correctAnswer.equals(inputtedAnswer);
    }

    public void setInputtedAnswer(String input) {
        inputtedAnswer = input;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getInputtedAnswer() {
        return inputtedAnswer;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("correct", correctAnswer);
        json.put("inputted", inputtedAnswer);
        return json;
    }
}
