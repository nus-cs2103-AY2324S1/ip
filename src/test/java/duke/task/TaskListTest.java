package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TaskListTest {
    @Test
    public void testGetSize() {
        TaskList tl = new TaskList();
        assertEquals(tl.getSize(), 0);
    }

    @Test
    public void testAddTasks() {
        try {
            TaskList tl = new TaskList();
            tl.add(new Todo("First task"));
            tl.add(new Deadline("Second task", LocalDateTime.parse("2023-10-10T12:34:56")));
            tl.add(new Event("Third task", LocalDateTime.parse("2023-10-10T12:34:56"),
                    LocalDateTime.parse("2023-10-10T12:34:57")));
            assertEquals(tl.getSize(), 3);
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testRemoveTasks() {
        try {
            TaskList tl = new TaskList();
            tl.add(new Todo("First task"));
            tl.add(new Deadline("Second task", LocalDateTime.parse("2023-10-10T12:34:56")));
            tl.add(new Event("Third task", LocalDateTime.parse("2023-10-10T12:34:56"),
                    LocalDateTime.parse("2023-10-10T12:34:57")));
            tl.remove(2);
            assertEquals(tl.getSize(), 2);
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testGetSavedStrings() throws DukeException {
        TaskList tl = new TaskList();
        tl.add(new Todo("First task"));
        tl.add(new Deadline("Second task", LocalDateTime.parse("2023-10-10T12:34:56")));
        tl.add(new Event("Third task", LocalDateTime.parse("2023-10-10T12:34:56"),
                LocalDateTime.parse("2023-10-10T12:34:57")));
        tl.add(new Deadline("Fourth task", LocalDateTime.parse("2023-10-10T12:34:58")));
        tl.remove(2);
        List<String> result = tl.getSavedStrings();
        assertEquals(result.get(0), "T | 0 | First task");
        assertEquals(result.get(1), "E | 0 | Third task | 2023-10-10T12:34:56 | 2023-10-10T12:34:57");
        assertEquals(result.get(2), "D | 0 | Fourth task | 2023-10-10T12:34:58");
    }

    @Test
    public void testGetTaskString() {
        try {
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
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testMarkAsDone() {
        try {
            TaskList tl = new TaskList();
            tl.add(new Todo("First task"));
            tl.add(new Deadline("Second task", LocalDateTime.parse("2023-10-10T12:34:56")));
            tl.add(new Event("Third task", LocalDateTime.parse("2023-10-10T12:34:56"),
                    LocalDateTime.parse("2023-10-10T12:34:57")));
            tl.add(new Deadline("Fourth task", LocalDateTime.parse("2023-10-10T12:34:58")));
            tl.remove(2);
            tl.markAsDone(3);
            List<String> result = tl.getSavedStrings();
            assertEquals(result.get(0), "T | 0 | First task");
            assertEquals(result.get(1), "E | 0 | Third task | 2023-10-10T12:34:56 | 2023-10-10T12:34:57");
            assertEquals(result.get(2), "D | 1 | Fourth task | 2023-10-10T12:34:58");
            assertEquals(tl.getTaskString(3), "[D][X] Fourth task (by: Oct 10 2023, 12:34:58)");
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testUnmarkAsDone() {
        try {
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
            List<String> result = tl.getSavedStrings();
            assertEquals(result.get(0), "T | 1 | First task");
            assertEquals(result.get(1), "E | 0 | Third task | 2023-10-10T12:34:56 | 2023-10-10T12:34:57");
            assertEquals(result.get(2), "D | 0 | Fourth task | 2023-10-10T12:34:58");
            assertEquals(tl.getTaskString(1), "[T][X] First task");
            assertEquals(tl.getTaskString(3), "[D][ ] Fourth task (by: Oct 10 2023, 12:34:58)");
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testUpdateTask_validTasks_success() {
        try {
            TaskList tl = new TaskList();
            tl.add(new Todo("First task"));
            tl.add(new Event("2nd task", LocalDateTime.parse("2023-10-10T12:34:56"),
                    LocalDateTime.parse("2023-10-10T12:34:57")));
            tl.add(new Deadline("3rd task", LocalDateTime.parse("2023-10-10T12:34:58")));
            tl.updateTask(1, UpdateType.DESCRIPTION, "1st task");
            tl.updateTask(2, UpdateType.DATE2, "2024-01-01T12:00:00");
            tl.updateTask(3, UpdateType.DATE1, "2024-01-01T12:00:01");
            tl.markAsDone(3);
            List<String> result = tl.getSavedStrings();
            assertEquals(result.get(0), "T | 0 | 1st task");
            assertEquals(result.get(1), "E | 0 | 2nd task | 2023-10-10T12:34:56 | 2024-01-01T12:00");
            assertEquals(result.get(2), "D | 1 | 3rd task | 2024-01-01T12:00:01");
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }

    @Test
    public void testUpdateTask_invalidEvent_dukeExceptionThrown() {
        try {
            TaskList tl = new TaskList();
            tl.add(new Todo("First task"));
            tl.add(new Event("2nd task", LocalDateTime.parse("2023-10-10T12:34:56"),
                    LocalDateTime.parse("2023-10-10T12:34:57")));
            tl.add(new Deadline("3rd task", LocalDateTime.parse("2023-10-10T12:34:58")));
            tl.updateTask(1, UpdateType.DESCRIPTION, "1st task");
            tl.updateTask(2, UpdateType.DATE2, "2022-01-01T12:00:00");
            tl.updateTask(3, UpdateType.DATE1, "2024-01-01T12:00:01");
            tl.markAsDone(3);
            fail("DukeException should not be thrown!");
        } catch (DukeException e) {
            assertEquals("Invalid date parameter: From date must be before to date!", e.getMessage());
        }
    }


    @Test
    public void testCloneTask() {
        try {
            TaskList tl = new TaskList();
            tl.add(new Todo("1st task"));
            tl.add(new Event("2nd task", LocalDateTime.parse("2023-10-10T12:34:56"),
                    LocalDateTime.parse("2023-10-10T12:34:57")));
            tl.add(new Deadline("3rd task", LocalDateTime.parse("2023-10-10T12:34:58")));
            tl.markAsDone(2);
            tl.cloneTask(2);
            tl.cloneTask(3);
            List<String> result = tl.getSavedStrings();
            assertEquals(result.get(0), "T | 0 | 1st task");
            assertEquals(result.get(1), "E | 1 | 2nd task | 2023-10-10T12:34:56 | 2023-10-10T12:34:57");
            assertEquals(result.get(2), "D | 0 | 3rd task | 2023-10-10T12:34:58");
            assertEquals(result.get(3), "E | 0 | 2nd task | 2023-10-10T12:34:56 | 2023-10-10T12:34:57");
            assertEquals(result.get(4), "D | 0 | 3rd task | 2023-10-10T12:34:58");
        } catch (DukeException e) {
            fail("DukeException should not be thrown!");
        }
    }
}
