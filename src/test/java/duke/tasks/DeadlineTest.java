package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    // Testing the creation of a Deadline task and its default values.
    @Test
    public void createDeadlineTest() {
        Deadline deadline = new Deadline("Complete project", "2023-09-01");

        assertEquals(TaskType.DEADLINE, deadline.getTaskType());
        assertEquals("Complete project", deadline.getDescription());
        assertFalse(deadline.isDone()); // Ensure that by default, tasks are not done.
        assertEquals("2023-09-01", deadline.getBy());
    }

    // Testing the string representation of a Deadline task.
    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline("Complete project", "2023-09-01");
        assertEquals("[D][ ] Complete project (by: 2023-09-01)", deadline.toString());
    }

    // Testing the transformFormat method for a Deadline task.
    @Test
    public void transformFormatTest() {
        Deadline deadline = new Deadline("Complete project", "2023-09-01");
        assertEquals("D | false | Complete project | 2023-09-01", deadline.transformFormat());
    }
}

