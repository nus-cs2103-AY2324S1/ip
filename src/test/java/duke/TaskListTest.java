package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    }

    @Test
    public void testHandleTodo() {
        taskList.handleTodo("todo Sample todo task");

        assertEquals(1, taskList.taskCount);
        assertTrue(taskList.tasks.get(0) instanceof ToDos);
    }

    @Test
    public void testDelete() {
        taskList.handleTodo("todo Sample todo task");
        taskList.handleTodo("todo Sample todo task 2");
        taskList.delete("delete 2");

        assertEquals(1, taskList.taskCount);
        assertTrue(taskList.tasks.get(0) instanceof ToDos);
    }
}

