package model;

import java.util.Dictionary;
import java.util.Hashtable;

// A dictionary that translates numbers into note names
// This class is useful for making generated notes readable to the user

public class NoteDictionary {
    private Dictionary<Integer, String> dict;

    // MODIFIES: this
    // EFFECTS: creates an instance of NoteDictionary
    public NoteDictionary() {
        String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        this.dict = new Hashtable<>();
        int num = 0;
        for (int i = 1; i <= 2; i++) {
            for (String note : notes) {
                String noteWithOctave = note + i;
                dict.put(num, noteWithOctave);
                num++;
            }
        }
    }

    // EFFECTS: returns the note name associated with the number, "Not Defined" if it cannot be found
    public String getNoteName(int note) {
        if (dict.get(note) != null) {
            return dict.get(note);
        }
        return "Not Defined";
    }
}
