package aichan.task;

import aichan.AiChanException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Event class.
 */
public class EventTest {

    /**
     * Checks whether the string representation for user is correct format.
     */
    @Test
    public void testToString() {
        String[] arr = {"meeting", "13/12/2019 1200", "13/12/2019 1400"};
        String result;
        try {
            Event event = new Event(arr);
            result = event.toString();
        } catch (AiChanException e) {
            result = e.getMessage();
        }
        assertEquals("[E][ ] meeting (from: Dec 13 2019 1200 to: Dec 13 2019 1400)", result);
    }

    /**
     * Checks whether the string representation for storing in line is correct format.
     */
    @Test
    public void testToFileLine() {
        String[] arr = {"meeting", "13/12/2019 1200", "13/12/2019 1400"};
        String result;
        try {
            Event event = new Event(arr);
            result = event.toFileLine();
        } catch (AiChanException e) {
            result = e.getMessage();
        }
        assertEquals("E | 0 | meeting | 13/12/2019 1200 - 13/12/2019 1400", result);
    }
}
