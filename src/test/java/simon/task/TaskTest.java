package simon.task;

import org.junit.jupiter.api.Test;
import simon.SimonException;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void markAsDone_taskNotDone_taskMarkedAsDone() {
        Task task = new ToDo("Sample Task");
        task.markAsDone();
        assertTrue(task.isDone);
    }

    @Test
    public void markAsUndone_taskDone_taskMarkedAsUndone() {
        Task task = new ToDo("Sample Task");
        task.markAsDone();
        task.markAsUndone();
        assertFalse(task.isDone);
    }

    @Test
    public void deadline_toString_formattedCorrectly() throws SimonException {
        Deadline deadline = new Deadline("Submit Report", "12/12/2023 2359");
        String expected = " [D][ ] Submit Report (by: Dec 12 2023, 11:59 PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void event_toString_formattedCorrectly() throws SimonException {
        Event event = new Event("Team Meeting", "12/12/2023 1400", "12/12/2023 1600");
        String expected = " [E][ ] Team Meeting (from: Dec 12 2023, 2:00 PM to: Dec 12 2023, 4:00 PM)";
        assertEquals(expected, event.toString());
    }
}
