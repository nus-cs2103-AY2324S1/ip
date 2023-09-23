package moss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The TaskListTest class contains unit tests for the TaskList class.
 */
public class TaskListTest {

    private Storage storage = new Storage();
    private ArrayList<Task> tasks = (ArrayList<Task>) storage.loadTasks();

    public TaskListTest() throws MossException {
    }

    /**
     * Sets up the test environment before each test method is executed.
     *
     * @throws MossException If there's an issue with setting up the environment.
     */
    @BeforeEach
    public void setUp() throws MossException {
        tasks = (ArrayList<Task>) storage.loadTasks();
        //TaskList.command("todo Buy food", tasks, storage);
        //TaskList.command("todo Study", tasks, storage);
        storage = new Storage();
    }

    /**
     * Tests the 'command' method for adding a to-do task.
     *
     * @throws MossException If there's an issue with task management.
     */
    @Test
    public void testCommand_AddToDo() throws MossException {
        TaskList.command("todo Buy groceries", tasks, storage);
        tasks = (ArrayList<Task>) storage.loadTasks();
        int count = tasks.size();
        assertEquals(count, tasks.size());
        assertTrue(tasks.get(count-1) instanceof ToDo);
    }

    /**
     * Tests the 'command' method for adding a deadline task.
     *
     * @throws MossException If there's an issue with task management.
     */
    @Test
    public void testCommand_AddDeadline() throws MossException {
        TaskList.command("deadline Finish homework /by 2023-09-15", tasks, storage);
        tasks = (ArrayList<Task>) storage.loadTasks();
        int count = tasks.size();
        assertEquals(count, tasks.size());
        assertTrue(tasks.get(count-1) instanceof Deadline);
        assertEquals(LocalDate.parse("2023-09-15"), ((Deadline) tasks.get(count-1)).getDate());
    }

    /**
     * Tests the 'command' method for adding an event task.
     *
     * @throws MossException If there's an issue with task management.
     */
    @Test
    public void testCommand_AddEvent() throws MossException {
        TaskList.command("event Party /from 2023-09-21 /to 2023-09-22", tasks, storage);
        tasks = (ArrayList<Task>) storage.loadTasks();
        int count = tasks.size();
        assertEquals(count, tasks.size());
        assertTrue(tasks.get(count-1) instanceof Event);

        assertEquals(LocalDate.parse("2023-09-21"), ((Event) tasks.get(count-1)).getFromDate());
        assertEquals(LocalDate.parse("2023-09-22"), ((Event) tasks.get(count-1)).getToDate());
    }

    /**
     * Tests the 'command' method for marking a task as done.
     *
     * @throws MossException If there's an issue with task management.
     */
    @Test
    public void testCommand_MarkTaskDone() throws MossException {
        TaskList.command("todo Study", tasks, storage);
        tasks = (ArrayList<Task>) storage.loadTasks();
        int count = tasks.size();
        TaskList.command("mark " + count, tasks, storage);
        tasks = (ArrayList<Task>) storage.loadTasks();
        count = tasks.size();
        assertTrue(tasks.get(count-1).isDone());
    }

    /**
     * Tests the 'command' method for marking a task as undone.
     *
     * @throws MossException If there's an issue with task management.
     */
    @Test
    public void testCommand_MarkTaskUndone() throws MossException {
        TaskList.command("todo Study", tasks, storage);
        tasks = (ArrayList<Task>) storage.loadTasks();
        int count = tasks.size();
        TaskList.command("unmark " + count, tasks, storage);
        tasks = (ArrayList<Task>) storage.loadTasks();
        count = tasks.size();
        assertFalse(tasks.get(count-1).isDone());
    }

    /**
     * Tests the 'command' method for deleting a task.
     *
     * @throws MossException If there's an issue with task management.
     */
    @Test
    public void testCommand_DeleteTask() throws MossException {
        TaskList.command("todo Study", tasks, storage);
        tasks = (ArrayList<Task>) storage.loadTasks();
        int count = tasks.size();
        TaskList.command("delete " + count, tasks, storage);
        tasks = (ArrayList<Task>) storage.loadTasks();
        count = tasks.size();
        assertEquals(tasks.size(), count);
    }
}



