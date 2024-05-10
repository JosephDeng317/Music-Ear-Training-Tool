package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// add to git
public class IntervalTest {

    Interval interval1;
    Interval interval2;

    @BeforeEach
    public void setup() {
        interval1 = new Interval(1, 2);
        interval2 = new Interval(5, 15);
    }

    @Test
    public void testGetIntervalName() {
        assertEquals("Minor 2nd", interval1.getIntervalName());
        assertEquals("Minor 7th", interval2.getIntervalName());
    }

    @Test
    public void testGetNote1() {
        assertEquals(1, interval1.getNote1());
        assertEquals(5, interval2.getNote1());
    }

    @Test
    public void testGetNote2() {
        assertEquals(2, interval1.getNote2());
        assertEquals(15, interval2.getNote2());
    }
}
