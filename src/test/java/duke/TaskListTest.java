package duke;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setup() throws IOException {
        File tempFile = File.createTempFile("tempTaskList", ".txt");
        taskList = new TaskList(tempFile);
        taskList.tasks = new ArrayList<>();
        taskList.taskCount = 0;
    }

    @Test
    public void testHandleTodo() {
        // Adding a task
        taskList.handleTodo("todo Sample todo task");

        assertEquals(1, taskList.taskCount);
        assertTrue(taskList.tasks.get(0) instanceof ToDos);
    }
}

