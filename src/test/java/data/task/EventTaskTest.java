package data.task;

import data.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class EventTaskTest {
    @Test
    public void testStringConversion() {
        try {
            EventTask testTask = new EventTask("test",
                    "01/01/2023 1200", "02/01/2023 1200");
            assertEquals("[E][ ] test (from: 01 Jan 2023 12:00 PM to: 02 Jan 2023 12:00 PM)",
                    testTask.toString());
            testTask.setDone();
            assertEquals("[E][X] test (from: 01 Jan 2023 12:00 PM to: 02 Jan 2023 12:00 PM)",
                    testTask.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getType_emptyInput_correctString() {
        try {
            assertEquals("Event", new EventTask("test",
                    "01/01/2023 1200", "02/01/2023 1200").getType());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getDateTime_emptyInput_correctString() {
        try {
            assertEquals("01/01/2023 1200,02/01/2023 1200", new EventTask("test",
                    "01/01/2023 1200", "02/01/2023 1200").getDateTime());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void invalidToDate() {
        DukeException thrown = assertThrows(DukeException.class, () -> new EventTask("test",
                "02/01/2023 1200", "01/01/2023 1200"),
                "Expected constructor to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Your start date is either the same or after your end date!"));
    }
}