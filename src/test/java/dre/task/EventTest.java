package dre.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testFileSaveFormat1() {
        Event testEvent = new Event("get blueprint", LocalDate.parse("2001-09-11"),
                LocalDate.parse("2001-09-11"));
        assertEquals(testEvent.fileSaveFormat(), "E| |get blueprint|2001-09-11|2001-09-11");
    }

    @Test
    public void testFileSaveFormat2() {
        //test space trim
        Event testEvent = new Event("    extra spaces", LocalDate.parse("2001-09-11"),
                LocalDate.parse("2001-09-11"));
        assertEquals(testEvent.fileSaveFormat(), "E| |extra spaces|2001-09-11|2001-09-11");
    }

    @Test
    public void testFileSaveFormat3() {
        //test different date format entry
        Event testEvent = new Event("learn to fly plane", LocalDate.parse("31-01-2001"),
                LocalDate.parse("31-01-2001"));
        assertEquals(testEvent.fileSaveFormat(), "E| |learn to fly plane|2001-01-31|2001-01-31");
    }

    @Test
    public void testToString() {
        Event testEvent = new Event("learn to fly plane", LocalDate.parse("2001-09-01"),
                LocalDate.parse("2001-09-07"));
        assertEquals(testEvent.toString(), "[E][ ] learn to fly plane (from: 2001-09-01 to: later)");
    }
}
