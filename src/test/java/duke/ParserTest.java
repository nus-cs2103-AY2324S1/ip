package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseInput_wrongCommand_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("lists", new TaskList()));

        assertEquals("⚠ Eek! I do not understand :(((", thrown.getMessage());
    }

    @Test
    public void parseInput_markInvalidNumber_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("mark a", new TaskList()));

        assertEquals("⚠ Eek! Please enter a valid number!", thrown.getMessage());
    }

    @Test
    public void parseInput_markOutOfBounds_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("mark 1", new TaskList()));

        assertEquals("⚠ Eek! Please enter a number within the list index!", thrown.getMessage());
    }

    @Test
    public void parseInput_markNormalInput_successful() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new ToDo("test"), false);
            int result = Parser.parseInput("mark 1", taskList);

            assertEquals(1, result);
            assertTrue(taskList.getTask(0).getIsDone());
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void parseInput_unmarkInvalidNumber_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("unmark a", new TaskList()));

        assertEquals("⚠ Eek! Please enter a valid number!", thrown.getMessage());
    }

    @Test
    public void parseInput_unmarkOutOfBounds_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("unmark 1", new TaskList()));

        assertEquals("⚠ Eek! Please enter a number within the list index!", thrown.getMessage());
    }

    @Test
    public void parseInput_unmarkNormalInput_successful() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new ToDo("test"), false);
            taskList.markTask(0, true);
            int result = Parser.parseInput("unmark 1", taskList);

            assertEquals(1, result);
            assertFalse(taskList.getTask(0).getIsDone());
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void parseInput_deleteInvalidNumber_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("delete a", new TaskList()));

        assertEquals("⚠ Eek! Please enter a valid number!", thrown.getMessage());
    }

    @Test
    public void parseInput_deleteOutOfBounds_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("delete 1", new TaskList()));

        assertEquals("⚠ Eek! Please enter a number within the list index!", thrown.getMessage());
    }

    @Test
    public void parseInput_deleteNormalInput_successful() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(new ToDo("test"), false);
            int result = Parser.parseInput("delete 1", taskList);

            assertEquals(1, result);
            assertEquals(0, taskList.getCount());
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void parseInput_todoNoName_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("todo ", new TaskList()));

        assertEquals("⚠ Eek! Description of todo cannot be empty!", thrown.getMessage());
    }

    @Test
    public void parseInput_todoNormalInput_successful() {
        try {
            TaskList taskList = new TaskList();
            int result = Parser.parseInput("todo read book", taskList);

            assertEquals(1, result);
            assertEquals(1, taskList.getCount());
            assertInstanceOf(ToDo.class, taskList.getTask(0));
            assertEquals("read book", taskList.getTask(0).getName());
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void parseInput_deadlineNoBy_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("deadline d1 ", new TaskList()));

        assertEquals("⚠ Eek! Please include when the deadline is! (`deadline name /by date`)",
                thrown.getMessage());
    }

    @Test
    public void parseInput_deadlineNoName_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("deadline /by ", new TaskList()));

        assertEquals("⚠ Eek! Please include name and deadline!"
                + "(`deadline name /by date (in yyyy-mm-dd)`)", thrown.getMessage());
    }

    @Test
    public void parseInput_deadlineNormalInput_successful() {
        try {
            TaskList taskList = new TaskList();
            int result = Parser.parseInput("deadline watch lecture /by 2023-08-31", taskList);

            assertEquals(1, result);
            assertEquals(1, taskList.getCount());
            assertInstanceOf(Deadline.class, taskList.getTask(0));
            assertEquals("[D][ ] watch lecture (by: Aug 31 2023)", taskList.getTask(0).toString());
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void parseInput_eventNoFromTo_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("event ", new TaskList()));

        assertEquals("⚠ Eek! Please include when the event is from and to! (`event name /from date /to date`)",
                thrown.getMessage());
    }

    @Test
    public void parseInput_eventNoName_exceptionThrown() {
        DukeException thrown = assertThrows(DukeException.class,
                () -> Parser.parseInput("event /from /to ", new TaskList()));

        assertEquals("⚠ Eek! Please include the name of the event"
                + " and when the event is from and to! (`event name /from date /to date`)", thrown.getMessage());
    }

    @Test
    public void parseInput_eventNormalInput_successful() {
        try {
            TaskList taskList = new TaskList();
            int result = Parser.parseInput("event CCA /from 7pm /to 9pm", taskList);

            assertEquals(1, result);
            assertEquals(1, taskList.getCount());
            assertInstanceOf(Event.class, taskList.getTask(0));
            assertEquals("[E][ ] CCA (from: 7pm to: 9pm)", taskList.getTask(0).toString());
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test
    public void parseInput_bye_successful() {
        try {
            int result = Parser.parseInput("bye", new TaskList());

            assertEquals(0, result);
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }
}
