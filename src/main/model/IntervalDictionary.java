package model;

import java.util.Dictionary;
import java.util.Hashtable;

// A dictionary that translates a numerical distance between notes into an interval name
// Useful for displaying intervals in music theory terms

public class IntervalDictionary {
    private Dictionary<Integer, String> dict;

    // MODIFIES: this
    // EFFECTS: creates an instance of IntervalDictionary
    public IntervalDictionary() {
        this.dict = new Hashtable<>();
        dict.put(0, "Same Note");
        dict.put(1, "Minor 2nd");
        dict.put(2, "Major 2nd");
        dict.put(3, "Minor 3rd");
        dict.put(4, "Major 3rd");
        dict.put(5, "Perfect 4th");
        dict.put(6, "Tritone");
        dict.put(7, "Perfect 5th");
        dict.put(8, "Minor 6th");
        dict.put(9, "Major 6th");
        dict.put(10, "Minor 7th");
        dict.put(11, "Major 7th");
        dict.put(12, "Octave");
    }

    // EFFECTS: returns the interval name associated with the number, "Not Defined" if it cannot be found
    public String getInterval(int interval) {
        if (dict.get(interval) != null) {
            return dict.get(interval);
        }
        return "Not Defined";
    }
}
