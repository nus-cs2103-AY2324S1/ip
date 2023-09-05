package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testGetSize() {
        TaskList tl = new TaskList();
        assertEquals(tl.getSize(), 0);
    }

    @Test
    public void testAddTasks() {
        TaskList tl = new TaskList();
        tl.add(new Todo("First task"));
        tl.add(new Deadline("Second task", LocalDateTime.parse("2023-10-10T12:34:56")));
        tl.add(new Event("Third task", LocalDateTime.parse("2023-10-10T12:34:56"),
                LocalDateTime.parse("2023-10-10T12:34:57")));
        assertEquals(tl.getSize(), 3);
    }

    @Test
    public void testRemoveTasks() {
        TaskList tl = new TaskList();
        tl.add(new Todo("First task"));
        tl.add(new Deadline("Second task", LocalDateTime.parse("2023-10-10T12:34:56")));
        tl.add(new Event("Third task", LocalDateTime.parse("2023-10-10T12:34:56"),
                LocalDateTime.parse("2023-10-10T12:34:57")));
        tl.remove(2);
        assertEquals(tl.getSize(), 2);
    }

    @Test
    public void testGetSavedStrings() {
        TaskList tl = new TaskList();
        tl.add(new Todo("First task"));
        tl.add(new Deadline("Second task", LocalDateTime.parse("2023-10-10T12:34:56")));
        tl.add(new Event("Third task", LocalDateTime.parse("2023-10-10T12:34:56"),
                LocalDateTime.parse("2023-10-10T12:34:57")));
        tl.add(new Deadline("Fourth task", LocalDateTime.parse("2023-10-10T12:34:58")));
        tl.remove(2);
        ArrayList<String> result = tl.getSavedStrings();
        assertEquals(result.get(0), "T | 0 | First task");
        assertEquals(result.get(1), "E | 0 | Third task | 2023-10-10T12:34:56 | 2023-10-10T12:34:57");
        assertEquals(result.get(2), "D | 0 | Fourth task | 2023-10-10T12:34:58");
    }

    @Test
    public void testGetTaskString() {
        TaskList tl = new TaskList();
        tl.add(new Todo("First task"));
        tl.add(new Deadline("Second task", LocalDateTime.parse("2023-10-10T12:34:56")));
        tl.add(new Event("Third task", LocalDateTime.parse("2023-10-10T12:34:56"),
                LocalDateTime.parse("2023-10-10T12:34:57")));
        tl.add(new Deadline("Fourth task", LocalDateTime.parse("2023-10-10T12:34:58")));
        assertEquals(tl.getTaskString(1), "[T][ ] First task");
        assertEquals(tl.getTaskString(2),
                "[D][ ] Second task (by: Oct 10 2023, 12:34:56)");
        assertEquals(tl.getTaskString(3),
                "[E][ ] Third task (from: Oct 10 2023, 12:34:56 to: Oct 10 2023, 12:34:57)");
        assertEquals(tl.getTaskString(4),
                "[D][ ] Fourth task (by: Oct 10 2023, 12:34:58)");

    }

    @Test
    public void testMarkAsDone() {
        TaskList tl = new TaskList();
        tl.add(new Todo("First task"));
        tl.add(new Deadline("Second task", LocalDateTime.parse("2023-10-10T12:34:56")));
        tl.add(new Event("Third task", LocalDateTime.parse("2023-10-10T12:34:56"),
                LocalDateTime.parse("2023-10-10T12:34:57")));
        tl.add(new Deadline("Fourth task", LocalDateTime.parse("2023-10-10T12:34:58")));
        tl.remove(2);
        tl.markAsDone(3);
        ArrayList<String> result = tl.getSavedStrings();
        assertEquals(result.get(0), "T | 0 | First task");
        assertEquals(result.get(1), "E | 0 | Third task | 2023-10-10T12:34:56 | 2023-10-10T12:34:57");
        assertEquals(result.get(2), "D | 1 | Fourth task | 2023-10-10T12:34:58");
        assertEquals(tl.getTaskString(3), "[D][X] Fourth task (by: Oct 10 2023, 12:34:58)");
    }

    @Test
    public void testUnmarkAsDone() {
        TaskList tl = new TaskList();
        tl.add(new Todo("First task"));
        tl.add(new Deadline("Second task", LocalDateTime.parse("2023-10-10T12:34:56")));
        tl.add(new Event("Third task", LocalDateTime.parse("2023-10-10T12:34:56"),
                LocalDateTime.parse("2023-10-10T12:34:57")));
        tl.add(new Deadline("Fourth task", LocalDateTime.parse("2023-10-10T12:34:58")));
        tl.remove(2);
        tl.markAsDone(1);
        tl.markAsDone(3);
        tl.unmarkAsDone(2);
        tl.unmarkAsDone(3);
        ArrayList<String> result = tl.getSavedStrings();
        assertEquals(result.get(0), "T | 1 | First task");
        assertEquals(result.get(1), "E | 0 | Third task | 2023-10-10T12:34:56 | 2023-10-10T12:34:57");
        assertEquals(result.get(2), "D | 0 | Fourth task | 2023-10-10T12:34:58");
        assertEquals(tl.getTaskString(1), "[T][X] First task");
        assertEquals(tl.getTaskString(3), "[D][ ] Fourth task (by: Oct 10 2023, 12:34:58)");
    }
}
