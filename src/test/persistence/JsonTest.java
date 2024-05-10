// Referenced JSONSerializationDemo

package persistence;

import model.TestResultCollection;
import model.TestResult;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTestResult(String correct, String inputted, TestResult testResult) {
        assertEquals(correct, testResult.getCorrectAnswer());
        assertEquals(inputted, testResult.getInputtedAnswer());
    }
}
