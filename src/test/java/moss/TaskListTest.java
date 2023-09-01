package moss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private ArrayList<Task> tasks;
    private Storage storage;

    @BeforeEach
    public void setUp() throws MossException {
        tasks = new ArrayList<>();
        storage = new Storage();
    }

    @Test
    public void testCommand_AddToDo() throws MossException {
        TaskList.command("todo Buy groceries", tasks, storage);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof ToDo);
    }

    @Test
    public void testCommand_AddDeadline() throws MossException {
        TaskList.command("deadline Finish homework /by 2023-09-15", tasks, storage);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Deadline);
        assertEquals(LocalDate.parse("2023-09-14"), ((Deadline) tasks.get(0)).getDate());
    }

    @Test
    public void testCommand_AddEvent() throws MossException {
        TaskList.command("event Party /from 2023-09-20 /to 2023-09-22", tasks, storage);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof Event);
        assertEquals(LocalDate.parse("2023-09-21"), ((Event) tasks.get(0)).getFromDate());
        assertEquals(LocalDate.parse("2023-09-22"), ((Event) tasks.get(0)).getToDate());
    }

    @Test
    public void testCommand_MarkTaskDone() throws MossException {
        Task task = new ToDo("Buy groceries");
        tasks.add(task);

        TaskList.command("mark 1", tasks, storage);
        assertTrue(task.isDone());
    }

    @Test
    public void testCommand_MarkTaskUndone() throws MossException {
        Task task = new ToDo("Buy groceries");
        task.markDone();
        tasks.add(task);

        TaskList.command("unmark 1", tasks, storage);
        assertFalse(task.isDone());
    }

    @Test
    public void testCommand_DeleteTask() throws MossException {
        Task task = new ToDo("Buy groceries");
        tasks.add(task);

        TaskList.command("delete 1", tasks, storage);
        assertEquals(0, tasks.size());
    }

    // Add more tests for other cases, error conditions, etc.
}

