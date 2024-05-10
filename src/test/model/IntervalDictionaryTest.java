package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// add to git
public class IntervalDictionaryTest {
    IntervalDictionary dict;

    @BeforeEach
    public void setup() {
        dict = new IntervalDictionary();
    }

    @Test
    public void testGetInterval() {
        assertEquals("Same Note", dict.getInterval(0));
        assertEquals("Perfect 4th", dict.getInterval(5));
        assertEquals("Octave", dict.getInterval(12));
        assertEquals("Not Defined", dict.getInterval(13));
    }


}
