package duke.tasks;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeInvalidArgumentException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTest {
    @Test
    public void testParseDateInput() {
        assertEquals("2023-08-29 08:00", Task.parseDateInput("2023-08-29 08:00"));
    }

    @Test
    public void parseInvalidDateInput_exceptionThrown() {
        String[] invalidDates = new String[] { "29-08-2023", "2023-08-29" };
        for (String d : invalidDates) {
            assertThrows(DukeInvalidArgumentException.class, () -> Task.parseDateInput(d));
        }
    }

    @Test
    public void testGetDateOutputString() {
        Event event = new Event("2101 Tutorial", "2023-08-29 08:00", "2023-08-29 10:00");
        assertEquals("[E][ ] 2101 Tutorial (from: Tue 29 Aug 2023 08:00 to: Tue 29 Aug 2023 10:00)", event.toString());
    }
}
