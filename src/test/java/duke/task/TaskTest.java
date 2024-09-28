package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    ToDo task = new ToDo("test");
    @Test
    void testStatusIcon() {
        assertEquals(task.getStatusIcon(), " ");
        task.updateTaskStatus(true, "", "");
        assertEquals(task.getStatusIcon(), "X");
        task.updateTaskStatus(false, "", "");
        assertEquals(task.getStatusIcon(), " ");
    }
}
