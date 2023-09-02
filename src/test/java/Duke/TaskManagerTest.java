package duke;

import task.Task;

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
