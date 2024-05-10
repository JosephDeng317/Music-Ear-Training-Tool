package model;

import java.lang.Math.*;

// Represents 2 music notes that create an interval

public class Interval {
    private int note1;
    private int note2;

    // MODIFIES: this
    // EFFECTS: creates a new interval storing 2 notes
    public Interval(int note1, int note2) {
        this.note1 = note1;
        this.note2 = note2;
    }

    // EFFECTS: returns the name associated with the interval between the 2 notes
    public String getIntervalName() {
        IntervalDictionary intervalDict = new IntervalDictionary();

        int gap = Math.abs(note2 - note1);
        return intervalDict.getInterval(gap);
    }

    public int getNote1() {
        return note1;
    }

    public int getNote2() {
        return note2;
    }
}
