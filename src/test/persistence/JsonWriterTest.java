// Referenced JSONSerializationDemo

package persistence;

import model.TestResult;
import model.TestResultCollection;
import org.junit.jupiter.api.Test;
import ui.Menu;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ArrayList<TestResultCollection> history = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyResults() {
        try {
            ArrayList<TestResultCollection> history = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(Menu.resultsToJson(history));
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            ArrayList<TestResultCollection> readHistory = reader.read();
            assertEquals(0, readHistory.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneral() {
        try {
            TestResultCollection easyCollection = new TestResultCollection(new ArrayList<>(), "EASY");
            TestResultCollection mediumCollection = new TestResultCollection(new ArrayList<>(), "MEDIUM");
            TestResultCollection hardCollection = new TestResultCollection(new ArrayList<>(), "HARD");
            ArrayList<TestResultCollection> history = new ArrayList<>();
            easyCollection.getCollection().add(new TestResult("Major 3rd", "Minor 3rd"));
            mediumCollection.getCollection().add(new TestResult("Major 6th", "Major 6th"));
            hardCollection.getCollection().add(new TestResult("Minor 7th", "Major 6th"));
            history.add(easyCollection);
            history.add(mediumCollection);
            history.add(hardCollection);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneral.json");
            writer.open();
            writer.write(Menu.resultsToJson(history));
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneral.json");
            ArrayList<TestResultCollection> readHistory = reader.read();
            assertEquals(3, history.size());
            TestResultCollection readEasyCollection = readHistory.get(0);
            TestResultCollection readMediumCollection = readHistory.get(1);
            TestResultCollection readHardCollection = readHistory.get(2);
            assertEquals("EASY", readEasyCollection.getDifficulty());
            assertEquals("MEDIUM", readMediumCollection.getDifficulty());
            assertEquals("HARD", readHardCollection.getDifficulty());
            checkTestResult("Major 3rd", "Minor 3rd", easyCollection.getCollection().get(0));
            checkTestResult("Major 6th", "Major 6th", mediumCollection.getCollection().get(0));
            checkTestResult("Minor 7th", "Major 6th", hardCollection.getCollection().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
