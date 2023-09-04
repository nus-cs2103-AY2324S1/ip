package duke;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Represents a test for Parser class
 */
public class ParserTest {
    @Test
    public void testGetCommand() {
        assertEquals("todo", new Parser("todo sleep").getCommand());
    }

    @Test
    public void testGetTodoTask() {
        try {
            assertArrayEquals(new String[] {"todo"}, new Parser("todo").getTodoTask());
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testIncompleteCommandDeadline() {
        try {
            assertArrayEquals(new String[] {"deadline"}, new Parser("deadline").getDeadlineTask());
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty and"
                    + " the deadline for the deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testInvalidCommandDeadline() {
        try {
            assertArrayEquals(new String[] {"deadline", "sleep"}, new Parser("deadline sleep /by").getDeadlineTask());
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid command. The command for a deadline should be:\n"
                    + "deadline <task> /by yyyy-mm-dd hh:mm", e.getMessage());
        }
    }

    @Test
    public void testIncompleteCommandEvent() {
        try {
            assertArrayEquals(new String[] {"event"}, new Parser("event").getEventTask());
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of an event cannot be empty and the start"
                    + " and end time/date cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void testInvalidCommandEvent() {
        try {
            assertArrayEquals(new String[] {"event", "sleep"}, new Parser("event sleep /from").getEventTask());
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! Invalid command. The command for an event should be:\n"
                    + "event <task> /from yyyy-mm-dd hh:mm /to yyyy-mm-dd hh:mm", e.getMessage());
        }
    }
}
