package bob.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bob.data.exception.DukeException;
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
            fail("An exception was thrown.");
        }
    }

    @Test
    public void getType_emptyInput_correctString() {
        try {
            assertEquals("Event", new EventTask("test",
                    "01/01/2023 1200", "02/01/2023 1200").getType());
        } catch (DukeException e) {
            System.out.println(e);
            fail("An exception was thrown.");
        }
    }

    @Test
    public void getDateTime_emptyInput_correctString() {
        try {
            assertEquals("01/01/2023 1200,02/01/2023 1200", new EventTask("test",
                    "01/01/2023 1200", "02/01/2023 1200").getDateTime());
        } catch (DukeException e) {
            System.out.println(e);
            fail("An exception was thrown.");
        }
    }

    @Test
    public void testInvalidToDate() {
        DukeException thrown = assertThrows(DukeException.class, () -> new EventTask("test",
                "02/01/2023 1200", "01/01/2023 1200"),
                "Expected constructor to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Your start date is either the same or after your end date!"));
    }

    @Test
    public void toFileString_completedTask_stringWithCompleted() {
        try {
            EventTask task = new EventTask("test",
                "01/01/2023 1200", "02/01/2023 1200");
            assertEquals("Event,0,test,01/01/2023 1200,02/01/2023 1200\n", task.toFileString());
        } catch (DukeException e) {
            System.out.println(e);
            fail("An exception was thrown.");
        }
    }

    @Test
    public void toFileString_incompleteTask_stringWithIncomplete() {
        try {
            EventTask task = new EventTask("test",
                    "01/01/2023 1200", "02/01/2023 1200");
            task.setDone();
            assertEquals("Event,1,test,01/01/2023 1200,02/01/2023 1200\n", task.toFileString());
        } catch (DukeException e) {
            System.out.println(e);
            fail("An exception was thrown.");
        }
    }
}
