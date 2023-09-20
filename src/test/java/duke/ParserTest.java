package duke;

import duke.task.TaskList;
import duke.task.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_bye_success() throws DukeException {
        assertEquals("", new Parser(null).parse("88"));
    }

    @Test
    public void parse_reminder_success() throws DukeException {
        assertEquals("Upcoming deadlines:\n"
                + "\"One thing at a time.\"", new Parser(new TaskList()).parse("reminder"));
    }

    @Test
    void parse_find_success() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(TaskType.TODO, "keyword");
        assertEquals("Here are the matching tasks in your list:\n"
                + "1 [T][ ] keyword\n" + "\"One thing at a time.\"", new Parser(tasks).parse("find key"));
    }

    @Test
    void parse_mark_success() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(TaskType.TODO, "keyword");
        assertEquals("Here's your modified task:\n"
                + "[T][X] keyword\n" + "\"Keep moving forward.\"", new Parser(tasks).parse("mark 1"));
    }

    @Test
    void parse_delete_success() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(TaskType.TODO, "keyword");
        assertEquals("I've successfully deleted this task:\n"
                + "[T][ ] keyword\n"
                + "Now you have 0 task in total.\n"
                + "\"Ride the waves.\"", new Parser(tasks).parse("delete 1"));
    }

    @Test
    void parse_addTask_success() throws DukeException {
        assertEquals("Task added:\n"
                + "[T][ ] task\n" + "Now you have 1 task in total.\n" + "\"Be here now.\"",
                new Parser(new TaskList()).parse("todo task"));
    }

    @Test
    void parse_list_success() throws DukeException {
        assertEquals("You have no tasks!\n" +
                "\"Enjoy today.\"", new Parser(new TaskList()).parse("List"));
    }

    @Test
    public void parse_findFailed_exceptionThrown() {
        try {
            assertEquals("task found", new Parser(new TaskList()).parse("find sth"));
            fail();
        } catch (DukeException e) {
            assertEquals("task not found", e.getMessage());
        }
    }

    @Test
    public void parse_markInvalidIndex_exceptionThrown() {
        try {
            assertEquals("task marked", new Parser(new TaskList()).parse("mark 3"));
            fail();
        } catch (DukeException e) {
            assertEquals("task not found", e.getMessage());
        }
    }

    @Test
    public void parse_deleteInvalidIndex_exceptionThrown() {
        try {
            assertEquals("task deleted", new Parser(new TaskList()).parse("delete 3"));
            fail();
        } catch (DukeException e) {
            assertEquals("task not found", e.getMessage());
        }
    }

    @Test
    public void parse_addInvalidTask_exceptionThrown() {
        try {
            assertEquals("task added", new Parser(new TaskList()).parse("deadline by sometime"));
            fail();
        } catch (DukeException e) {
            assertEquals("deadline error", e.getMessage());
        }
    }

    @Test
    public void parse_undefinedInput_exceptionThrown() {
        try {
            assertEquals("⚠ Sorry! I am not able to understand you. Try another language:D",
                    new Parser(null).parse("undefined"));
            fail();
        } catch (DukeException e) {
            assertEquals("undefined", e.getMessage());
        }
    }

}
