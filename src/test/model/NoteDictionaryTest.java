package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteDictionaryTest {
    private NoteDictionary noteDictionary;

    @BeforeEach
    public void setup() {
        noteDictionary = new NoteDictionary();
    }

    @Test
    public void testGetNoteNum() {
        assertEquals("C1", noteDictionary.getNoteName(0));
        assertEquals("D#1", noteDictionary.getNoteName(3));
        assertEquals("B2", noteDictionary.getNoteName(23));
        assertEquals("Not Defined", noteDictionary.getNoteName(24));
    }
}