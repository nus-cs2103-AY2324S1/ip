package aichan.task;

import aichan.AiChanException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
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
