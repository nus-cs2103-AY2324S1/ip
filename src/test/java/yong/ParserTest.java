package yong;

import yong.command.Command;
import yong.command.DeadlineCommand;
import yong.command.DeleteCommand;
import yong.command.EventCommand;
import yong.command.ExitCommand;
import yong.command.ListCommand;
import yong.command.MarkCommand;
import yong.command.StartCommand;
import yong.command.ToDoCommand;
import yong.command.FindCommand;
import yong.command.UnmarkCommand;

import yong.exception.DukeException;
import yong.tasklist.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    private TaskList taskList;
    private Parser parser;

    @BeforeEach
    public void init() {
        this.taskList = new TaskList();
        this.parser = new Parser(taskList);
    }

    @Test
    public void testParse_validInputs_success() {
        try {
            Command command = parser.parse("Hi");
            assertTrue(command instanceof StartCommand);

            command = parser.parse("list");
            assertTrue(command instanceof ListCommand);

            command = parser.parse("todo read book");
            assertTrue(command instanceof ToDoCommand);

            command = parser.parse("deadline borrow book /by 2022-02-02 1900");
            assertTrue(command instanceof DeadlineCommand);

            command = parser.parse("event return book /from 2022-02-02 1800 /to 2023-01-02 1722");
            assertTrue(command instanceof EventCommand);

            command = parser.parse("bye");
            assertTrue(command instanceof ExitCommand);
        } catch (Exception e) {
            fail("Unexpected DukeException: " + e.getMessage());
        }
    }

    @Test
    public void testParse_invalidInputs_exceptionThrown() {
        // Test invalid command
        try {
            Command command = parser.parse("INVALID");
            fail("Expected DukeException, but got " + command.getClass().getSimpleName());
        } catch (DukeException e) {
            assertEquals("I do not know what you are saying.", e.getMessage());
        }
    }

}
