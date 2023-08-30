package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testTaskStringify() {
        // isDone is false
        assertEquals("E | 0 | project meeting | 2023-08-06 1400 - 2023-08-06 1600",
                new Event("project meeting", false, "2023-08-06 1400", "2023-08-06 1600")
                        .taskStringify());

        // isDone is true
        assertEquals("E | 1 | project meeting | 2023-08-06 1400 - 2023-08-06 1600",
                new Event("project meeting", true, "2023-08-06 1400", "2023-08-06 1600")
                        .taskStringify());
    }

    @Test
    public void testToString() {
        // isDone not given
        assertEquals("[E][ ] project meeting (from: Aug 06 2023 1400 to: Aug 06 2023 1600)",
                new Event("project meeting", "2023-08-06 1400", "2023-08-06 1600").toString());

        // isDone is false
        assertEquals("[E][ ] project meeting (from: Aug 06 2023 1400 to: Aug 06 2023 1600)",
                new Event("project meeting", false, "2023-08-06 1400", "2023-08-06 1600")
                        .toString());

        // isDone is true
        assertEquals("[E][X] project meeting (from: Aug 06 2023 1400 to: Aug 06 2023 1600)",
                new Event("project meeting", true, "2023-08-06 1400", "2023-08-06 1600")
                        .toString());
    }
}
