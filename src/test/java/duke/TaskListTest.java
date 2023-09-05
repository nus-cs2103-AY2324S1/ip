package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


class TaskListTest {
    private final File tempFile;

    TaskListTest() {
        tempFile = new File("./src/main/data/tasklist.txt");
    }

    @Test
    void testAddToList() throws FileNotFoundException {
        TaskList taskList = new TaskList(tempFile);
        ToDo todo = new ToDo("Buy groceries");
        taskList.addToList(todo, 0);
        assertEquals(1, taskList.tasks.size());
        tempFile.deleteOnExit();
    }

    @Test
    void testHandleTodo() throws FileNotFoundException {
        TaskList taskList = new TaskList(tempFile);
        taskList.handleTodo("todo Buy groceries");
        assertEquals(1, taskList.tasks.size());
        assertTrue(taskList.tasks.get(0) instanceof ToDo);
        tempFile.deleteOnExit();
    }

    @Test
    void testHandleDeadline() throws FileNotFoundException {
        TaskList taskList = new TaskList(tempFile);
        taskList.handleDeadline("deadline Complete assignment /by 2023-09-30");
        assertEquals(1, taskList.tasks.size());
        assertTrue(taskList.tasks.get(0) instanceof Deadline);
        tempFile.deleteOnExit();
    }

    @Test
    void testHandleEvent() throws FileNotFoundException {
        TaskList taskList = new TaskList(tempFile);
        taskList.handleEvent("event Conference /from 2023-09-01 /to 2023-09-05");
        assertEquals(1, taskList.tasks.size());
        assertTrue(taskList.tasks.get(0) instanceof Event);
        tempFile.deleteOnExit();
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
        tempFile.deleteOnExit();
    }
}
