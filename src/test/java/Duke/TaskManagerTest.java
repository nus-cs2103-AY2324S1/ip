package Duke;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {
    @Test
    public void test1() {
        TaskManager taskManager = new TaskManager(new ArrayList<Task>());
        taskManager.todo("read book");
        assertEquals(1, taskManager.displayList().size());

    }

}
