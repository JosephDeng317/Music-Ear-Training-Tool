package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestResultCollectionTest {
    TestResultCollection resultCollection;

    @BeforeEach
    void setup() {
        resultCollection = new TestResultCollection(new ArrayList<>(), "EASY");
    }

    @Test
    void testConstructor() {
        assertTrue(resultCollection.getCollection().isEmpty());
        assertEquals("EASY", resultCollection.getDifficulty());
    }

    @Test
    void testSetDifficulty() {
        resultCollection.setDifficulty("HARD");
        assertEquals("HARD", resultCollection.getDifficulty());
    }
}
