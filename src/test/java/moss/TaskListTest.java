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
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Sets up the test environment before each test method is executed.
     *
     * @throws MossException If there's an issue with setting up the environment.
     */
    @BeforeEach
    public void setUp() throws MossException {
        tasks = new ArrayList<>();
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
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof ToDo);
    }

    /**
     * Tests the 'command' method for adding a deadline task.
     *
     * @throws MossException If there's an issue with task management.
     */
    @Test
    public void testCommand_AddDeadline() throws MossException {
        TaskList.command("deadline Finish homework /by 2023-09-15", tasks, storage);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Deadline);
        assertEquals(LocalDate.parse("2023-09-14"), ((Deadline) tasks.get(0)).getDate());
    }

    /**
     * Tests the 'command' method for adding an event task.
     *
     * @throws MossException If there's an issue with task management.
     */
    @Test
    public void testCommand_AddEvent() throws MossException {
        TaskList.command("event Party /from 2023-09-20 /to 2023-09-22", tasks, storage);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Event);

        assertEquals(LocalDate.parse("2023-09-21"), ((Event) tasks.get(0)).getFromDate());
        assertEquals(LocalDate.parse("2023-09-22"), ((Event) tasks.get(0)).getToDate());
    }

    /**
     * Tests the 'command' method for marking a task as done.
     *
     * @throws MossException If there's an issue with task management.
     */
    @Test
    public void testCommand_MarkTaskDone() throws MossException {
        Task task = new ToDo("Buy groceries");
        tasks.add(task);

        TaskList.command("mark 1", tasks, storage);
        assertTrue(task.isDone());
    }

    /**
     * Tests the 'command' method for marking a task as undone.
     *
     * @throws MossException If there's an issue with task management.
     */
    @Test
    public void testCommand_MarkTaskUndone() throws MossException {
        Task task = new ToDo("Buy groceries");
        task.markDone();
        tasks.add(task);

        TaskList.command("unmark 1", tasks, storage);
        assertFalse(task.isDone());
    }

    /**
     * Tests the 'command' method for deleting a task.
     *
     * @throws MossException If there's an issue with task management.
     */
    @Test
    public void testCommand_DeleteTask() throws MossException {
        Task task = new ToDo("Buy groceries");
        tasks.add(task);

        TaskList.command("delete 1", tasks, storage);
        assertEquals(0, tasks.size());
    }

    // Add more tests for other cases, error conditions, etc.
}


