package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;

class TaskListTest {
    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile(null, null).toFile();
    }

    @Test
    void testAddToList() throws FileNotFoundException {
        TaskList taskList = new TaskList(tempFile);
        ToDo todo = new ToDo("Buy groceries");
        taskList.addToList(todo, 0);
        assertEquals(1, taskList.tasks.size());
    }

    @Test
    void testHandleTodo() throws FileNotFoundException {
        TaskList taskList = new TaskList(tempFile);
        taskList.handleTodo("todo Buy groceries");
        assertEquals(1, taskList.tasks.size());
        assertTrue(taskList.tasks.get(0) instanceof ToDo);
    }

    @Test
    void testHandleDeadline() throws FileNotFoundException {
        TaskList taskList = new TaskList(tempFile);
        taskList.handleDeadline("deadline Complete assignment /by 2023-09-30");
        assertEquals(1, taskList.tasks.size());
        assertTrue(taskList.tasks.get(0) instanceof Deadline);
    }

    @Test
    void testHandleEvent() throws FileNotFoundException {
        TaskList taskList = new TaskList(tempFile);
        taskList.handleEvent("event Conference /from 2023-09-01 /to 2023-09-05");
        assertEquals(1, taskList.tasks.size());
        assertTrue(taskList.tasks.get(0) instanceof Event);
    }

    @Test
    void testHandleFind() throws FileNotFoundException {
        TaskList taskList = new TaskList(tempFile);
        ToDo todo = new ToDo("Buy groceries");
        taskList.addToList(todo, 0);
        ArrayList<Task> findTasks = new ArrayList<>();
        taskList.handleFind("find groceries");
        assertEquals(1, taskList.tasks.size());
        assertTrue(taskList.tasks.get(0) instanceof ToDo);
    }
}
