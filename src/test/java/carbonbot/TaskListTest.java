package carbonbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import carbonbot.task.Deadline;
import carbonbot.task.Task;
import carbonbot.task.Todo;

public class TaskListTest {
    @Test
    public void delete_emptyList_exceptionThrown() {
        TaskList tasks = new TaskList();
        try {
            tasks.delete(0);
            fail();
        } catch (Exception e) {
            assertEquals("The task list does not contain the given index.", e.getMessage());
        }
    }

    @Test
    public void get_withinBounds_noExceptionThrown() {
        TaskList tasks = new TaskList();
        Task t1 = new Todo("Task 1");
        Task t2 = new Deadline("Task 2", LocalDateTime.parse("2/12/2020 2350",
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
        tasks.add(t1);
        tasks.add(t2);

        assertEquals(tasks.get(1), t1);
        assertEquals(tasks.get(2), t2);
    }

    @Test
    public void get_outOfBounds_exceptionThrown() {
        TaskList tasks = new TaskList();
        Task t1 = new Todo("Task 1");
        Task t2 = new Deadline("Task 2", LocalDateTime.parse("2/12/2020 2350",
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
        tasks.add(t1);
        tasks.add(t2);

        try {
            tasks.get(0);
            fail();
        } catch (Exception e) {
            assertEquals("The task list does not contain the given index.", e.getMessage());
        }

        try {
            tasks.get(3);
            fail();
        } catch (Exception e) {
            assertEquals("The task list does not contain the given index.", e.getMessage());
        }

        try {
            tasks.get(-1);
            fail();
        } catch (Exception e) {
            assertEquals("The task list does not contain the given index.", e.getMessage());
        }
    }

    @Test
    public void delete_outOfBounds_exceptionThrown() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));
        // No Task at Index 3
        try {
            tasks.delete(3);
            fail();
        } catch (Exception e) {
            assertEquals("The task list does not contain the given index.", e.getMessage());
        }

        // Delete Task 1
        tasks.delete(1);

        // No Task at Index 2
        try {
            tasks.delete(2);
            fail();
        } catch (Exception e) {
            assertEquals("The task list does not contain the given index.", e.getMessage());
        }

        tasks.delete(1);

        // No Task at Index 1
        try {
            tasks.delete(1);
            fail();
        } catch (Exception e) {
            assertEquals("The task list does not contain the given index.", e.getMessage());
        }

        // No Task at Index 0
        try {
            tasks.delete(0);
            fail();
        } catch (Exception e) {
            assertEquals("The task list does not contain the given index.", e.getMessage());
        }

    }
}
