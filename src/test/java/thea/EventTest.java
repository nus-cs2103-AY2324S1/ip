package thea;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toMemoryFormatTest() {
        Event event = new Event("Career Fair", "2023-09-12 13:00",
                "2023-09-13 17:00");
        assertEquals("E | 0 | Career Fair | 2023-09-12 13:00 | 2023-09-13 17:00",
                event.toMemoryFormat());
    }

    @Test
    public void toStringTest() {
        Event event = new Event("Career Fair", "2023-09-12 13:00",
                "2023-09-13 17:00");
        assertEquals("[E][ ] Career Fair (from: Sep 12 2023 13:00 to: Sep 13 2023 17:00)",
                event.toString());
    }

    @Test
    public void markAsDoneTest() {
        Event event = new Event("Career Fair", "2023-09-12 13:00",
                "2023-09-13 17:00");
        event.markAsDone();
        assertEquals("[E][X] Career Fair (from: Sep 12 2023 13:00 to: Sep 13 2023 17:00)",
                event.toString());
    }

    @Test
    public void unmarkAsDoneTest() {
        Event event = new Event("Career Fair", "2023-09-12 13:00",
                "2023-09-13 17:00");
        event.markAsDone();
        event.unmarkAsDone();
        assertEquals("[E][ ] Career Fair (from: Sep 12 2023 13:00 to: Sep 13 2023 17:00)",
                event.toString());
    }
}
