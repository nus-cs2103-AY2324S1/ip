package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskTest {

    @Test
    public void getDescription_deadlineValidInput_correctString() {
        Deadline d = null;
        try {
            d = new Deadline("finish homework", "2023-10-19");
            assertEquals("[D] [ ] finish homework (by: Oct 19 2023)", d.getDescription());
            assertEquals("D | 0 | finish homework | 2023-10-19", d.getSavedString());
        } catch (DukeException e) {
            assertEquals("Please provide a deadline in the format yyyy-MM-dd", e.getMessage());
        }
    }

    @Test
    public void getDescription_deadlineInvalidInput_exceptionThrown() {
        Deadline d = null;
        try {
            d = new Deadline("finish homework", "Friday 2pm");
        } catch (DukeException e) {
            assertEquals("Please provide a deadline in the format yyyy-MM-dd", e.getMessage());
        }
    }

    @Test
    public void getDescription_eventValidInput_correctString() {
        Event d = new Event("finish homework", "Friday 10pm", "11pm");
        assertEquals("[E] [ ] finish homework (from: Friday 10pm to: 11pm)", d.getDescription());
        assertEquals("E | 0 | finish homework | Friday 10pm-11pm", d.getSavedString());
    }
}
