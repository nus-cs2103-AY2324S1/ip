package buddy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import buddy.utils.BuddyException;

public class TaskListTest {
    @Test
    public void taskListConstructorTest() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.getSize(), "Test initialisation of empty TaskList.");
    }

    @Test
    public void taskListOverloadedConstructorTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        Todo todo = new Todo("read book", false);
        tasks.add(todo);
        TaskList taskList = new TaskList(tasks);
        assertEquals(1, taskList.getSize(), "Test initialisation of TaskList.");
    }

    @Test
    public void taskListMethodsTest() {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("read book", false);
        tasks.addTask(todo);

        assertEquals(1, tasks.getSize(), "Test adding of task to TaskList.");

        Deadline deadline = new Deadline("return book", LocalDate.parse("2023-06-06"), false);
        tasks.addTask(deadline);

        Event event = new Event("holiday trip", LocalDate.parse("2023-12-11"),
                LocalDate.parse("2023-12-14"), false);
        tasks.addTask(event);

        try {
            tasks.markAsDone(1);
        } catch (BuddyException e) {
            throw new RuntimeException(e);
        }
        assertTrue(tasks.getTask(1).isDone, "Test marking tasks as done.");

        try {
            tasks.deleteTask(1);
        } catch (BuddyException e) {
            throw new RuntimeException(e);
        }

        assertEquals(2, tasks.getSize(), "Test deleting of task from TaskList.");
    }
}
