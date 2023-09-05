package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toLogStringTest() {
        Event event = new Event("test", "2020-10-01", "2023-12-20");
        assertEquals(event.toLogString(),
                "E|O|test|2020-10-01|2023-12-20",
                "'toLogString()' should return a simplified string to be saved to file");
    }

    @Test
    public void toStringTest() {
        Event event = new Event("test", "2020-10-01", "2023-12-20");
        assertEquals(event.toString(),
                "[E][ ] test (from: Oct 1 2020 to: Dec 20 2023)",
                "'toString()' should return a string to be printed");
    }
}
