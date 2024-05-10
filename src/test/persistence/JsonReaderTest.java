// Referenced JSONSerializationDemo

package persistence;

import model.TestResultCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ArrayList<TestResultCollection> history = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmpty() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            ArrayList<TestResultCollection> history = reader.read();
            assertEquals(0,history.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneral() {
        JsonReader reader = new JsonReader("./data/testReaderGeneral.json");
        try {
            ArrayList<TestResultCollection> history = reader.read();
            assertEquals(3,history.size());
            TestResultCollection easyCollection = history.get(0);
            TestResultCollection mediumCollection = history.get(1);
            TestResultCollection hardCollection = history.get(2);
            assertEquals("EASY", easyCollection.getDifficulty());
            assertEquals("MEDIUM", mediumCollection.getDifficulty());
            assertEquals("HARD", hardCollection.getDifficulty());
            checkTestResult("Major 3rd", "Major 2nd", easyCollection.getCollection().get(0));
            checkTestResult("Major 7th", "Minor 3rd", mediumCollection.getCollection().get(1));
            checkTestResult("Major 7th", "Major 3rd", hardCollection.getCollection().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
