package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for the {@link Event} class.
 */
public class EventTest {
    /**
     * Test the {@link Event#toString()} method.
     */
    @Test
    public void testToString() {
        Event event = new Event("Meeting", "2023-08-15", "2023-08-16");
        assertEquals("[E][ ] Meeting (from: Aug 15 2023 to: Aug 16 2023)", event.toString());
    }

    /**
     * Test the {@link Event#markDone()} method.
     */
    @Test
    public void testMarkDone() {
        Event event = new Event("Party", "2023-08-20", "2023-08-21");
        assertFalse(event.isDone());
        event.markDone();
        assertTrue(event.isDone());
    }

    /**
     * Test the {@link Event#markUndone()} method.
     */
    @Test
    public void testMarkUndone() {
        Event event = new Event("Conference", "2023-08-25", "2023-08-26");
        event.markDone();
        assertTrue(event.isDone());
        event.markUndone();
        assertFalse(event.isDone());
    }

    /**
     * Test the {@link Event#getName()} method.
     */
    @Test
    public void testGetName() {
        Event event = new Event("Gathering", "2023-08-30", "2023-08-31");
        assertEquals("Gathering", event.getName());
    }

    /**
     * Test the {@link Event#toString()} method with invalid dates.
     */
    @Test
    public void testToStringWithInvalidDates() {
        Event event = new Event("Invalid Event", "invalid-start", "invalid-end");
        assertEquals("[E][ ] Invalid Event (from: invalid-start to: invalid-end)", event.toString());
    }
}
