package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testToString() {
        LocalDate start = LocalDate.of(2023, 9, 1);
        LocalDate end = LocalDate.of(2023, 9, 5);
        Event event = new Event("Conference", start, end);
        String expected = "[E][ ] Conference (from: Sep 1 2023 to: Sep 5 2023)";
        assertEquals(expected, event.toString());
    }

    @Test
    void testMark() {
        LocalDate start = LocalDate.of(2023, 9, 1);
        LocalDate end = LocalDate.of(2023, 9, 5);
        Event event = new Event("Conference", start, end);
        event.mark();
        assertTrue(event.isMarked());
    }

    @Test
    void testUnmark() {
        LocalDate start = LocalDate.of(2023, 9, 1);
        LocalDate end = LocalDate.of(2023, 9, 5);
        Event event = new Event("Conference", start, end);
        event.mark();
        assertTrue(event.isMarked());
        event.unMark();
        assertFalse(event.isMarked());
    }
}

