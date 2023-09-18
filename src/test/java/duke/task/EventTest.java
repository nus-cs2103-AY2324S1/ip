package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidStartEndDatetimeException;

public class EventTest {

    @Test
    public void testTaskStringify() {
        try {
            // isDone is false
            assertEquals("E | 0 | project meeting | 2023-08-06 1400 - 2023-08-06 1600",
                    new Event("project meeting", false, "2023-08-06 1400", "2023-08-06 1600")
                            .taskStringify());

            // isDone is true
            assertEquals("E | 1 | project meeting | 2023-08-06 1400 - 2023-08-06 1600",
                    new Event("project meeting", true, "2023-08-06 1400", "2023-08-06 1600")
                            .taskStringify());
        } catch (InvalidStartEndDatetimeException e) {
            final String expectedMsg = "☹ OOPS!!! Start datetime cannot be >= end datetime.";
            assertEquals(expectedMsg, e.getMessage());
        }
    }

    @Test
    public void testToString() {
        try {
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
        } catch (InvalidStartEndDatetimeException e) {
            final String expectedMsg = "☹ OOPS!!! Start datetime cannot be >= end datetime.";
            assertEquals(expectedMsg, e.getMessage());
        }
    }
}
