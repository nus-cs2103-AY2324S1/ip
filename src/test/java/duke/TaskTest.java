package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    @Test
    public void testMarkAsDone() {
        Task task = new Task("Sample Task");
        task.markAsDone();
        assertTrue(task.isDone);
    }

    @Test
    public void testUnmarkAsDone() {
        Task task = new Task("Sample Task");
        task.markAsDone();
        task.unmarkAsDone();
        assertFalse(task.isDone);
    }
}
