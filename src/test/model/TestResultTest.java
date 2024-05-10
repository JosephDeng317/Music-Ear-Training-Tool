package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestResultTest {
    TestResult result;

    @BeforeEach
    public void setup() {
        result = new TestResult("Perfect 5th", "Perfect 4th");
    }

    @Test
    public void testIsCorrect() {
        assertFalse(result.isCorrect());
        result.setInputtedAnswer("Perfect 5th");
        assertTrue(result.isCorrect());
    }

    @Test
    public void testSetGetInputtedAnswer() {
        assertEquals("Perfect 4th", result.getInputtedAnswer());
        result.setInputtedAnswer("Major 2nd");
        assertEquals("Major 2nd", result.getInputtedAnswer());
    }

    @Test
    public void testGetCorrectAnswer() {
        assertEquals("Perfect 5th", result.getCorrectAnswer());
    }

}
