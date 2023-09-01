package fluke;

import fluke.exceptions.FlukeException;
import fluke.tasks.Deadline;
import fluke.tasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TaskListTest {
    @Test
    public void addDeadline_invalidDateInput_exceptionThrown() {
        try {
            Deadline task = new Deadline("Do Homework", "invalid date");
            fail();
        } catch (FlukeException f) {
            assertEquals("I don't understand!", f.getMessage());
        }
    }

    @Test
    public void addEvent_futureToPastInput_exceptionThrown() {
        try {
            Event task = new Event("Do Homework", "2023-02-01", "2023-01-26");
            fail();
        } catch (FlukeException f) {
            assertEquals("I don't understand!", f.getMessage());
        }
    }
}
