package simon;

import org.junit.jupiter.api.Test;
import simon.task.Task;
import simon.task.ToDo;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testMarkTask_validIndex() throws SimonException {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Sample Task"));
        Task markedTask = tasks.markTask("mark 1", true);
        assertTrue(markedTask.isDone);
    }

    @Test
    public void testMarkTask_invalidIndex() {
        TaskList tasks = new TaskList();
        assertThrows(SimonException.class, () -> tasks.markTask("100", true));
    }
}
