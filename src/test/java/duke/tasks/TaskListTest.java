package duke.tasks;

import duke.exception.DukeException;
import duke.storage.Storage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    public static final String INDENTATION = "    ";


    @Test
    public void testAddToDoSuccess() {
        Storage storage = new Storage("duke/test.txt");
        TaskList testList = new TaskList(new ArrayList<>(), storage);
        String actual = testList.handleToDo("test desc");
        String expected = "Got it. I've added this task:\n   " +
                INDENTATION + "[T][ ] test desc" + "\n " + INDENTATION + "Now you have " +
                1 + " tasks in the list.";
        assertEquals(expected, actual);
    }

    @Test
    public void testAddDeadlineSuccess() {
        Storage storage = new Storage("duke/test.txt");
        TaskList testList = new TaskList(new ArrayList<>(), storage);
        String actual = "";
        try {
            actual = testList.handleDeadline("boo", "2001-10-22 11:12");
        } catch (DukeException e) {
            fail("Did not add Deadline successfully");
        }
        String expected = "Got it. I've added this task:\n   " +
                INDENTATION + "[D][ ] boo (by: 22 October 2001 11:12)" + "\n " + INDENTATION + "Now you have " +
                1 + " tasks in the list.";
        assertEquals(expected, actual);
    }

    @Test
    public void testAddDeadlineError() {
        Storage storage = new Storage("duke/test.txt");
        TaskList testList = new TaskList(new ArrayList<>(), storage);
        Exception exception = assertThrows(DukeException.class, () -> {
            testList.handleDeadline("boo", "2001-10-22");
        });
        String expected = "Deadline time must be in this format: yyyy-mm-dd hh:mm";
        assertEquals(expected, exception.getMessage());
    }

    @Test
    public void testAddEventSuccess() {
        Storage storage = new Storage("duke/test.txt");
        TaskList testList = new TaskList(new ArrayList<>(), storage);
        String actual = "";
        try {
            actual = testList.handleEvent("boo", "2001-10-22 11:12", "2001-11-22 11:12");
        } catch (DukeException e) {
            fail("Did not add Event successfully");
        }
        String expected = "Got it. I've added this task:\n   " +
                INDENTATION + "[E][ ] boo (from: 22 October 2001 11:12 to: 22 November 2001 11:12)" + "\n " +
                INDENTATION + "Now you have " + 1 + " tasks in the list.";
        assertEquals(expected, actual);
    }

    @Test
    public void testAddEventError() {
        Storage storage = new Storage("duke/test.txt");
        TaskList testList = new TaskList(new ArrayList<>(), storage);
        Exception exception = assertThrows(DukeException.class, () -> {
            testList.handleEvent("boo", "2001-10-22 11:12", "2001-11-22");
        });
        String expected = "Event times must be in this format: yyyy-mm-dd HH:mm";
        assertEquals(expected, exception.getMessage());
    }
}
