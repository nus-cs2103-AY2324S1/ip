package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;

public class ParserTest {
    @Test
    public void testTaskToStringData() {
        Task task = new Deadline("math homework", "12/12/2002 1700");
        String taskString = Parser.taskData(task);
        String expected = "D | 0 | math homework | 12/12/2002 1700";

        assertEquals(expected, taskString);
    }

    @Test
    public void testStringDataToEventTask() {
        String taskString = "E | 1 | project meeting | Mon 2pm | 6pm";
        Task task = Parser.dataToTask(taskString);

        assertEquals(Event.class, task.getClass());

        assertEquals("project meeting", task.getDescription());

        assertTrue(task.getIsDone());

        Event event = (Event) task;
        assertEquals("Mon 2pm", event.getFrom());
        assertEquals("6pm", event.getTo());
    }

    @Test
    public void testStringToDeadlineTask() {
        String taskString = "D | 0 | assignment 0 | 12/12/2002 1700";
        Task task = Parser.dataToTask(taskString);

        assertEquals(Deadline.class, task.getClass());

        assertEquals("assignment 0", task.getDescription());

        assertTrue(!task.getIsDone());

        Deadline deadline = (Deadline) task;
        assertEquals(LocalDateTime.parse("2002-12-12T17:00"), deadline.getBy());
    }

    // Supposed to be Deadline test
    @Test
    public void testDeadlineString() {
        Task task = new Deadline("math homework", "12/12/2002 1700");
        String expected = "[D][ ] math homework (by: 12 Dec 2002 5:00 PM)";

        assertEquals(expected, task.toString());
    }

}
